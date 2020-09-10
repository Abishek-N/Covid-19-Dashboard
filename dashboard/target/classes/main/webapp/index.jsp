<jsp:include page="header.jsp"/>
	<br/>
	<%@ page import="java.util.List" %>
	<%@ page import="maven.hibernate.DAO.StateDataDAO" %>
	<%@ page import="maven.hibernate.entities.StateData" %>
	
	
<% 
	if((String)request.getSession().getAttribute("username") != null){
	out.print("<br/>");
	out.print("<h1>Welcome "+(String)request.getSession().getAttribute("username")+"!</h1>");
	out.print("<br/>");
	}

%>


 	<%
	List<StateData> states = new StateDataDAO().getStates();
 	int totalActive=0;
 	int totalConfirmed=0;
 	int totalDeath=0;
 	int totalRecovered=0;
 	for(StateData state : states){
 		totalActive+=state.getActive();
 		totalConfirmed+=state.getConfirmed();
 		totalDeath+=state.getDeath();
 		totalRecovered+=state.getRecovered();
 	}

 	out.print("<input type=\"hidden\" id=\"activeId\" value=\""+totalActive+"\">");
 	out.print("<input type=\"hidden\" id=\"confirmedId\" value=\""+totalConfirmed+"\">");
 	out.print("<input type=\"hidden\" id=\"deathId\" value=\""+totalDeath+"\">");
 	out.print("<input type=\"hidden\" id=\"recoveredId\" value=\""+totalRecovered+"\">");
 	%>
 	<script>

window.onload = function () {
	


var chart = new CanvasJS.Chart("chartContainer", {
	
	animationEnabled: true,
	theme: "light2", // "light1", "light2", "dark1", "dark2"
	title:{
		text: "States"
	},
	axisY: {
		title: "Numbers"
	},
	data: [{        
		type: "column",  
		showInLegend: true, 
		legendMarkerColor: "grey",
		legendText: "No. of people",
		dataPoints: [      
			{ y: Number(document.getElementById("activeId").value), label: "Active" },
			{ y: Number(document.getElementById("confirmedId").value), label: "Confirmed" },
			{ y: Number(document.getElementById("deathId").value), label: "Death" },
			{ y: Number(document.getElementById("recoveredId").value), label: "Recovered" },

		]
	}]
});
chart.render();

}
</script>

<div id="chartContainer" style="height: 600px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>




 	<% 
 	out.print("<br/>");
 	out.print("<div class=\"container\">");
 	
 	out.print("<br/>");

	out.print("<table class=\"table\">");
	out.print("<thead>");
	out.print("<tr class=\"table-active\">");
	out.print("<th scope=\"col\">State Name</th>");
	out.print("<th scope=\"col\">Active</th>");
	out.print("<th scope=\"col\">Confirmed</th>");
	out.print("<th scope=\"col\">Death</th>");
	out.print("<th scope=\"col\">Recovered</th>");
	out.print("</tr>");
	out.print("</thead");
	out.print("<tbody>");
 	
 	for(StateData state : states){
 		out.print("<tr class=\"table-active\" onclick=\"window.location='"+request.getContextPath()+"/SiteController?page=state&id="+state.getStateCode()+"';\" style='cursor: pointer;'>");
 		out.print("<th scope=\"col\">"+state.getStateName()+"</th>");
 		out.print("<td scope=\"col\">"+state.getActive()+"</td>");
 		out.print("<td scope=\"col\">"+state.getConfirmed()+"</td>");
 		out.print("<td scope=\"col\">"+state.getDeath()+"</td>");
 		out.print("<td scope=\"col\">"+state.getRecovered()+"</td>");
 		out.print("</tr>");

 	}
 	
 	
		out.print("</tbody>");
		out.print("</table>");
		
		out.print("</div>");
	%> 



<jsp:include page="footer.jsp"/>
