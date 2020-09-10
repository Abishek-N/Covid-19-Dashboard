<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html >
  <head>
    <meta charset="ISO-8859-1">


    <title>Login</title>
    
<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setDateHeader("Expires",0);
	response.setHeader("Pragma","no-cache");
	
	String email=(String)request.getSession().getAttribute("adminEmail");
	
	if(email!=null){
		request.getRequestDispatcher("admin.jsp").forward(request,response);
	}

%>


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
            <h5 class="card-title text-center">Admin Login</h5>
            <form class="form-signin" action="<%request.getContextPath();%>/dashboard/Operation?action=adminlogin" method="POST">
              <div class="form-label-group">
                <label for="inputEmail">Email address</label>
                <input type="email" name="inputEmail" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
                
              </div>
              <br/>

              <div class="form-label-group">
                <label for="inputPassword">Password</label>
                <input type="password" name="inputPassword" id="inputPassword" class="form-control" placeholder="Password" required>
                
              </div>
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