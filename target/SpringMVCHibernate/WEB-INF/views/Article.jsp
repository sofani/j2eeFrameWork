<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
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
<body>
<jsp:include page="menu.jsp"/>
    <div class="container">	
	 
	<h2> ${article.title}</h2>	<br />
	<p><strong> Posted by:</strong> ${article.username}        
         <strong>on:</strong> <fmt:formatDate type="both" value="${article.date}" /></p>
	<img src="<c:url value="/resources/images/${article.username}.png" />" height="120" width="100" /><br/> 
	
	 ${article.body}	<br />
	 <!-- <a href="download">Download file</a> -->
	 </div>
	 
	<div class="container">
	<c:if test="${fn:length(article.comments)>0}">
	<h3>Comments(${fn:length(article.comments)})</h3>
	</c:if>
	<c:if test="${fn:length(article.comments)< 1}">
	<h3>Comments(0)</h3>
	</c:if>
	<c:if test="${!empty article.comments}" >
	
	
		<c:forEach items="${article.comments}" var="comment">		
					
					<strong>Commented by:</strong>  ${comment.username} <strong>on:</strong>  <fmt:formatDate type="both" value="${comment.date}" /><br />
					${comment.comment}<br />
					
			 <c:if test="${!empty comment.reply}">
				<c:forEach items="${comment.reply}" var="reply">
					<div style="width:500px; padding-left: 1cm;">	
							
					<strong>Replied by:</strong> ${reply.username} <strong>on:</strong> <fmt:formatDate type="both" value="${reply.date}" /><br />				
					 ${reply.reply}<br />				
					</div>	
					
				</c:forEach>
			</c:if> 
			
			<button class="btn btn-default" onclick="toggleDiv('newReply${comment.id}')">Reply</button>
			<br/>
			<br/>
			
			<div id="newReply${comment.id}" class="form">
			<form:form class="form-horizontal" role="form"  method="POST" action="add_reply?cid=${comment.id}&aid=${article.id}" modelAttribute="reply">
  
  <form:hidden path="username" value="${pageContext.request.userPrincipal.name}"/>
    
    <div class="form-group">     
     <div class="col-sm-5">
      <form:textarea class="form-control" rows="5" id="reply" path="reply"  placeholder="Enter Reply"/>
    </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-0 col-sm-2">
        <button type="submit"  class="btn btn-default">Submit</button>
      </div>
    </div>
  </form:form>
		</div>	
		</c:forEach>
	</c:if>
	<br/>
	<button class="btn btn-default" onclick="toggleDiv('newComment')">Comment</button><br/>
	
	
	
	<div class ="form" id = "newComment">
		
		<div class="container">
  
  <form:form class="form-horizontal" role="form"  method="POST" action="add_comment?id=${article.id}" modelAttribute="comment">
  
  <form:hidden path="username" value="${pageContext.request.userPrincipal.name}"/>
    
    <div class="form-group">
     <label class="control-label col-sm-1" for="comment">Comment:</label>
     <div class="col-sm-5">
      <form:textarea class="form-control" rows="5" id ="comment" path="comment"  placeholder="Enter Comment"/>
    </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-1 col-sm-5">
        <button type="submit"  class="btn btn-primary">Submit</button>
      </div>
    </div>
  </form:form>
  
  
	</div>	

</div>
	</div>
</body>

</html>





