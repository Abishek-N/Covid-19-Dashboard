<jsp:include page="adminHeader.jsp"/>

<% 

out.print("<br/>");
out.print("<div class=\"container\">");
out.print("<form action=\""+request.getContextPath()+"/Operation?action=addData\" method=\"POST\" > ");
out.print("<label for=\"newState\" class=\"col-sm-2 col-form-label\">New State</label>");
out.print("<input type=\"text\" class=\"form-control\" id=\"newState\" name=\"newState\" placeholder=\"Name of new State\" >");
out.print("<label for=\"newId\" class=\"col-sm-2 col-form-label\">New State Code</label>");
out.print("<input type=\"text\" class=\"form-control\" id=\"newId\" name=\"newId\" placeholder=\"Code of new State\" >");
out.print("<label for=\"noState\" class=\"col-sm-2 col-form-label\">Number of states</label>");
out.print("<input type=\"number\"  class=\"form-control\" id=\"noState\" name=\"noState\" >");
out.print("<br/>");
out.print("<input type=\"submit\" value =\"SUBMIT\"class=\"btn btn-primary\" >");
out.print("</form>");
out.print("</div>");

%>

<jsp:include page="adminFooter.jsp"/>