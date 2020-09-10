<jsp:include page="header.jsp" />

<% 
	if(request.getSession()==null || (String)request.getSession().getAttribute("username") == null){
	String url=(String)request.getContextPath()+"/SiteController?page=index";
	response.sendRedirect(url);
	}

%>

<br/>
<div class="container" align="center" style="text-align:center">
<br/>
<br/>
<h3>Your E-pass request has been successfully submitted.You will be notified about the status of the pass through the specified email.</h3>
<br/>
<br/>
</div>

<jsp:include page="footer.jsp" />