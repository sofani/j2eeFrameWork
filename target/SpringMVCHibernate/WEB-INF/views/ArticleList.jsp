<!DOCTYPE html>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>

<title>Home</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-2.1.4.js" />"></script>
 <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script type="text/javascript">

	
	function toggleDiv(divId) {
		<sec:authorize access="isAnonymous()">
		window.location.replace("login");
        </sec:authorize>
		$("#" + divId).toggle('slow');
	}
	
</script>

</head>
</head>
<body>
<jsp:include page="menu.jsp"/>

      <!-- Example row of columns -->
      <div class="container">
      <div class="row">
      <c:forEach var="article" items="${articleList}" varStatus="status">
      
        <div class="col-md-4">
          <h2>${article.title}</h2>          
	
         <p><strong> Posted by:</strong> ${article.username}        
         <strong>on:</strong> <fmt:formatDate type="both" value="${article.date}" /></p>
          <%-- <img src="<c:url value="/resources/images/${article.username}.png" />" height="120" width="100" /> --%> 
          <p>${article.summary}  </p> 
          <p><img src="getImage/${article.id}" height="120" width="100"/>  </p>      
       
        <c:if test="${pageContext.request.userPrincipal.name == article.username}">
         &nbsp; <a class="btn btn-default" href="edit_article?id=${article.id}" role="button">Edit</a>
	<a class="btn btn-default" href="delete_article?id=${article.id}" role="button">Delete</a>
        </c:if>
        <c:if test="${pageContext.request.userPrincipal.name != article.username}">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
	<a class="btn btn-default" href="edit_article?id=${article.id}" role="button">Edit</a>
	<a class="btn btn-default" href="delete_article?id=${article.id}" role="button">Delete</a>
              
	</sec:authorize>
	</c:if>
	<a class="btn btn-default" href="article?id=${article.id}" role="button">View details &raquo;</a>
	</div>
		</c:forEach>
      </div>
     <br/>
    <button onclick="toggleDiv('newArticle')" class="btn btn-default">New Article</button>
	<div class ="form" id = "newArticle">		
		<div class="container">  
  <form:form class="form-horizontal" role="form" action="save_article?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" method="post" modelAttribute="article">
  <form:hidden path="id"/>
  <form:hidden path="username"  value="${pageContext.request.userPrincipal.name}" />
    <div class="form-group">
      <label class="control-label col-sm-1" for="title">Title:</label>
      <div class="col-sm-5">
        <form:input path="title" class="form-control" id="title" placeholder="Enter Title"/>
      </div>
    </div>
    <div class="form-group">
     <label class="control-label col-sm-1" for="summary">Summary:</label>
     <div class="col-sm-5">
      <form:textarea class="form-control" rows="5" maxlength="200" path="summary" placeholder="Enter summary"/>
    </div>
    </div>
    
    <div class="form-group">
     <label class="control-label col-sm-1" for="body">Body:</label>
     <div class="col-sm-5">
      <form:textarea class="form-control" rows="10" path="body" placeholder="Enter body"/>
    </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-1" for="file">Title:</label>
      <div class="col-sm-5">
        <input type="file" path="image" name="file" class="form-control" id="file" placeholder="Enter image"/>
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-5">
        <button type="submit"  class="btn btn-default">Submit</button>
      </div>
    </div>
  </form:form>
</div>
</div>
</div>
</body>
</html>