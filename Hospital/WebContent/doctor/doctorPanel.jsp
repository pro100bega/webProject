<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/patientDisplay.tld" prefix="ctg"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ include file="../elements/doctor/e_localeMessages.jsp"%>
<script src="scripts/jquery-3.1.1.js"></script>
<script src="css/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	$(function() {
		$(".col-sm-9").fadeIn(600);

		$('.panel-heading').on('click', function() {
			$(this).siblings().toggle(400);
		});

	});
</script>
<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/starter-template.css">
<link rel="stylesheet" href="css/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/dashboard.css">

<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<title><c:out value="${doctorPanelTitle}"></c:out></title>
</head>
<body>

	<%@ include file="../elements/doctor/e_newPatientModal.jsp" %>

	<%@ include file="../elements/doctor/e_navbar.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="controller?command=GET_PATIENT_LIST&orderBy=name">
							<c:out value="${sortByNameMessage}"></c:out>
					</a></li>
					<li><a href="controller?command=GET_PATIENT_LIST&orderBy=surname">
							<c:out value="${sortBySurnameMessage}"></c:out>
					</a></li>
					<li><a
						href="controller?command=GET_PATIENT_LIST&orderBy=diagnosis"> <c:out
								value="${sortByDiagnosisMessage}"></c:out>
					</a></li>
				</ul>
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"
				style="display: none;">
				<c:set var="patients" value="${requestScope.patients}"></c:set>
				<c:set var="patientsCount" value="${requestScope.patientsCount}"></c:set>
				<c:set var="currentPage" value="${sessionScope.currentPage}"></c:set>
				<c:set var="patientsPerPage" value="${sessionScope.patientsPerPage}"></c:set>
				<ctg:patientDisplay patients="${patients}" patientsCount="${patientsCount}"
						currentPage="${currentPage}" patientsPerPage="${patientsPerPage}"/>
			</div>
		</div>
	</div>
</body>
</html>