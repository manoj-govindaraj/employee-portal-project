<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<style>
div.a {
    color: green;
}
</style>
<head>
<title>Employee Portal - Upload</title>
</head>
<body  background="<c:url value='/resources/images/index.jpg'/>">
	<br> <br>
	<div style="text-align: center">
		<h2>
			<font color="white">Upload employee details</font>
		</h2>
			<h5> <i> <font color="green">(Contact admin for a sample sheet) </font></i> </h5>
			
			<br> <br>
		
		<form method="POST" action="upload-employee-details.do" enctype="multipart/form-data">
			<input type="file" name="file"> 
			<input type="submit" value="Upload">
		</form>	
		<c:if test="${not empty message}">
			<h4> <i> <font color="red">${message} </font> </i> </h4>
    	</c:if>
    	<div class=a><h5> <i>Disclaimer: This is a one time activity. Uploading same sheet again creates another entry for the same employee with different employee id.</i> </h5></div>
	</div>
</body>
</html>
