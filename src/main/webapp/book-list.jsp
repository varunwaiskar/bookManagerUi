<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.nagarro.a5.entity.*"%>
<%@page import="com.nagarro.a5.dao.*"%>

<%@page import="java.util.List"%>
<html>
<head>
<title>Book Listing</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style1.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/add-customer-style.css">
</head>
<body >
<h4 align="right" > <a href="/" >Logout</a></h4>
	<h2>Book Listing</h2>
	<div class="card-body"></div> 
	<div id="container">

		<div id="content" >
			<table  style="border : black thick;">
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Date</th>
					<th>Author</th>
					<th>Date</th>
					<th>Action</th>
				</tr>


				<c:forEach var="book" items="${books}">
					<tr>
					<tr>
						<td>${book.id}</td>
						<td> ${book.name} </td>
						<td> ${book.date} </td>
						<td> ${book.author.name} </td>
						<td> ${book.date} </td>
						<td>
							<a 
								href="${pageContext.request.contextPath}/book/delete/${book.id}"
							   onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
							   
							<a href="${pageContext.request.contextPath}/book/showFormForUpdate/${book.id}">Edit</a>
						</td>
					</tr>
					</tr>
					
				</c:forEach>
			</table>
			<h4>
				New Book Register <a href="${pageContext.request.contextPath}/book/formForNewBook">here</a>
			</h4>
			</div>
	</div>
				

</body>
</html>
