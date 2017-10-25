<!DOCTYPE html>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>

<title>New or Edit User</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-2.1.4.js" />"></script>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<div class="container">
		<h2>Registration form</h2>
		
		<form:form class="form-horizontal" role="form" action="save_user"
			method="post" modelAttribute="user">

			<div class="form-group">
				<label class="control-label col-sm-2" for="username">Name:</label>
				<div class="col-sm-5">
					<form:input type="text" path="username" class="form-control"
						id="username" placeholder="Enter your name" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Password:</label>
				<div class="col-sm-5">
					<form:input type="password" path="password" class="form-control"
						id="pwd" placeholder="Enter password" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-success">Submit</button>
				</div>
			</div>


		</form:form>

    
	
	</div>

</body>
</html>
