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
		$(".panel-body").fadeIn(500);
		$(".panel-heading").on("click", function() {
			$(".panel-body").slideToggle(500);
		});

		$('#dischargeShowButton').on('click', function() {
			$(this).hide();
			$('#dischargeForm').fadeIn(500);
		});

		$('#dischargeCancelButton').on('click', function() {
			$('#dischargeForm').fadeOut(500);
			setTimeout(function() {
				$('#dischargeShowButton').fadeIn();
			}, 500);
		});
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

	<%@ include file="../elements/doctor/e_newAppointmentModal.jsp"%>

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
					<div class="page-header">
						<h1>
							<c:out value="${patientInfoHeading}"></c:out>
						</h1>
					</div>
					<p>
						<label> <c:out value="${nameMessage}:"></c:out>
						</label>
						<c:out value="${selectedPatient.name}"></c:out>
					</p>
					<p>
						<label> <c:out value="${surnameMessage}:"></c:out>
						</label>
						<c:out value="${selectedPatient.surname}"></c:out>
					</p>
					<p>
						<label> <c:out value="${sexMessage}:"></c:out>
						</label>
						<c:if test="${selectedPatient.sex == 'м'}">
							<c:out value="${fullMaleMessage}"></c:out>
						</c:if>
						<c:if test="${selectedPatient.sex == 'ж'}">
							<c:out value="${fullFemaleMessage}"></c:out>
						</c:if>
					</p>
					<p>
						<label> <c:out value="${birthDateMessage}:"></c:out>
						</label>
						<c:out value="${selectedPatient.birthDate}"></c:out>
					</p>
					<p>
						<label> <c:out value="${diagnosisMessage}:"></c:out>
						</label>
						<c:out value="${selectedPatient.diagnosis}"></c:out>
					</p>
					<p>
						<label> <c:out value="${receiptDateMessage}:"></c:out>
						</label>
						<c:out value="${selectedPatient.receiptDate}"></c:out>
					</p>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<c:out value="${appointmentsListHeading}">
								</c:out>
							</h3>
						</div>
						<div class="panel-body" style="display: none;">
							<c:if test="${requestScope.appointments == null}">
								<h4>
									<c:out value="${emptyAppointmentsMessage}"></c:out>
								</h4>
							</c:if>
							<c:if test="${requestScope.appointments != null}">
								<table class="table table-hover">
									<thead>
										<tr class="success">
											<td><strong> <c:out
														value="${appointmentTypeMessage}"></c:out>
											</strong></td>
											<td><strong> <c:out
														value="${appointmentNameMessage}"></c:out>
											</strong></td>
											<td><strong> <c:out
														value="${appointmentTimeMessage}"></c:out>
											</strong></td>
											<td><strong> <c:out
														value="${executionPeriodMessage}"></c:out>
											</strong></td>
											<td></td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="appointment"
											items="${requestScope.appointments}">
											<tr>
												<td><c:out value="${appointment.type}"></c:out></td>
												<td><c:out value="${appointment.name}"></c:out></td>
												<td><c:out value="${appointment.appointmentDate}">
													</c:out></td>
												<td><c:out value="${appointment.appointmentTerm}">
													</c:out></td>
												<td>
													<form action="controller" method="post">
														<input type="hidden" name="patientId"
															value="${selectedPatient.id}"> <input
															type="hidden" name="command" value="PERFORM_APPOINTMENT">
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
										</tr>
									</tbody>
								</table>
							</c:if>
							<button type="button" class="btn btn btn-success"
								data-toggle="modal" data-target="#newAppointmentModal">
								<span class="glyphicon glyphicon-plus"></span>
								<c:out value="${addAppointmentButton}">
								</c:out>
							</button>
						</div>
					</div>

					<form action="controller" method="post" style="display: none;"
						id="dischargeForm">
						<input type="hidden" name="command" value="DISCHARGE_PATIENT">
						<input type="hidden" name="patientId"
							value="${selectedPatient.id}">
						<p>
							<label> <c:out value="${dischargeHeading}:"></c:out>
							</label>
						</p>
						<p>
							<textarea rows="3" cols="3" class="form-control"
								name="finalDiagnosis" placeholder="${finalDiagnosisPlaceholder}"></textarea>
						</p>
						<p>
							<button type="sumbit" class="btn btn btn-danger">
								<span class="glyphicon glyphicon-trash"></span>
								<c:out value="${dischargeButton}"></c:out>
							</button>
							<button type="button" class="btn btn btn-default"
								id="dischargeCancelButton">
								<c:out value="${cancelButton}"></c:out>
							</button>
						</p>
					</form>
					<button type="button" class="btn btn btn-danger"
						id="dischargeShowButton">
						<span class="glyphicon glyphicon-trash"></span>
						<c:out value="${dischargeButton}"></c:out>
					</button>

					<form action="controller" method="get">
						<input type="hidden" name="command" value="GET_EDIT_PATIENT_PAGE">
						<input type="hidden" name="patientId"
							value="${selectedPatient.id}"> <br>
						<button type="submit" class="btn btn btn-warning">
							<span class="glyphicon glyphicon-pencil"></span>
							<c:out value="${editButton}"></c:out>
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>