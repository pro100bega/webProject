<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ include file="../elements/doctor/e_localeMessages.jsp"%>
<script src="scripts/jquery-3.1.1.js"></script>
<script src="css/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	$(function() {
		$(".panel-body").fadeIn(1500);
	});
</script>
<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/starter-template.css">
<link rel="stylesheet" href="css/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/dashboard.css">


<c:set var="selectedPatient" value="${sessionScope.selectedPatient}"></c:set>
<c:set var="patientName" value="${selectedPatient.name}"></c:set>
<c:set var="patientSurname" value="${selectedPatient.surname}"></c:set>

<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<title><c:out value="${patientSurname} ${patientName}"></c:out></title>
</head>
<body>
	<%@ include file="../elements/doctor/e_patientNavbar.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li></li>
					<li></li>
					<li></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="col-6">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								<c:out
									value="${patientInfo}:
										 ${selectedPatient.id}, ${selectedPatient.name}
										  ${selectedPatient.surname}, 
										  ${selectedPatient.diagnosis}">
								</c:out>
							</h3>
						</div>
						<div class="panel-body" style="display: none;">
							<table class="table table-bordered">
								<thead>
									<tr>
										<td><c:out value="${patientIdMessage}"></c:out></td>
										<td><c:out value="${doctorIdMessage}"></c:out></td>
										<td><c:out value="${appointmentTypeMessage}"></c:out></td>
										<td><c:out value="${appointmentNameMessage}"></c:out></td>
										<td></td>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="appointment"
										items="${sessionScope.appointments}">
										<tr>
											<td><c:out value="${appointment.patientId}"></c:out></td>
											<td><c:out value="${appointment.doctorId}"></c:out></td>
											<td><c:out value="${appointment.type}"></c:out></td>
											<td><c:out value="${appointment.name}"></c:out></td>
											<td>
												<form action="controller" method="post">
													<input type="hidden" name="command"
														value="PERFORM_APPOINTMENT">
													<input type="hidden" name="id" value="${appointment.id}">
													<input type="hidden" name="type"
														value="${appointment.type}">
													<button type="submit" class="btn btn-sm btn-success">
														<c:out value="${performAppointmentButton}">
														</c:out>
													</button>
												</form>
											</td>
										</tr>
									</c:forEach>
									<tr>
										<td><c:out value="${selectedPatient.id}"></c:out></td>
										<td><c:out value="${selectedPatient.doctorId}"></c:out></td>
										<td><input type="text" class="form-control"></td>
										<td><input type="text" class="form-control"></td>
										<td>
											<form>
												<button type="submit" class="btn btn-sm btn-success">
												<c:out value="${addAppointmentButton}">
												</c:out>
											</button>
											</form>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>