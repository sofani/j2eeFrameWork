<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Login Page</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-2.1.4.js" />"></script>
 <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body onload='document.form.username.focus();'>

<jsp:include page="menu.jsp"/>
	
	<c:if test="${not empty error}">
			<div class="alert alert-danger" style="width:300px; margin:0 auto;">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="alert alert-success" style="width:300px; margin:0 auto;">${msg}</div>
		</c:if>

		
<div class="container" style="width:300px; margin:0 auto;">

      <form class="form-signin" action="<c:url value='j_spring_security_check' />" method='POST'>
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="username" class="sr-only">Email address</label>
        <input type="text" id="username" name='username' class="form-control" placeholder="Email address" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name='password' class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
      </form>

    </div> <!-- /container -->

</body>
</html>