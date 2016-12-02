<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="../elements/doctor/e_localeMessages.jsp"%>

<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/starter-template.css">
<link rel="stylesheet" href="css/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="content/theme.css">

<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">

<title><c:out value="${doctorPanelTitle}"></c:out></title>
</head>
<body>

	<%@include file="../elements/doctor/e_navbar.jsp"%>
	<div class="container">
		<div class="row">
			<c:forEach var="patient" items="${sessionScope.foundPatients}">
				<div class="col-sm-4">
					<div class="panel panel-success">
						<div class="panel-heading">
							<h3 class="panel-title">
								<c:out value="${patientInfo} ${patient.id}">
								</c:out>
							</h3>
						</div>
						<div class="panel-body">
							<p>
								<c:out value="${nameMessage}: ${patient.name}" />
							</p>
							<p>
								<c:out value="${surnameMessage}: ${patient.surname}" />
							</p>
							<p>
								<c:out value="${diagnosisMessage}: ${patient.diagnosis}" />
							</p>
							<form action="controller" method="post">
								<input type="hidden" name="command" value="DISCHARGE_PATIENT">
								<input type="hidden" name="patientId" value="${patient.id}">
								<textarea name="finalDiagnosis" placeholder="${finalDiagnosis}"
									required rows="3" cols="30" maxlength=60></textarea>
								<br />
								<button type="submit" class="btn btn btn-danger">
									<c:out value="${dischargeButton}"></c:out>
								</button>
							</form>
							<form action="controller" method="get">
								<input type="hidden" name="command" value="UPDATE_PATIENT">
								<input type="hidden" name="patientId" value="${patient.id}">
								<button type="submit" class="btn btn btn-warning">
									<c:out value="${editButton}"></c:out>
								</button>
							</form>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>