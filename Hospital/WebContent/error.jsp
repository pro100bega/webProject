<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<%@ include file="elements/index/e_localeMessages.jsp" %>

<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/starter-template.css">
<link rel="stylesheet" href="css/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/signin.css">

<script src="scripts/jquery-3.1.1.js"></script>
<script src="css/bootstrap/js/bootstrap.js"></script>

<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<title><c:out value="${errorTitle}"></c:out></title>
</head>
<body>
	
	<%@ include file="elements/index/e_navbar.jsp" %>

	<div class="container">
		<div class="starter-template">
			<h1><c:out value="${errorHeading}"></c:out></h1>
			<div class="alert alert-danger">
				<strong><c:out value="${errorMessage}"></c:out></strong>
			</div>
		</div>

	</div>
</body>
</html>