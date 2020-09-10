<jsp:include page="adminHeader.jsp"/>
<% 
out.print("<br/>");
out.print("<div class=\"container\">");
out.print("<form action=\""+request.getContextPath()+"/Operation?action=addAdmin\" method=\"POST\" > ");
out.print("<label for=\"inputUsername\" >Username:</label>");
out.print("<input type=\"text\" class=\"form-control\" id=\"inputUsername\" name=\"inputUsername\"  />");
out.print("<label for=\"inputPassword\" class=\"col-sm-2 col-form-label\">Password:</label>");
out.print("<input type=\"password\" class=\"form-control\" id=\"inputPassword\" name=\"inputPassword\" />");
out.print("<label for=\"inputEmail\" class=\"col-sm-2 col-form-label\">Email:</label>");
out.print("<input type=\"email\"  class=\"form-control\" id=\"inputEmail\" name=\"inputEmail\" />");
out.print("<br/>");
out.print("<input type=\"submit\" value=\"SUBMIT\" class=\"btn btn-primary\" />");
out.print("</form>");
out.print("</div>");
%>
<jsp:include page="adminFooter.jsp"/>