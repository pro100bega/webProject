<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<fmt:setLocale value="${sessionScope.localeName}" />
<fmt:setBundle basename="resources.localization" var="local" />
<fmt:message bundle="${local}" key="title.signUp" var="signUpTitle" />
<fmt:message bundle="${local}" key="placeholder.username"
	var="usernamePlaceholder" />
<fmt:message bundle="${local}" key="placeholder.password"
	var="passwordPlaceholder" />
<fmt:message bundle="${local}" key="placeholder.passwordConfirmation"
	var="passwordConfirmationPlaceholder" />
<fmt:message bundle="${local}" key="button.signUp" var="signUpButton" />

<c:if test="${requestScope.error eq null}">
	<fmt:message bundle="${local}" key="heading.signUp" var="signUpHeading" />
</c:if>
<c:if test="${requestScope.error eq true}">
	<fmt:message bundle="${local}" key="error.signUp" var="signUpError" />
</c:if>
<script src="scripts/jquery-3.1.1.js"></script>
<script src="scripts/jquery.validate.min.js"></script>
<script src="scripts/validation.js"></script>
<c:if test="${sessionScope.localeName eq null}">
	<script src="scripts/messages_ru.js"></script>
</c:if>
<c:if test="${sessionScope.localeName eq 'ru_RU'}">
	<script src="scripts/messages_ru.js"></script>
</c:if>

<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/signin.css">

<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<title><c:out value="${signUpTitle}"></c:out></title>
</head>
<body>
	<div class="container">
		<form action="controller" class="form-signin" method="post">
			<c:if test="${requestScope.error eq null}">
				<h2 class="form-signin-heading">
					<c:out value="${signUpHeading}"></c:out>
				</h2>
			</c:if>
			<c:if test="${requestScope.error eq true}">
				<div class="alert alert-danger">
					<strong><c:out value="${signUpError}"></c:out></strong>
				</div>
			</c:if>
			<input type="hidden" name="command" value="SIGN_UP"> <input
				type="hidden" name="userType" value="guest"> <label><c:out
					value="${usernameMessage}"></c:out></label> <input type="text"
				class="form-control" name="username"
				placeholder="${usernamePlaceholder}"> <label><c:out
					value="${passwordMessage}"></c:out></label> <input type="password"
				id="password" class="form-control" name="password"
				placeholder="${passwordPlaceholder}"> <label><c:out
					value="${confirmPasswordMessage}"></c:out></label> <input type="password"
				class="form-control" name="passwordConfirm"
				placeholder="${passwordConfirmationPlaceholder}"> <input
				type="submit" class="btn btn-lg btn-primary btn-block"
				value="${signUpButton}">
		</form>
	</div>
</body>
</html>