<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<%@ include file="../elements/doctor/e_localeMessages.jsp"%>
<script src="scripts/jquery-3.1.1.js"></script>
<script src="css/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	$(function() {

	});
</script>
<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/starter-template.css">
<link rel="stylesheet" href="css/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/dashboard.css">


<c:set var="selectedPatient" value="${requestScope.selectedPatient}"></c:set>
<c:set var="patientName" value="${selectedPatient.name}"></c:set>
<c:set var="patientSurname" value="${selectedPatient.surname}"></c:set>

<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<title><c:out value="${patientSurname} ${patientName}"></c:out></title>
</head>
<body>
	<%@ include file="../elements/doctor/e_patientNavbar.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar"></div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="col-sm-6">
					<div class="page-header">
						<h1>
							<c:out value="${patientEditHeading}"></c:out>
						</h1>
					</div>
					<form action="controller" method="post" id="editPatient">
						<input type="hidden" name="patientId" 
						value="${selectedPatient.id}">
						<input type="hidden" name="command" value="EDIT_PATIENT">
						<p>
							<label> <c:out value="${nameMessage}:"></c:out>
							</label> <input type="text" class="form-control" name="name"
								value="${selectedPatient.name}">
						</p>
						<p>
							<label> <c:out value="${surnameMessage}:"></c:out>
							</label> <input type="text" class="form-control" name="surname"
								value="${selectedPatient.surname}">
						</p>
						<p>
							<label> <c:out value="${sexMessage}:"></c:out>
							</label> <input type="radio" name="sex" value="м" checked>
							<c:out value="${maleMessage}"></c:out>
							<input type="radio" name="sex" value="ж">
							<c:out value="${femaleMessage}"></c:out>
						</p>
						<p>
							<label> <c:out value="${birthDateMessage}:"></c:out>
							</label> <input type="text" class="form-control" name="birthDate"
								value="${selectedPatient.birthDate}">
						</p>
						<p>
							<label> <c:out value="${diagnosisMessage}:"></c:out>
							</label> <input type="text" class="form-control" name="diagnosis"
								value="${selectedPatient.diagnosis}">
						</p>
						<p>
							<label> <c:out value="${noteMessage}:"></c:out>
							</label> <input type="text" class="form-control" name="note"
								value="${selectedPatient.note}">
						</p>
					</form>
					<form action="controller" method="get" id="cancel">
						<input type="hidden" name="command" value="GET_PATIENT_INFO">
						<input type="hidden" name="patientId"
							value="${selectedPatient.id}"> <input type="hidden"
							name="status" value="undone">
					</form>
					<button type="submit" form="editPatient"
						class="btn btn btn-success">
						<c:out value="${saveChangesButton}"></c:out>
					</button>
					<button type="submit" form="cancel" class="btn btn btn-default">
						<c:out value="${cancelButton}"></c:out>
					</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>