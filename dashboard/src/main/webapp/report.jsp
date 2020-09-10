<jsp:include page="header.jsp" />

<% 
	if(request.getSession()==null || (String)request.getSession().getAttribute("username") == null){
	String url=(String)request.getContextPath()+"/SiteController?page=index";
	response.sendRedirect(url);
	}

%>

<br/>
<h3>Download Report:</h3>
<br/>
<%
out.print("<div= \"container\" align=\"center\">");
out.print("<div class=\"d-inline col-xl-4\">");
out.print("<a href=\""+request.getContextPath()+"/SiteController?page=report&type=pdf\"><button class=\"btn btn-outline-success\" type=\"button\">Download Report(.pdf)</button></a>");
out.print("</div>");
out.print("<div class=\"d-inline col-xl-4\">");
out.print("<a href=\""+request.getContextPath()+"/SiteController?page=report&type=text\"><button class=\"btn btn-outline-success\" type=\"button\">Download Report(.txt)</button></a>");
out.print("</div>");
out.print("<div class=\"d-inline col-xl-4\">");
out.print("<a href=\""+request.getContextPath()+"/SiteController?page=report&type=word\"><button class=\"btn btn-outline-success\" type=\"button\">Download Report(.docx)</button></a>");
out.print("</div>");
out.print("</div>");
out.print("<br/>");
%>
<br/>
<div class="container">
<h3>Send Report to Email:</h3>
<br/>
<% 
out.print("<br/>");
out.print("<form action=\""+request.getContextPath()+"/Operation?action=sendReportMail\" method=\"POST\" > ");
out.print("<label for=\"inputEmail\" >Email:</label>");
out.print("<input type=\"text\" class=\"form-control\" id=\"inputEmail\" name=\"inputEmail\"  />");
out.print("<br/>");
out.print("<div align=\"center\">");
out.print("<input type=\"submit\" value=\"Send Report\" class=\"btn btn-primary\" />");
out.print("</div>");
out.print("</form>");
out.print("<br/>");
%>
</div>
<br/>
<div class="container" >

<h3>Share Report:</h3>
<br/>
<%
String path="https://"+(String)request.getContextPath();

out.print("<div class=\"d-inline col-xl-4\">");
out.print("<a href=\"http://www.facebook.com/share.php?u="+path+"/SiteController?page=report&type=pdf"+"\" target=\"_blank\"><button class=\"btn btn-outline-success\" type=\"button\">Facebook</button></a>");
out.print("</div>");
out.print("<div class=\"d-inline col-xl-4\">");
out.print("<a href=\"https://twitter.com/share?url="+path+"/SiteController?page=report&type=pdf"+"&amp;text=Covid19Report&amp;hashtags=Covid19Report\" target=\"_blank\"><button class=\"btn btn-outline-success\" type=\"button\">Twitter</button></a>");
out.print("</div>");
out.print("<div class=\"d-inline col-xl-4\">");
out.print("<a href=\"https://web.whatsapp.com/send?text="+path+"/SiteController?page=report&type=pdf"+"\" target=\"_blank\"><button class=\"btn btn-outline-success\" type=\"button\">Whatsapp</button></a>");
out.print("</div>");

%>
<br/>
<br/>
</div>

<jsp:include page="footer.jsp" />