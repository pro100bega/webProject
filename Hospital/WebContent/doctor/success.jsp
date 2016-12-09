<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="css/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="css/starter-template.css">

<%@include file="../elements/doctor/e_localeMessages.jsp" %>

<fmt:message bundle="${local}" key="title.success" var="successTitle" />
<fmt:message bundle="${local}" key="heading.patientAddSuccess" var="successHeading" />
<fmt:message bundle="${local}" key="message.patientAddSuccess" var="successMessage" />

<title><c:out value="${successTitle}"></c:out></title>
</head>
<body>
	<%@include file="../elements/doctor/e_navbar.jsp" %>
	<div class="container">
		<div class="starter-template">
			<h1><c:out value="${successHeading}"></c:out></h1>
			<div class="alert alert-success">
				<strong><c:out value="${successMessage}"></c:out></strong>
			</div>
		</div>

	</div>
</body>
</html>