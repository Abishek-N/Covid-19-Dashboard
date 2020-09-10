<jsp:include page="/header.jsp"/>

	<br/>
	<%@ page import="maven.hibernate.DAO.DistrictTableDAO" %>
	<%@ page import="maven.hibernate.entities.DistrictTable" %>

 	<%
 	
 	String id=(String)request.getAttribute("districtId");
	DistrictTable district = new DistrictTableDAO().getDistrictData(id);
 	String name=(String)request.getAttribute("districtName");
 	out.print("<input type=\"hidden\" id=\"districtName\" value=\""+name+"\">");

 	out.print("<input type=\"hidden\" id=\"activeId\" value=\""+district.getActive()+"\">");
 	out.print("<input type=\"hidden\" id=\"confirmedId\" value=\""+district.getConfirmed()+"\">");
 	out.print("<input type=\"hidden\" id=\"deathId\" value=\""+district.getDeath()+"\">");
 	out.print("<input type=\"hidden\" id=\"recoveredId\" value=\""+district.getRecovered()+"\">");
 	%>
	<script>

window.onload = function () {

var chart = new CanvasJS.Chart("chartContainer", {
	animationEnabled: true,
	theme: "light2", // "light1", "light2", "dark1", "dark2"
	title:{
		text: document.getElementById("districtName").value
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
		
 		out.print("<div class=\"col-xl-12\">");
 		out.print("<div class=\"row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative\">");
 		out.print("<div class=\"col p-4 d-flex flex-column position-static\">");
 		out.print("<h3 class=\"mb-0\" style=\"color : black\" align=\"center\">"+district.getDistrictName()+"</h3>");
 		out.print("<h5 style=\"color:orange\" align=\"center\">Active:"+district.getActive()+"</h3>");
 		out.print("<h5 style=\"color:blue\" align=\"center\">Confirmed:"+district.getConfirmed()+"</h3>");
 		out.print("<h5 style=\"color:red\" align=\"center\">Death:"+district.getDeath()+"</h3>");
 		out.print("<h5 style=\"color:green\" align=\"center\">Recovered:"+district.getRecovered()+"</h3>");
 		out.print("</div>");
 		out.print("</div>");
 		out.print("</div>");

 	%>

<jsp:include page="/footer.jsp"/>