<jsp:include page="adminHeader.jsp"/>
<br/>
<br/>
<h1>Welcome to Admin Page!</h1>
<br/>
<br/>
	<%@ page import="java.util.List" %>
	<%@ page import="maven.hibernate.DAO.StateDataDAO" %>
	<%@ page import="maven.hibernate.entities.StateData" %>
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
<jsp:include page="adminFooter.jsp"/>