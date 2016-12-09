<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="../elements/doctor/e_localeMessages.jsp"%>
<script src="scripts/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(function() {
		$(".col-sm-9").fadeIn(1500);

		$('.panel-heading').on('click', function() {
			$(this).siblings().toggle(400);
		});
	});
</script>
<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/dashboard.css">

<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<title><c:out value="${doctorPanelTitle}"></c:out></title>
</head>
<body>

	<%@include file="../elements/doctor/e_navbar.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li>
						<a href="controller?command=SORT_PATIENTS&sortBy=name">
							<c:out value="${sortByNameMessage}"></c:out>
						</a>
					</li>
					<li>
						<a href="controller?command=SORT_PATIENTS&sortBy=surname">
							<c:out value="${sortBySurnameMessage}"></c:out>
						</a>
					</li>
					<li>
						<a href="controller?command=SORT_PATIENTS&sortBy=diagnosis">
							<c:out value="${sortByDiagnosisMessage}"></c:out>
						</a>
					</li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"
				style="display: none;">
				<c:forEach var="patient" items="${sessionScope.patients}">
					<div class="col-6">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">
									<c:out
										value="${patientInfo}:
										 ${patient.id}, ${patient.name}
										  ${patient.surname}, 
										  ${patient.diagnosis}">
									</c:out>
								</h3>
							</div>
							<div class="panel-body" style="display: none;">
								<dl>
									<dt>
										<c:out value="${nameMessage}:" />
									</dt>
									<dd>
										<c:out value="${patient.name}" />
									</dd>
									<dt>
										<c:out value="${surnameMessage}:" />
									</dt>
									<dd>
										<c:out value="${patient.surname}" />
									</dd>
									<dt>
										<c:out value="${diagnosisMessage}:" />
									</dt>
									<dd>
										<c:out value="${patient.diagnosis}" />
									</dd>
								</dl>
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
	</div>
</body>
</html>