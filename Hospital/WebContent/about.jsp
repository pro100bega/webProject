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

<title><c:out value="${aboutTitle}"></c:out></title>
</head>
<body>

	<%@ include file="elements/index/e_aboutNavbar.jsp"%>
	
	<div class="container">
		<div class="jumbotron">
			<h1>
				<c:out value="${aboutTitle}"></c:out>
			</h1>
			<p>
				<c:out value="${aboutMessage}"></c:out>
				&copy;
				<c:out value="${bugReportFirstMessage}"></c:out>
				<a href="bugReport.jsp"><c:out value="${linkMessage}"></c:out></a>
				<c:out value="${bugReportSecondMessage}"></c:out>
			</p>
		</div>
	</div>
</body>
</html>