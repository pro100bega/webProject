<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<fmt:setLocale value="${sessionScope.localeName}"/>
<fmt:setBundle basename="resources.localization" var="local"/>
<fmt:message bundle="${local}" key="title.signUp" var="signUpTitle"/>
<fmt:message bundle="${local}" key="heading.signUp" var="signUpHeading"/>
<fmt:message bundle="${local}" key="placeholder.username" var="usernamePlaceholder"/>
<fmt:message bundle="${local}" key="placeholder.password" var="passwordPlaceholder"/>
<fmt:message bundle="${local}" key="button.signUp" var="signUpButton"/>

<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/signin.css">

<title><c:out value="${signUpTitle}"></c:out> </title>
</head>
<body>
	<div class = "container">
	<form action="controller" class="form-signin" method = "post">
		<h2 class="form-signin-heading"><c:out value="${signUpHeading}"></c:out> </h2>	
		<input type = "hidden" name = "command" value = "SIGN_UP">
		<input type="text" class="form-control" name="username"
		 			placeholder="${usernamePlaceholder}" required pattern="[0-9a-zA-Z_]{6,20}">
		<input type = "password" class="form-control" name = "password"
					placeholder="${passwordPlaceholder}" required pattern="[0-9a-zA-Z]{6,20}">
		<input type="submit" class="btn btn-lg btn-primary btn-block" value = "${signUpButton}">
	</form>
	</div>
</body>
</html>