<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html >
  <head>
    <meta charset="ISO-8859-1">
<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setDateHeader("Expires",0);
	response.setHeader("Pragma","no-cache");
	
	String username=(String)request.getSession().getAttribute("username");
	
	if(username!=null){
		request.getRequestDispatcher("index.jsp").forward(request,response);
	}

%>

    <title>Login</title>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />



    <!-- Bootstrap core CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" rel="stylesheet">
    
    
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.slim.min.js"></script>
    
    <script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
  </head>



<body style="background-color: #1B262C">

  <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center">You have successfully registered!</h5>
            <form class="form-signin" action="<%request.getContextPath();%>/dashboard/Operation?action=success" method="POST">

              <br/>
              <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Log In</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>