<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>

<html>

<head>
<title>Book Registration Form</title>
</head>

<body>

	<form:form action="/book/register" modelAttribute="book">
	
	 <input type="hidden" id="id" name="id" value="${id}">
		
	
		Book Name : <form:input path="name" />

		<br>
		<br>
	
		Date : <%=(new java.util.Date()).toLocaleString()%>

		<br>
		<br>

		Author :
		
		<form:select path="author.id">
			<form:options items="${authorMap}" />
		</form:select>

		<br>
		<br>

		<input type="submit" value="Submit" />

	</form:form>


</body>

</html>












