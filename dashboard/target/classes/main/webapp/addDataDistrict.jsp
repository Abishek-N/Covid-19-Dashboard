<jsp:include page="adminHeader.jsp"/>

<br/>
	<%@ page import="java.util.List" %>
	<%@ page import="maven.hibernate.DAO.DistrictTableDAO" %>
	<%@ page import="maven.hibernate.entities.DistrictTable" %>
	<%@ page import="maven.hibernate.DAO.StateDataDAO" %>
	
	
 	<%
 	String id=(String)request.getAttribute("stateId");
 	int num=Integer.parseInt(request.getAttribute("numbers").toString());
	out.print("<form action=\""+request.getContextPath()+"/Operation?action=addDb\" method=\"POST\">");
	out.print("<table class=\"table\">");
	out.print("<thead>");
	out.print("<tr class=\"table-active\">");
	out.print("<th scope=\"col\">#</th>");
	out.print("<th scope=\"col\">District Name</th>");
	out.print("<th scope=\"col\">Active</th>");
	out.print("<th scope=\"col\">Confirmed</th>");
	out.print("<th scope=\"col\">Death</th>");
	out.print("<th scope=\"col\">Recovered</th>");
	out.print("</tr>");
	out.print("</thead");
	out.print("<tbody>");
	
	for(int i=1;i<=num;i++){
		
		out.print("<tr class=\"table-active\">");
		out.print("<th scope=\"col\">"+i+"</th>");
		out.print("<td scope=\"col\">"+"<input type=\"text\" class=\"form-control mb-2 mr-sm-2\" id=\""+i+"Name\" name=\""+i+"Name\" value=\"Name"+i+"\" ></td>");
		out.print("<td scope=\"col\">"+"<input type=\"text\" class=\"form-control mb-2 mr-sm-2\" id=\""+i+"Active\" name=\""+i+"Active\" value=\"0\" ></td>");
		out.print("<td scope=\"col\">"+"<input type=\"text\" class=\"form-control mb-2 mr-sm-2\" id=\""+i+"Confirmed\" name=\""+i+"Confirmed\" value=\"0\" ></td>");
		out.print("<td scope=\"col\">"+"<input type=\"text\" class=\"form-control mb-2 mr-sm-2\" id=\""+i+"Death\" name=\""+i+"Death\" value=\"0\" ></td>");
		out.print("<td scope=\"col\">"+"<input type=\"text\" class=\"form-control mb-2 mr-sm-2\" id=\""+i+"Recovered\" name=\""+i+"Recovered\" value=\"0\" ></td>");
		out.print("</tr>");

	}
		out.print("</tbody>");
		out.print("</table>");
		out.print("<div align=\"center\">");
		out.print("<input type=\"hidden\" id=\"stateId\" name=\"stateId\" value=\""+id+"\" >");
		out.print("<input type=\"hidden\" id=\"numbers\" name=\"numbers\" value=\""+num+"\" >");
		out.print("<input type=\"submit\" class=\"btn btn-primary mb-2\" >");
		out.print("</div>");
		out.print("</form>");
	%>

<jsp:include page="adminFooter.jsp"/>