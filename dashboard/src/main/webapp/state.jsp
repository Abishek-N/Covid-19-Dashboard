<jsp:include page="header.jsp" />

	<br/>
	<%@ page import="java.util.List" %>
	<%@ page import="maven.hibernate.DAO.DistrictTableDAO" %>
	<%@ page import="maven.hibernate.entities.DistrictTable" %>
	<%@ page import="maven.hibernate.DAO.StateDataDAO" %>

 	<%
 	String id=(String)request.getAttribute("stateId");
	List<DistrictTable> districts = new DistrictTableDAO().getDistricts(id);
 	int totalActive=0;
 	int totalConfirmed=0;
 	int totalDeath=0;
 	int totalRecovered=0;
 	for(DistrictTable district : districts){
 		totalActive+=district.getActive();
 		totalConfirmed+=district.getConfirmed();
 		totalDeath+=district.getDeath();
 		totalRecovered+=district.getRecovered();
 	}
 	String name=new StateDataDAO().getStateName(id);
 	out.print("<input type=\"hidden\" id=\"stateName\" value=\""+name+"\">");

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
		text: document.getElementById("stateName").value
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
 		out.print("<tr class=\"table-active\" onclick=\"window.location='"+request.getContextPath()+"/SiteController?page=district&id="+district.getDistrictId()+"';\" style='cursor: pointer;'>");
 		out.print("<th scope=\"col\">"+district.getDistrictName()+"</th>");
 		out.print("<td scope=\"col\">"+district.getActive()+"</td>");
 		out.print("<td scope=\"col\">"+district.getConfirmed()+"</td>");
 		out.print("<td scope=\"col\">"+district.getDeath()+"</td>");
 		out.print("<td scope=\"col\">"+district.getRecovered()+"</td>");
 		out.print("</tr>");

 	}
 		out.print("</tbody>");
 		out.print("</table>");
 	%>
 	<!-- 
 	<table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">First</th>
      <th scope="col">Last</th>
      <th scope="col">Handle</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
 	
 	 -->

<jsp:include page="footer.jsp" />