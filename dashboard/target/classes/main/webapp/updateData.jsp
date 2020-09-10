<jsp:include page="adminHeader.jsp"/>
	<br/>
	<%@ page import="java.util.List" %>
	<%@ page import="maven.hibernate.DAO.StateDataDAO" %>
	<%@ page import="maven.hibernate.entities.StateData" %>

<h3>Select State</h3>
 	<%
	List<StateData> states = new StateDataDAO().getStates();
 	for(StateData state:states){
 		out.print("<a href=\""+request.getContextPath()+"/SiteController?page=updateData&stateId="+state.getStateCode()+"\">");
 		out.print("<div class=\"col-xl-12\">");
 		out.print("<div class=\"row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative\">");
 		out.print("<div class=\"col p-4 d-flex flex-column position-static\">");
 		out.print("<h3 class=\"mb-0\" style=\"color : black\" align=\"center\">"+state.getStateName()+"</h3>");
 		out.print("</div>");
 		out.print("</div>");
 		out.print("</div>");
 		out.print("</a>");
 	}
 	
 	%>
<jsp:include page="adminFooter.jsp"/>