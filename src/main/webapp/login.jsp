<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login: Naggaro</title>
<link type="text/css" rel="stylesheet" href="css/login.css" />
</head>
<body>
	<div class="wrapper">
		<form action="/login" class="login">
			<p class="title">Log in</p>

			<input type="text" name = "username" placeholder="Username" autofocus /> <i class="fa fa-user"></i> 
			<input type="password" name = "password" placeholder="Password" /> <i class="fa fa-key"></i> 
			
			<button type="submit" >
				<i class="spinner"></i> <span class="state">Log in</span>
			</button>
		</form>
		<footer>
			<a target="blank" href="http://boudra.me/">boudra.me</a>
		</footer>
		</p>
	</div>
<script src="js/login.js"></script>
</body>
</html>