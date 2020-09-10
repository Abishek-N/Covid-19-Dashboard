<jsp:include page="adminHeader.jsp"/>

	<br/>
	<%@ page import="java.util.List" %>
	<%@ page import="maven.hibernate.DAO.DistrictTableDAO" %>
	<%@ page import="maven.hibernate.entities.DistrictTable" %>
	<%@ page import="maven.hibernate.DAO.StateDataDAO" %>

 	<%
 	String id=(String)request.getAttribute("stateId");
	List<DistrictTable> districts = new DistrictTableDAO().getDistricts(id);
	out.print("<form action=\""+request.getContextPath()+"/Operation?action=updateDb\" method=\"POST\">");
	out.print("<table class=\"table\">");
	out.print("<thead>");
	out.print("<tr class=\"table-active\">");
	out.print("<th scope=\"col\">District Name</th>");
	out.print("<th scope=\"col\">Active</th>");
	out.print("<th scope=\"col\">Confirmed</th>");
	out.print("<th scope=\"col\">Death</th>");
	out.print("<th scope=\"col\">Recovered</th>");
	out.print("</tr>");
	out.print("</thead");
	out.print("<tbody>");
	
	for(DistrictTable district : districts){
		
		out.print("<tr class=\"table-active\">");
		out.print("<th scope=\"col\">"+district.getDistrictName()+"</th>");
		out.print("<td scope=\"col\">"+"<input type=\"text\" class=\"form-control mb-2 mr-sm-2\" id=\""+district.getDistrictName()+"Active\" name=\""+district.getDistrictName()+"Active\" value="+district.getActive()+"></td>");
		out.print("<td scope=\"col\">"+"<input type=\"text\" class=\"form-control mb-2 mr-sm-2\" id=\""+district.getDistrictName()+"Confirmed\" name=\""+district.getDistrictName()+"Confirmed\" value="+district.getConfirmed()+"></td>");
		out.print("<td scope=\"col\">"+"<input type=\"text\" class=\"form-control mb-2 mr-sm-2\" id=\""+district.getDistrictName()+"Death\" name=\""+district.getDistrictName()+"Death\" value="+district.getDeath()+"></td>");
		out.print("<td scope=\"col\">"+"<input type=\"text\" class=\"form-control mb-2 mr-sm-2\" id=\""+district.getDistrictName()+"Recovered\" name=\""+district.getDistrictName()+"Recovered\" value="+district.getRecovered()+"></td>");
		out.print("</tr>");

	}
		out.print("</tbody>");
		out.print("</table>");
		out.print("<div align=\"center\">");
		out.print("<input type=\"hidden\" id=\"stateId\" name=\"stateId\" value=\""+id+"\"/>");
		out.print("<input type=\"submit\" class=\"btn btn-primary mb-2\"/>");
		out.print("</div>");
		out.print("</form>");
	%>

<jsp:include page="adminFooter.jsp"/>