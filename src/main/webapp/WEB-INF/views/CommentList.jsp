<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <div align="center">
            <h1>Comments List</h1>
            <h2><a href="new_comment">New Comment</a></h2>
             
            <table border="1">
                <th>ID</th>
                <th>Comment</th>
                
                <th>Actions</th>
                 
                <c:forEach var="comment" items="${commentList}" varStatus="status">
                <tr>
                    <%-- <td>${status.index + 1}</td> --%>
                    <td>${comment.id}</td>
                    <td>${comment.comment}</td>
                    
                    <td>
                        <a href="edit_comment?id=${comment.id}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete_comment?id=${comment.id}">Delete</a>
                    </td>
                </tr>
                </c:forEach>              
            </table>
        </div>
        
        
       
    </body>
   

</html>
 
    
    


