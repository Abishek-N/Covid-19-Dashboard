<jsp:include page="header.jsp"/>

<% 
	if(request.getSession()==null || (String)request.getSession().getAttribute("username") == null){
	String url=(String)request.getContextPath()+"/SiteController?page=index";
	response.sendRedirect(url);
	}

%>

<% 
out.print("<br/>");
out.print("<div class=\"container\">");
out.print("<form action=\""+request.getContextPath()+"/Operation?action=applyEpass\" method=\"POST\" > ");
out.print("<label for=\"inputName\" >Name:</label>");
out.print("<input type=\"text\" class=\"form-control\" id=\"inputName\" name=\"inputName\"  />");
out.print("<label for=\"inputAge\" class=\"col-sm-2 col-form-label\">Age:</label>");
out.print("<input type=\"number\" class=\"form-control\" id=\"inputAge\" name=\"inputAge\" />");
out.print("<label for=\"inputEmail\" class=\"col-sm-2 col-form-label\">Email:</label>");
out.print("<input type=\"email\" class=\"form-control\" id=\"inputEmail\" name=\"inputEmail\" />");
out.print("<label for=\"inputAddress\" class=\"col-sm-2 col-form-label\">Address:</label>");
out.print("<textarea  class=\"form-control\" id=\"inputAddress\" name=\"inputAddress\" rows=\"5\" ></textarea>");
out.print("<label for=\"inputAadhar\" class=\"col-sm-2 col-form-label\">Aadhar No.:</label>");
out.print("<input type=\"text\" class=\"form-control\" id=\"inputAadhar\" name=\"inputAadhar\" />");
out.print("<label for=\"inputPurpose\" class=\"col-sm-2 col-form-label\">Purpose:</label>");
out.print("<textarea  class=\"form-control\" id=\"inputPurpose\" name=\"inputPurpose\" rows=\"5\" ></textarea>");
out.print("<label for=\"inputFrom\" class=\"col-form-label\">From(In format of:District name-State abbreviation):</label>");
out.print("<input type=\"text\" class=\"form-control\" id=\"inputFrom\" name=\"inputFrom\" />");
out.print("<label for=\"inputTo\" class=\"col-form-label\">To(In format of:District name-State abbreviation):</label>");
out.print("<input type=\"text\" class=\"form-control\" id=\"inputTo\" name=\"inputTo\" />");
out.print("<br/>");
out.print("<div align=\"center\">");
out.print("<input type=\"submit\" value=\"APPLY\" class=\"btn btn-primary\" />");
out.print("</div>");
out.print("</form>");
out.print("</div>");
out.print("<br/>");
%>
<jsp:include page="footer.jsp"/>