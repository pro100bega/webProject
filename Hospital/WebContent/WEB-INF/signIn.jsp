<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign in</title>
</head>
<body>
	<form action="controller" method = "post">
		<input type = "hidden" name = "command" value = "LOGIN_USER">
		<label>Enter username:</label>
		<br>
		<input type = "text" required name = "username" placaholder = "Name" >
		<br>
		<label>Enter password:</label>
		<br>
		<input type = "password" required name = "password">
		<br>
		<input type = "submit" value = "Log in">
	</form>
</body>
</html>