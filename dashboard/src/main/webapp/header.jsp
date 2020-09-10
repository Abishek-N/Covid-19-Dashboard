<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="ISO-8859-1">
<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setDateHeader("Expires",-1);
	response.setHeader("Pragma","no-cache");


%>

<script src="jquery-3.5.1.min.js"></script>
  

<script type='text/javascript'>

(function()
{
  if( window.localStorage )
  {
    if( !localStorage.getItem( 'firstLoad' ) )
    {
      localStorage[ 'firstLoad' ] = true;
      window.location.reload();
    }  

    else
      localStorage.removeItem( 'firstLoad' );
  }
})();

</script>





<title>Covid-19 Dashboard</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="/docs/4.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

</head>

<body>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
<% 
out.print("<a class=\"navbar-brand\" href=\""+request.getContextPath()+"/SiteController?page=index\">Covid-19 Dashboard</a>");
%>
<c:set var="sesn" scope="application" value="<%=((String)request.getSession().getAttribute(\"username\"))%>"/>
<c:choose>
<c:when test="${sesn!= null}">
<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="<%=request.getContextPath()%>/SiteController?page=applyEpass">E-pass <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="<%=request.getContextPath()%>/SiteController?page=reportPage">Report</a>
      </li>
      </ul>
 
<form class="form-inline my-2 my-lg-0" action="<%request.getContextPath();%>/dashboard/Operation?action=logout" method="POST">
<button class="btn btn-outline-success" type="submit">Logout</button>
</form>
</div>
</c:when>
<c:otherwise>
<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
              <a class="nav-link" href="#"> <span class="sr-only">(current)</span></a>
      </li>
      </ul>
<form class="form-inline my-2 my-lg-0">
<a href="<%request.getContextPath();%>/dashboard/SiteController?page=login"><button class="btn btn-outline-success" type="button">Login</button></a>
<h5 style="color:white"> &nbsp; or &nbsp; </h5>
<a href="<%request.getContextPath();%>/dashboard/SiteController?page=register"><button class="btn btn-outline-success" type="button">Register</button></a>
</form>
</div>
</c:otherwise>
</c:choose>
</nav>
<% 
out.print("<div class=\"container\">");
%>