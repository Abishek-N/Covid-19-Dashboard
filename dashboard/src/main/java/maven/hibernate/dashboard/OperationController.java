package maven.hibernate.dashboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.qoppa.office.WordDocument;

import maven.hibernate.DAO.AdminDAO;
import maven.hibernate.DAO.DistrictTableDAO;
import maven.hibernate.DAO.EpassDAO;
import maven.hibernate.DAO.StateDataDAO;
import maven.hibernate.DAO.UserVerificationDAO;
import maven.hibernate.DAO.UsersDAO;
import maven.hibernate.entities.Admin;
import maven.hibernate.entities.DistrictTable;
import maven.hibernate.entities.Epass;
import maven.hibernate.entities.StateData;
import maven.hibernate.entities.UserVerification;
import maven.hibernate.entities.Users;

@WebServlet("/Operation")
public class OperationController extends HttpServlet {
	private static final long serialVersionUID = 1L;




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action").toLowerCase();
		switch(action) {
		case "sendreportmail":
			sendReportMail(request,response);
			break;
		case "ackepass":
			ackEpass(request,response);
			break;
		case "applyepass":
			applyEpass(request,response);
			break;
		case "otpsuccess":
			request.getRequestDispatcher("registerSuccess.jsp").forward(request, response);
			break;
		case "success":
			request.getRequestDispatcher("login.jsp").forward(request, response);
			break;
		case "login":
			login(request,response);
			break;
		case "register":
			register(request,response);
			break;
		case "logout":
			logout(request,response);
			break;
		case "otp":
			verifyUser(request,response);
			break;
		case "adminlogin":
			if((String)request.getAttribute("adminLogout")!=null) {
				request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
			}
			else {
				adminLogin(request,response);
			}
			break;
		case "adminlogout":
			adminLogout(request,response);
			break;
		case "adddata":
			addData(request,response);
			break;
		case "addadmin":
			addAdmin(request,response);
			break;
		case "updatedb":
			updateDb(request,response);
			break;
		case "adddb":
			addDb(request,response);
			break;
		}
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email=request.getParameter("inputEmail");
		String password=request.getParameter("inputPassword");
		String url=request.getContextPath()+"/SiteController?page=";



		Users user=new UsersDAO().getObj(email);
		if(user!=null) {
			if(user.getPassword().equals(password)) {
				request.getSession().invalidate();
				HttpSession newSession = request.getSession(true);
				newSession.setMaxInactiveInterval(-1);
				newSession.setAttribute("username", user.getUsername());
				request.setAttribute("logout", "false");
				response.sendRedirect(url+"index");
			}
			else {
				response.sendRedirect(url+"login");;
			}
		}
		else {
			response.sendRedirect("register");
		}
	}
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("inputUsername");
		String email = request.getParameter("inputEmail");
		String password = request.getParameter("inputPassword");

		System.out.println(username+" "+email+" "+password);

		Random rand = new Random();

		int otp = rand.nextInt(1000000-100000)+100000;

		while(new UserVerificationDAO().checkOtp(otp)!=null) {
			otp = rand.nextInt(1000000-100000)+100000;
		}
		UserVerification user = new UserVerification(username,password,email,otp);

		System.out.println(user.getUsername()+" "+user.getPassword()+" "+user.getEmail());

		sendMail(request,response,user,email);

		new Thread(new Runnable() {
			@Override
			public void run(){
				try {
					Thread.sleep(300000);

				}
				catch(InterruptedException e){
					System.out.println(e.toString());
				}
				new UserVerificationDAO().removeOtp(user);
			}
		}).start();
		request.setAttribute("username", username);
		request.getRequestDispatcher("registerOtp.jsp").forward(request, response);
	}
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session=request.getSession(false);
		session.removeAttribute("username");
		session.invalidate();
		request.setAttribute("logout", "true");
		String url=(String)request.getContextPath()+"/SiteController?page=index";
		response.sendRedirect(url);;
	}
	protected void sendMail(HttpServletRequest request, HttpServletResponse response,UserVerification user,String recMail) throws ServletException, IOException{
		System.out.println("Sending verification email "+user.getEmail()+" "+user.getPassword()+" "+user.getUsername());
		while(new UserVerificationDAO().getObj(user.getUsername())!=null) {
			new UserVerificationDAO().removeOtp(new UserVerificationDAO().getObj(user.getUsername()));
		}
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		String myEmail="yourmail@gmail.com";
		String myPassword="**********";

		try {

			Session session=Session.getInstance(properties,new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myEmail,myPassword);
				}
			});

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(myEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recMail));
			message.setSubject("Covid-19 Dashboard - OTP Verification");
			message.setText("Welcome to covid-19 Dashboard.\nVerify your account to complete your registration in our website using the following OTP:\n"+user.getOtp());

			Transport.send(message);
			new UserVerificationDAO().insertOtp(user);
			System.out.println("Verification message sent successful");		
			return;
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}

	protected void verifyUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String url=(String)request.getContextPath()+"/SiteController?page=";
		
		int otp=Integer.parseInt(request.getParameter("inputOtp"));

		UserVerification user = new UserVerificationDAO().checkOtp(otp);
		System.out.println(user.getEmail()+" "+user.getUsername());

		String username=request.getParameter("username");
		System.out.println(username);

		try {
			if(user.getUsername().equals(username)) {
				Users newUser=new Users(user.getUsername(),user.getPassword(),user.getEmail());
				new UsersDAO().registerUser(newUser);
				response.sendRedirect(url+"login");
			}
			else {
				request.setAttribute("username", username);
				response.sendRedirect(url+"register");
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}

	protected void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email=request.getParameter("inputEmail");
		String password=request.getParameter("inputPassword");
		String url=(String)request.getContextPath()+"/SiteController?page=";
		
		Admin admin=new AdminDAO().getObj(email);
		if(admin!=null) {
			if(admin.getPassword().equals(password)) {
				request.getSession().invalidate();
				HttpSession newSession = request.getSession(true);
				newSession.setMaxInactiveInterval(-1);
				newSession.setAttribute("adminEmail", admin.getEmail());
				request.removeAttribute("adminLogout");
				response.sendRedirect(url+"adminpage");
			}
			else {
				response.sendRedirect(url+"admin");
			}
		}
		else {
			response.sendRedirect(url+"admin");
		}
	}

	protected void adminLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String url=(String)request.getContextPath()+"/SiteController?page=";
		HttpSession session=request.getSession(false);
		session.removeAttribute("adminEmail");
		session.invalidate();
		request.setAttribute("adminLogout", "true");		
		response.sendRedirect(url+"admin");
	}



	protected void addData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=(String)request.getParameter("newId");
		String name=(String)request.getParameter("newState");
		int districts=Integer.parseInt((String)request.getParameter("noState"));
		String url=(String)request.getContextPath()+"/SiteController?page=";

		
		StateData state=new StateData(name,id,0,0,0,0);
		request.setAttribute("stateId",id);
		request.setAttribute("numbers", districts);

		new StateDataDAO().createNewState(state);

		response.sendRedirect(url+"admindistrict");

	}
	protected void addAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username=request.getParameter("inputUsername");
		String password=request.getParameter("inputPassword");
		String email=request.getParameter("inputEmail");
		String url=(String)request.getContextPath()+"/SiteController?page=";

		Admin admin = new Admin(username,password,email);
		new AdminDAO().registerAdmin(admin);

		response.sendRedirect(url+"adminpage");

	}

	protected void updateDb(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String url=(String)request.getContextPath()+"/SiteController?page=";
		String id=request.getParameter("stateId");
		List<DistrictTable> districts = new DistrictTableDAO().getDistricts(id);
		int totalActive=0;
		int totalConfirmed=0;
		int totalDeath=0;
		int totalRecovered=0;
		for(DistrictTable district:districts) {
			int active=Integer.parseInt(request.getParameter(district.getDistrictName().concat("Active")));
			int confirmed=Integer.parseInt(request.getParameter(district.getDistrictName().concat("Confirmed")));
			int death=Integer.parseInt(request.getParameter(district.getDistrictName().concat("Death")));
			int recovered=Integer.parseInt(request.getParameter(district.getDistrictName().concat("Recovered")));

			totalActive+=active;
			totalConfirmed+=confirmed;
			totalDeath+=death;
			totalRecovered+=recovered;
			int districtId=new DistrictTableDAO().getDistrictId(district.getDistrictName(), id);

			new DistrictTableDAO().updateDistrict(active, confirmed, death, recovered,districtId);

		}
		new StateDataDAO().updateState(totalActive, totalConfirmed, totalDeath, totalRecovered, id);

		response.sendRedirect(url+"adminpage");
	}

	protected void addDb(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String url=(String)request.getContextPath()+"/SiteController?page=";
		String stateId=request.getParameter("stateId");
		int num=Integer.parseInt(request.getParameter("numbers"));
		int totalActive=0;
		int totalConfirmed=0;
		int totalDeath=0;
		int totalRecovered=0;

		for(int i=1;i<num;i++) {
			String name=request.getParameter(i+"Name");
			int active=Integer.parseInt(request.getParameter(i+"Active"));
			int confirmed=Integer.parseInt(request.getParameter(i+"Confirmed"));
			int death=Integer.parseInt(request.getParameter(i+"Death"));
			int recovered=Integer.parseInt(request.getParameter(i+"Recovered"));

			DistrictTable district = new DistrictTable(name,stateId,confirmed,recovered,active,death);

			new DistrictTableDAO().createNewDistrict(district);

			totalActive+=active;
			totalConfirmed+=confirmed;
			totalDeath+=death;
			totalRecovered+=recovered;

		}
		new StateDataDAO().updateState(totalActive, totalConfirmed, totalDeath, totalRecovered, stateId);

		response.sendRedirect(url+"adminpage");
	}

	protected void applyEpass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String url=(String)request.getContextPath()+"/SiteController?page=";
		try {
		String name=(String)request.getParameter("inputName");
		int age=Integer.parseInt((String)request.getParameter("inputAge"));
		String email=(String)request.getParameter("inputEmail");
		String address=(String)request.getParameter("inputAddress");
		String aadhar=(String)request.getParameter("inputAadhar");
		String purpose=(String)request.getParameter("inputPurpose");
		String from=(String)request.getParameter("inputFrom");
		String to=(String)request.getParameter("inputTo");
		
		Epass epass=new Epass(name,age,email,address,aadhar,purpose,from,to);
		
		new EpassDAO().applyEpass(epass);

		response.sendRedirect(url+"ackepass");
		}
		catch(Exception e) {
			System.out.println(e.toString());
			response.sendRedirect(url+"ackepass");

		}
	}

	protected void ackEpass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String url=request.getContextPath()+"/SiteController?page=";
		String approval=(String)request.getParameter("approval");
		
		if(approval.equals("accept")) {
			acceptEpass(request,response);
		}
		else if(approval.equals("reject")) {
			rejectEpass(request,response);
		}
		else {
			response.sendRedirect(url+"adminpage");
		}
		
	}

	protected void acceptEpass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String url=(String)request.getContextPath()+"/SiteController?page=";

	try {
		int epassId=Integer.parseInt((String)request.getParameter("epassId"));
		String pdfName="report.pdf";
		String wordName="wordReport.docx";

		XWPFDocument doc = new XWPFDocument();
		File file = new File(wordName);

		FileOutputStream out = new FileOutputStream(file);

		Epass epass=new EpassDAO().getPass(epassId);
		
			XWPFParagraph para = doc.createParagraph();
			XWPFRun run = para.createRun();

			run.setBold(true);
			run.setText("E-PASS");
			run.setBold(false);


			XWPFTable table=doc.createTable();

			XWPFTableRow rowOne=table.getRow(0);
			rowOne.getCell(0).setText("Name");
			rowOne.createCell().setText(epass.getName());
			
			XWPFTableRow rowTwo=table.createRow();
			rowTwo.getCell(0).setText("Age");
			rowTwo.getCell(1).setText(epass.getAge()+"");
			
			XWPFTableRow rowThree=table.createRow();
			rowThree.getCell(0).setText("Address");
			rowThree.getCell(1).setText(epass.getAddress());
			
			XWPFTableRow rowFour=table.createRow();
			rowFour.getCell(0).setText("Aadhar No.");
			rowFour.getCell(1).setText(epass.getAadharNo()+"");
			
			XWPFTableRow rowFive=table.createRow();
			rowFive.getCell(0).setText("Purpose");
			rowFive.getCell(1).setText(epass.getPurpose());
			
			XWPFTableRow rowSix=table.createRow();
			rowSix.getCell(0).setText("From");
			rowSix.getCell(1).setText(epass.getFrom());
			
			XWPFTableRow rowSeven=table.createRow();
			rowSeven.getCell(0).setText("To");
			rowSeven.getCell(1).setText(epass.getTo());
			

		doc.write(out);
		out.close();

		File pdfFile=new File(pdfName);

		InputStream is = new FileInputStream(wordName);
		OutputStream os = new FileOutputStream(pdfFile);

		WordDocument wdoc=new WordDocument(is);
		wdoc.saveAsPDF(os);
		
		
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		String myEmail="abishek259@gmail.com";
		String myPassword="abin04259093";



			Session session=Session.getInstance(properties,new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myEmail,myPassword);
				}
			});

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(myEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(epass.getEmail()));
			message.setSubject("Covid-19 Dashboard : E-pass");
			BodyPart messageBodyPart = new MimeBodyPart();

	         messageBodyPart.setText("Greetings!\n	Your request for E-pass has been accepted.");

	         Multipart multipart = new MimeMultipart();

	         multipart.addBodyPart(messageBodyPart);

	         messageBodyPart = new MimeBodyPart();
	         DataSource source = new FileDataSource(pdfFile);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(pdfName);
	         multipart.addBodyPart(messageBodyPart);

	         message.setContent(multipart);

	         Transport.send(message);
		
	         System.out.println("E-pass sent successful");
	         doc.close();
	         new EpassDAO().removePass(epass);
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	finally {
		response.sendRedirect(url+"adminepasses");
	}
		
	}

	protected void rejectEpass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int epassId=Integer.parseInt((String)request.getParameter("epassId"));
		Epass epass = new EpassDAO().getPass(epassId);
		String url=(String)request.getContextPath()+"/SiteController?page=";

		
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		String myEmail="abishek259@gmail.com";
		String myPassword="abin04259093";

		try {

			Session session=Session.getInstance(properties,new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myEmail,myPassword);
				}
			});

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(myEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(epass.getEmail()));
			message.setSubject("Covid-19 Dashboard : E-Pass");
			message.setText("Greetings!\n	We are sorry to inform you that your request for E-Pass has been denied.");

			Transport.send(message);
	        new EpassDAO().removePass(epass);
			System.out.println("E-Pass message sent successful");
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			response.sendRedirect(url+"adminepasses");
		}
		
	}
	
	protected void sendReportMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			
			String url=(String)request.getContextPath()+"/SiteController?page=";
			String recMail=(String)request.getParameter("inputEmail");
			String pdfName="report.pdf";
			String wordName="wordReport.docx";

			XWPFDocument doc = new XWPFDocument();
			File file = new File(wordName);

			FileOutputStream out = new FileOutputStream(file);

			List<StateData> states = new StateDataDAO().getStates();

			for(StateData state:states) {
				XWPFParagraph para = doc.createParagraph();
				XWPFRun run = para.createRun();

				run.setBold(true);
				run.setText("State Name:"+state.getStateName()+"\n");
				run.setBold(false);
				run.addCarriageReturn();
				run.setText("Active:"+state.getActive());
				run.addCarriageReturn();
				run.setText("Confirmed:"+state.getConfirmed());
				run.addCarriageReturn();
				run.setText("Death:"+state.getDeath());
				run.addCarriageReturn();
				run.setText("Recovered:"+state.getRecovered());
				run.addCarriageReturn();

				List<DistrictTable> districts = new DistrictTableDAO().getDistricts(state.getStateCode());

				XWPFTable table=doc.createTable();

				XWPFTableRow rowOne=table.getRow(0);

				rowOne.getCell(0).setText("DISTRICT NAME");
				rowOne.createCell().setText("ACTIVE");
				rowOne.createCell().setText("CONFIRMED");
				rowOne.createCell().setText("DEATH");
				rowOne.createCell().setText("RECOVERED");


				for(DistrictTable district:districts) {

					XWPFTableRow row=table.createRow();
					row.getCell(0).setText(district.getDistrictName());
					row.getCell(1).setText(""+district.getActive());
					row.getCell(2).setText(""+district.getConfirmed());
					row.getCell(3).setText(""+district.getDeath());
					row.getCell(4).setText(""+district.getRecovered());

				}
			}

			doc.write(out);
			out.close();

			File pdfFile=new File(pdfName);

			InputStream is = new FileInputStream(wordName);
			OutputStream os = new FileOutputStream(pdfFile);

			WordDocument wdoc=new WordDocument(is);
			wdoc.saveAsPDF(os);
			
			Properties properties = new Properties();

			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");

			String myEmail="abishek259@gmail.com";
			String myPassword="abin04259093";



				Session session=Session.getInstance(properties,new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(myEmail,myPassword);
					}
				});

				Message message = new MimeMessage(session);

				message.setFrom(new InternetAddress(myEmail));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(recMail));
				message.setSubject("Covid-19 Dashboard : Report");
				BodyPart messageBodyPart = new MimeBodyPart();

		         messageBodyPart.setText("Greetings!\n	The report that you have requested has been attached in this e-mail.");

		         Multipart multipart = new MimeMultipart();

		         multipart.addBodyPart(messageBodyPart);

		         messageBodyPart = new MimeBodyPart();
		         DataSource source = new FileDataSource(pdfFile);
		         messageBodyPart.setDataHandler(new DataHandler(source));
		         messageBodyPart.setFileName(pdfName);
		         multipart.addBodyPart(messageBodyPart);

		         message.setContent(multipart);

		         Transport.send(message);
			
		         System.out.println("Email report sent successful");
		         doc.close();
		         response.sendRedirect(url+"index");
			
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
		
	}


}
