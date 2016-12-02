<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="../elements/doctor/e_localeMessages.jsp"%>
<fmt:message bundle="${local}" key="error.discharge" var="dischargeError" />
<fmt:message bundle="${local}" key="heading.discharge" var="dischargeHeading" />
	
<link rel="stylesheet" href="../css/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/starter-template.css">
<link rel="stylesheet" href="../css/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="../content/theme.css">
<link rel="stylesheet" href="../css/signin.css">

<link rel="shortcut icon" href="hospital.ico" type="image/x-icon">
<title><c:out value="${doctorPanelTitle}" /></title>
</head>
<body>
	<%@include file="../elements/doctor/e_navbar.jsp"%>
	<div class="content">
		<form class="form-signin" action="../controller" method="post">
			<input type="hidden" name="command" value="DISCHARGE_PATIENT">
			<input type="hidden" name="patientId"
				value="${sessionScope.patientId}">
			<c:out value="${requestScope.patientId}"></c:out>
			<c:if test="${requestScope.error eq null}">
				<h2 class="form-signin-heading">
					<c:out value="${dischargeHeading}"></c:out>
				</h2>
			</c:if>
			<c:if test="${requestScope.error eq true}">
				<div class="alert alert-danger">
					<strong><c:out value="${dischargeError}"></c:out></strong>
				</div>
			</c:if>
			<p>
			<textarea rows="3" cols="40" name="finalDiagnosis"></textarea>
			</p>
			<button class="btn btn btn-danger" type="submit">
				<c:out value="${dischargeButton}"></c:out>
			</button>
		</form>
	</div>
</body>
</html>