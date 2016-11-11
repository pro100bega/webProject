<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="content/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="content/signin.css">

<title>Book user</title>
</head>
<body>
	<div class = "container">
	<form action="controller" class="form-signin" method = "post">
		<h2 class="form-signin-heading">Registration</h2>	
		<input type = "hidden" name = "command" value = "BOOK_USER">
		<input type="text" class="form-control" name="username"
		 			placeholder="New username" required>
		<input type = "password" class="form-control" name = "password"
					placeholder="New password" required>
		<input type="submit" class="btn btn-lg btn-primary btn-block" value = "Book">
	</form>
	</div>
</body>
</html>