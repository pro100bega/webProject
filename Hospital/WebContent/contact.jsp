<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="elements/index/e_localeMessages.jsp"%>

<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/starter-template.css">
<link rel="stylesheet" href="css/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/signin.css">

<script src="scripts/jquery-3.1.1.js"></script>
<script src="scripts/jquery.validate.min.js"></script>
<script src="scripts/validation.js"></script>
<script src="css/bootstrap/js/bootstrap.js"></script>

<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">

<title><c:out value="${contactTitle}"></c:out></title>
</head>
<body>

	<%@ include file="elements/index/e_contactNavbar.jsp"%>
	
	<div class="container">
		<div class="jumbotron">
			<h2><c:out value="${contactInformationHeading}"></c:out></h2>
			<address>
				<strong><c:out value="${phoneNumberHeading}"></c:out></strong> <br>
				+375(25)786-25-99
			</address>
			<address>
				<strong><c:out value="${emailHeading}"></c:out></strong> <br>
				<a>pro100bega@gmail.com</a>
			</address>
			<address>
				<strong><c:out value="${vkHeading}"></c:out></strong> <br>
				<a href="https://vk.com/pro100bega">vk.com/pro100bega</a>
			</address>
			<address>
				<strong><c:out value="${skypeHeading}"></c:out></strong> <br>
				<a>imabad13</a>
			</address>
		</div>
	</div>

</body>
</html>