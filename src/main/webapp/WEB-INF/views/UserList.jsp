<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 
<title>Home</title>
</head>
<body>
	<table>
		<c:forEach var="user" items="${userList}" varStatus="status">
			<tr>
				<td><h2>User</h2></td>
			</tr>
			<tr>
				<td>ID:</td>
				<td>${user.id}</td>
			</tr>
			<tr>
				<td>UserName:</td>
				<td>${user.username}</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td>${user.password}</td>
			</tr>			
			<tr>
				<td>Enabled:</td>
				<td>${user.enabled}</td>
			</tr>
			<tr>
			<td>&nbsp; <a href="delete_user?id=${user.id}">Delete
						User</a></td>
				<td>&nbsp;<a href="edit_user?id=${user.id}">Edit
						User</a></td>
			</tr>
			<tr>
				<td><h2>Authority</h2></td>
			</tr>

			<c:if test="${!empty user.authorities}">

				<c:forEach var="authority" items="${user.authorities}" varStatus="status" >
					
					<tr>
						<td>Authority:</td>
						<td>${authority.authority}</td>
					</tr>					
					<tr>
						<td><a
							href="edit_authority?aid=${authority.id}&cid=${authority.id}">Edit
								Authority</a> &nbsp; <a href="delete_authority?id=${authority.id}">Delete
								Authority</a></td>
					</tr>			
				</c:forEach>
				
			</c:if>
			<tr>
				<td><a href="new_authority?id=${user.id}">New Authority</a></td>
              
			</tr>
       
		</c:forEach>
		
	</table>	
      <a href="new_user">New User</a><br/>
      <script type="text/javascript">
   
    $(document).ready(function() {
      
      $('#myForm').submit(function(event) {    	  
    	  	  
        $.ajax({
        	url: $("#myForm").attr( "action"),
        	contentType: "application/json",
        	data: 'name=' + $("#name").val(),
        	type: "POST",      	
        	
        	
        	success: function(smartphone) {
        		var respContent = smartphone.name;
        		$("#res").html(respContent);   		
        	}
        });
         
        event.preventDefault();
      });
       
    }); 
    
  </script>
      <div id="res"></div>

<form id="myForm" action="${pageContext.request.contextPath}/index" >

Name :<input type="text" id="name" /> <p> 
<input type="submit" value="Create" />

</form>
 
 
     
</body>
</html>





