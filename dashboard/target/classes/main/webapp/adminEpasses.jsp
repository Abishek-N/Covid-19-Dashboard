<jsp:include page="adminHeader.jsp" />

<br/>
<%@ page import="java.util.List" %>
<%@ page import="maven.hibernate.entities.Epass" %>
<%@ page import="maven.hibernate.DAO.EpassDAO" %>

<%

List<Epass> passes = new EpassDAO().getPasses();

for(Epass pass:passes){
	out.print("<a href=\""+request.getContextPath()+"/SiteController?page=adminEpass&epassId="+pass.getEpassId()+"\">");
	out.print("<div class=\"col-xl-12\">");
	out.print("<div class=\"row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative\">");
	out.print("<div class=\"col p-4 d-flex flex-column position-static\">");
	out.print("<h3 class=\"mb-0\" style=\"color : black\" align=\"center\">Name: "+pass.getName()+"</h3>");
	out.print("<h3 class=\"mb-0\" style=\"color : black\" align=\"center\">From: "+pass.getFrom()+"</h3>");
	out.print("<h3 class=\"mb-0\" style=\"color : black\" align=\"center\">To: "+pass.getTo()+"</h3>");
	out.print("<h3 class=\"mb-0\" style=\"color : black\" align=\"center\">Purpose: "+pass.getPurpose()+"</h3>");
	out.print("</div>");
	out.print("</div>");
	out.print("</div>");
	out.print("</a>");
}

%>


<jsp:include page="adminFooter.jsp" />