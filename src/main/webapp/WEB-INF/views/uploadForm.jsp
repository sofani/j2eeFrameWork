<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
 
<title>Being Java Guys | Hello World</title>
</head>
<body>

	
		<h2>Being Java Guys | Hello World</h2>
		<h3>Please select a file to upload !</h3>
		<br />
		<form:form method="POST" modelAttribute="uploadedFile" action="fileUpload?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
		
			<table>
				<tr>
					<td>Upload File:&nbsp;</td>
					<td><input type="file" name="file" />
					</td>
					<td style="color: red; font-style: italic;">
					<form:errors path="file" />
					</td> 
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Upload" />
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</form:form>
	
</body>
</html>
