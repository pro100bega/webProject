<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ include file="elements/index/e_localeMessages.jsp" %>

<fmt:message bundle="${local}" key="success.sendReport" var="sendReportSuccess"/>

<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/starter-template.css">
<link rel="stylesheet" href="css/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/signin.css">

<script src="scripts/jquery-3.1.1.js"></script>
<script src="scripts/jquery.validate.min.js"></script>
<script src="scripts/validation.js"></script>
<script src="css/bootstrap/js/bootstrap.js"></script>

<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<title><c:out value="${successTitle}"></c:out></title>
</head>
<body>

	<c:set var="message" value="${sessionScope.successMessage}"></c:set>
	
	<%@ include file="elements/index/e_navbar.jsp" %>

	<div class="container">
		<div class="starter-template">
			<h1><c:out value="${successHeading}"></c:out></h1>
			<div class="alert alert-success">
				<c:choose>
					<c:when test="${message == 'sendReport'}">
						<c:set var="successMessage" value="${sendReportSuccess}"></c:set>
					</c:when>
				</c:choose>
				<strong><c:out value="${successMessage}"></c:out></strong>
			</div>
		</div>

	</div>
</body>
</html>