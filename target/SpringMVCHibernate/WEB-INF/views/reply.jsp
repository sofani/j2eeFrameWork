<html>
<head></head>
<body>
<h1> I am from test page.</h1>
<table>
<%-- $(function() {
		$('a.reply').click(
						function() {
							var comment_id = $(this).attr('id');
							alert(comment_id);
							$(this).after(
											'<br/>CommentID: <input type="text" value="" id="commentId"><br />'
													+ 'Reply: <input type="text" id="reply"><br />'
													+ 'Name:<input type="text" value="${pageContext.request.userPrincipal.name}" id="username"><br />'
													+ '<input type="button" value="Add Reply" onclick="doAjaxReply()"><br />')
													document.getElementById("commentId").value = comment_id;
							return false;
						});
	}); --%>
<tr>
						<td><h1>New/Edit Reply</h1></td>
					</tr>

					<tr>
						<td>Comment ID :</td>
						<td><input type="text" value="${comment.id}" id="commentId"><br /></td>
					</tr>
					<tr>
						<td>Reply :</td>
						<td><input type="text" id="reply"><br /></td>
					</tr>
					<tr>
						<td>Name :</td>
						<td><input type="text"
							value="${pageContext.request.userPrincipal.name}" id="username"><br /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="button" value="Add Reply"
							onclick="doAjaxReply()"><br /></td>
					</tr>
					<tr>
						<td colspan="2"><div id="infoReply" style="color: green;"></div></td>
					</tr>
					</table>
					
</body>
</html>
