<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>


	<table>

		<c:forEach var="article" items="${userArticles}" varStatus="status">
			<tr>
				<td><h2>Article</h2></td>
			</tr>
			<tr>
				<td>ID:</td>
				<td>${article.id}</td>
			</tr>
			<tr>
				<td>Title:</td>
				<td>${article.title}</td>
			</tr>
			<tr>
				<td>Body:</td>
				<td>${article.body}</td>
			</tr>
			
			<tr>

				<td>&nbsp; <a href="delete_article?id=${article.id}">Delete
						Article</a></td>
				<td>&nbsp;<a href="edit_article?id=${article.id}">Edit
						Article</a></td>
			</tr>
		</c:forEach>

	</table>
	<a href="new_article">New Article</a>




</body>


</html>





