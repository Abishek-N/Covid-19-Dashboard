package maven.hibernate.dashboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.qoppa.office.WordDocument;

import maven.hibernate.DAO.DistrictTableDAO;
import maven.hibernate.DAO.StateDataDAO;
import maven.hibernate.entities.DistrictTable;
import maven.hibernate.entities.StateData;




@WebServlet("/")
public class SiteController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page=request.getParameter("page");
		if(page!=null) {
			page=page.toLowerCase();
			switch(page) {
			case "admindistrict":
				if((String)request.getSession().getAttribute("adminEmail")!=null) {
					request.getRequestDispatcher("addDataDistrict.jsp").forward(request, response);
				}
				else {
					request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
				}
				break;
			case "ackepass":
				request.getRequestDispatcher("ackEpass").forward(request, response);;
				break;
			case "reportpage":
				request.getRequestDispatcher("report.jsp").forward(request, response);
				break;
			case "applyepass":
				request.getRequestDispatcher("applyEpass.jsp").forward(request, response);
				break;
			case "adminepass":
				if((String)request.getSession().getAttribute("adminEmail")!=null) {
					adminEpass(request,response);
				}
				else {
					request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
				}
				break;
			case "adminepasses":
				if((String)request.getSession().getAttribute("adminEmail")!=null) {
					request.getRequestDispatcher("adminEpasses.jsp").forward(request, response);
				}
				else {
					request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
				}
				break;
			case "index":
				request.getRequestDispatcher("index.jsp").forward(request,response);
				break;
			case "login":
				login(request,response);
				break;
			case "report":
				report(request,response);
				break;
			case "register":
				register(request,response);
				break;
			case "admin":
				request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
				break;
			case "adminpage":
				if((String)request.getSession().getAttribute("adminEmail")!=null) {
					request.getRequestDispatcher("admin.jsp").forward(request, response);
				}
				else {
					request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
				}
				break;
			case "district":
				districtPage(request,response);
				break;
			case "state":
				statePage(request,response);
				break;
			case "adminadd":
				if((String)request.getSession().getAttribute("adminEmail")!=null) {
				request.getRequestDispatcher("addAdmin.jsp").forward(request, response);
				}
				else {
					request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
				}
				break;
			case "adminupdate":
				if((String)request.getSession().getAttribute("adminEmail")!=null) {
				request.getRequestDispatcher("updateData.jsp").forward(request, response);
				}
				else {
					request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
				}
				break;
			case "updatedata":
				if((String)request.getSession().getAttribute("adminEmail")!=null) {
					updateData(request,response);
				}
				else {
					request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
				}
				break;
			case "admindata":
				if((String)request.getSession().getAttribute("adminEmail")!=null) {
				request.getRequestDispatcher("addData.jsp").forward(request, response);
				}
				else {
					request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
				}
				break;
			default:
				String url=request.getContextPath()+"/SiteController?page=index";
				response.sendRedirect(url);
				break;
			}
		}
	}

	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(request.getSession().getAttribute("username")!=null){
			String url=request.getContextPath().concat("/SiteController?page=index");
			response.sendRedirect(url);
		}
		else
		{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("register.jsp").forward(request,response);
	}

	protected void statePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		request.setAttribute("stateId",id);
		request.getRequestDispatcher("state.jsp").forward(request, response);
	}

	protected void districtPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		String name=new DistrictTableDAO().getDistrictName(id);
		request.setAttribute("districtName",name);
		request.setAttribute("districtId", id);
		request.getRequestDispatcher("district.jsp").forward(request, response);


	}

	protected void updateData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("stateId");
		request.setAttribute("stateId", id);
		request.getRequestDispatcher("updateDataDistrict.jsp").forward(request, response);


	}

	protected void report(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String type=request.getParameter("type");
		switch(type) {
		case "pdf":
			reportPdf(request,response);
			break;
		case "text":
			reportText(request,response);
			break;
		case "word":
			reportWord(request,response);
			break;
		}
	}

	protected void reportPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
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

			response.setContentType("text/html");
			PrintWriter Pout = response.getWriter();

			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ pdfName + "\"");

			FileInputStream fileInputStream = new FileInputStream(pdfFile);

			int i;
			while ((i = fileInputStream.read()) != -1) {
				Pout.write(i);
			}
			fileInputStream.close();
			Pout.close();
			doc.close();
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}



	protected void reportText(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			List<StateData> states = new StateDataDAO().getStates();

			String fileName = "report.txt";
			File file = new File(fileName);

			FileWriter fw = new FileWriter(file);

			for(StateData state:states) {
				List<DistrictTable> districts = new DistrictTableDAO().getDistricts(state.getStateCode());

				fw.write("================================================================\n");


				fw.write(state.getStateName()+":-\n");
				fw.write("Active:"+state.getActive()+"\n");
				fw.write("Confirmed:"+state.getConfirmed()+"\n");
				fw.write("Death:"+state.getDeath()+"\n");
				fw.write("Recovered:"+state.getRecovered()+"\n");

				fw.write("================================================================\n");

				for(DistrictTable district:districts) {
					fw.write(district.getDistrictName()+":-\n");
					fw.write("Active:"+district.getActive()+"\n");
					fw.write("Confirmed:"+district.getConfirmed()+"\n");
					fw.write("Death:"+district.getDeath()+"\n");
					fw.write("Recovered:"+district.getRecovered()+"\n");
					fw.write("-----------------------------------------------------------------\n");

				}
			}
			fw.close();

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + "\"");
			FileInputStream fileInputStream = new FileInputStream(file);

			int i;
			while ((i = fileInputStream.read()) != -1) {
				out.write(i);
			}
			fileInputStream.close();
			out.close();
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}


	protected void reportWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		XWPFDocument doc = new XWPFDocument();
		String fileName="report.docx";
		File file = new File(fileName);

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

		response.setContentType("text/html");
		PrintWriter Pout = response.getWriter();

		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");
		FileInputStream fileInputStream = new FileInputStream(file);

		int i;
		while ((i = fileInputStream.read()) != -1) {
			Pout.write(i);
		}
		fileInputStream.close();
		Pout.close();
		doc.close();
	}

	protected void adminEpass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String epassId=(String)request.getParameter("epassId");
		
		request.setAttribute("epassId", epassId);
		request.getRequestDispatcher("adminEpass.jsp").forward(request, response);
		
	}


}
