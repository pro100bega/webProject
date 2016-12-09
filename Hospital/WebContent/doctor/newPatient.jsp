<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<fmt:setLocale value="${sessionScope.localeName}" />
<fmt:setBundle basename="resources.localization" var="local" />

<fmt:message bundle="${local}" key="title.newPatient" var="newPatientTitle" />
<fmt:message bundle="${local}" key="heading.newPatient" var="newPatientHeading" />
<fmt:message bundle="${local}" key="error.newPatient" var="newPatientError" />
<fmt:message bundle="${local}" key="button.addNewPatient" var="addNewPatientButton" />
<fmt:message bundle="${local}" key="placeholder.name" var="namePlaceholder" />
<fmt:message bundle="${local}" key="placeholder.surname" var="surnamePlaceholder" />
<fmt:message bundle="${local}" key="placeholder.diagnosis" var="diagnosisPlaceholder" />
<fmt:message bundle="${local}" key="heading.name" var="nameHeading" />
<fmt:message bundle="${local}" key="heading.surname" var="surnameHeading" />
<fmt:message bundle="${local}" key="heading.diagnosis" var="diagnosisHeading" />
<fmt:message bundle="${local}" key="button.cancel" var="cancelButton" />

<link rel="stylesheet" href="../css/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="../css/signin.css">

<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon">
<title><c:out value="${newPatientTitle}"></c:out></title>
</head>
<body>
	<div class="container">
		<form class="form-signin" action="../controller" method="post">
			<c:if test="${requestScope.error eq null}">
				<h2 class="form-signin-heading">
					<c:out value="${newPatientHeading}"></c:out>
				</h2>
			</c:if>
			<c:if test="${requestScope.error eq true}">
				<div class="alert alert-danger">
					<strong><c:out value="${newPatientError}"></c:out></strong>
				</div>
			</c:if>
			<input type="hidden" name="command" value="ADD_NEW_PATIENT">
			<h4><c:out value="${nameHeading}:"></c:out></h4>
			<input type="text" name="name" placeholder="${namePlaceholder}" 
				class="form-control">
			<h4><c:out value="${surnameHeading}:"></c:out></h4>
			<input type="text" name="surname" placeholder="${surnamePlaceholder}"
				class="form-control">
			<h4><c:out value="${diagnosisHeading}:"></c:out></h4>
			<input type="text" name="diagnosis" placeholder="${diagnosisPlaceholder}"
				class="form-control">
			<button type="submit" class="btn btn-lg btn-success btn-block">
				<c:out value="${addNewPatientButton}"></c:out>
			</button>
		</form>
		<form class="form-signin" action="redirection.jsp" method="get">
			<button type="submit" class="btn btn-lg btn-warning btn-block">
				<c:out value="${cancelButton}"></c:out>
			</button>
		</form>
	</div>
</body>
</html>