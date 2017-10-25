<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New or Edit Comment</title>
</head>
<body>
	<h1>New/Edit Reply</h1>

<table>
	<form:form modelAttribute="reply" method="POST" action="save_reply?id=${reply.id}">
		<form:hidden path="id"/>

			<tr>
				<td>Comment Id:</td>
				<td><input type="text" value="${commentId}" disabled="true" />
			</tr>


			<tr>
				<td>Reply:</td>
				<td><form:input path="reply" /></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Save"></td>
			</tr>
			</form:form>
		</table>
</body>
</html>
