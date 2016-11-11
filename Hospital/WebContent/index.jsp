<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link rel="stylesheet" href="content/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="content/starter-template.css">
</head>
<body>

	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Hospital</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
					<li><a href="#contact">Contact</a></li>
				</ul>
				<c:if test="${sessionScope.authorisedUser eq null}">
					<form class="navbar-form navbar-right" action="signIn"
						method="post">
						<button type="submit" class="btn btn-lg btn-success">Sign
							in</button>
					</form>
					<form class="navbar-form navbar-right" action="book" method="post">
						<button type="submit" class="btn btn-lg btn-default">Registration</button>
					</form>
				</c:if>
				<c:if test="">
					<form action="" method="post"></form>
				</c:if>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>

	<div class="container">

		<div class="starter-template">
			<h1>Welcome to Hospital page!</h1>
			<p class="lead">Use this document as a way to quickly start any
				new project. All you get is this text and a mostly barebones HTML
				document.</p>
		</div>

	</div>
	<!-- /.container -->
</body>
</html>