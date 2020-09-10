<jsp:include page="adminHeader.jsp" />

<%@ page import="maven.hibernate.entities.Epass" %>
<%@ page import="maven.hibernate.DAO.EpassDAO" %>

<div class="container" align="center" style="text-align:center">
<br/>
<br/>
<%

int epassId=Integer.parseInt((String)request.getAttribute("epassId"));

Epass epass = new EpassDAO().getPass(epassId);

out.print("<h3>Name : "+epass.getName()+"</h3>");
out.print("<br/>");
out.print("<h3>Age : "+epass.getAge()+"</h3>");
out.print("<br/>");
out.print("<h3>Email : "+epass.getEmail()+"</h3>");
out.print("<br/>");
out.print("<h3>Address : "+epass.getAddress()+"</h3>");
out.print("<br/>");
out.print("<h3>Aadhar No. : "+epass.getAadharNo()+"</h3>");
out.print("<br/>");
out.print("<h3>Purpose : "+epass.getPurpose()+"</h3>");
out.print("<br/>");
out.print("<h3>From : "+epass.getFrom()+"</h3>");
out.print("<br/>");
out.print("<h3>To : "+epass.getTo()+"</h3>");
out.print("<br/>");
out.print("<div class=\"container\" align=\"center\">");
out.print("<form action=\""+request.getContextPath()+"/Operation?action=ackEpass\" method=\"POST\">");
out.print("<input type=\"hidden\" id=\"approval\" name=\"approval\" value=\"accept\" />");
out.print("<input type=\"hidden\" id=\"epassId\" name=\"epassId\" value=\""+epass.getEpassId()+"\" />");
out.print("<input type=\"submit\" value=\"Accept\" class=\"btn btn-primary\" />");
out.print("</form>");
out.print("<br/>");
out.print("</div>");
out.print("<div class=\"container\" align=\"center\">");
out.print("<form action=\""+request.getContextPath()+"/Operation?action=ackEpass\" method=\"POST\">");
out.print("<input type=\"hidden\" id=\"approval\" name=\"approval\" value=\"reject\" />");
out.print("<input type=\"hidden\" id=\"epassId\" name=\"epassId\" value=\""+epass.getEpassId()+"\" />");
out.print("<input type=\"submit\" value=\"Reject\" class=\"btn btn-primary\" />");
out.print("</form>");
out.print("</div>");
%>
<br/>
<br/>

</div>

<jsp:include page="adminFooter.jsp" />