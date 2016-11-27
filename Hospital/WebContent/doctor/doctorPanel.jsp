<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<c:if test="${not sessionScope.localeName eq null}">
	<fmt:setLocale value="${sessionScope.localeName}" />
</c:if>
<fmt:setBundle basename="resources.localization" var="local" />
<fmt:message bundle="${local}" key="navbar.projectName"
	var="projectName" />
<fmt:message bundle="${local}" key="navbar.home" var="home" />
<fmt:message bundle="${local}" key="navbar.about" var="about" />
<fmt:message bundle="${local}" key="navbar.contact" var="contact" />
<fmt:message bundle="${local}" key="navbar.doctorPanel"
	var="doctorPanel" />
<fmt:message bundle="${local}" key="button.signOut" var="signOutButton" />
<fmt:message bundle="${local}" key="title.doctorPanel" var="doctorPanelTitle" />

<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/starter-template.css">
<link rel="stylesheet" href="css/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="content/theme.css">

<link rel="shortcut icon" href="hospital.ico" type="image/x-icon">
<title><c:out value="${doctorPanelTitle}"></c:out></title>
</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp"><c:out
						value="${projectName}"></c:out></a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="index.jsp"><c:out value="${home}"></c:out></a></li>
					<li><a href="#about"><c:out value="${about}"></c:out></a></li>
					<li><a href="#contact"><c:out value="${contact}"></c:out></a></li>
					<li class="active"><a href="#"> <c:out
								value="${doctorPanel}"></c:out></a></li>
				</ul>
				<form class="navbar-nav navbar-right" action="controller"
					method="post">
					<input type="hidden" name="command" value="SET_LOCALE"> <input
						type="hidden" name="localeName" value="ru_RU"> <input
						type="hidden" name="lastPage" value="doctor/doctorPanel.jsp">
					<button type="submit" class="btn btn-xs btn-default">ru</button>
				</form>
				<form class="navbar-nav navbar-right" action="controller"
					method="post">
					<input type="hidden" name="command" value="SET_LOCALE"> <input
						type="hidden" name="localeName" value="en_US"> <input
						type="hidden" name="lastPage" value="doctor/doctorPanel.jsp">
					<button type="submit" class="btn btn-xs btn-default">en</button>
				</form>
				<c:if test="${sessionScope.authorisedUser eq null}">

					<form class="navbar-form navbar-right" action="signIn.jsp"
						method="post">
						<button type="submit" class="btn btn btn-success">
							<c:out value="${signInButton}"></c:out>
						</button>
					</form>
					<form class="navbar-form navbar-right" action="signUp.jsp"
						method="post">
						<button type="submit" class="btn btn btn-primary">
							<c:out value="${signUpButton}"></c:out>
						</button>
					</form>
				</c:if>
				<c:if test="${not(sessionScope.authorisedUser eq null)}">
					<form class="navbar-form navbar-right" action="controller"
						method="post">
						<input type="hidden" name="command" value="SIGN_OUT"> <input
							type="hidden" name="lastPage" value="doctor/doctorPanel.jsp">
						<c:out value="${sessionScope.authorisedUser.username}"></c:out>
						<button type="submit" class="btn btn btn-danger">
							<c:out value="${signOutButton}"></c:out>
						</button>
					</form>
				</c:if>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<div class="container">
		<c:forEach var="patient" items="${sessionScope.patients}">
			<div class="jumbotron">
				<p>
					
					<c:out value="Name: ${patient.name}" />
				</p>
				<p>
					<c:out value="Surname: ${patient.surname}" />
				</p>
				<p>
					<c:out value="Diagnosis: ${patient.diagnosis}" />
				</p>
				<form action="controller" method="post">
					<input type="hidden" name="command" value="DISCHARGE_PATIENT">
					<input type="hidden" name="patientId" value="${patient.id}">
					<button type="submit" class="btn btn btn-warning">Discharge</button>
				</form>
				<form action="controller" method="post">
					<input type="hidden" name="command" value="UPDATE_PATIENT">
					<input type="hidden" name="patientId" value="${patient.id}">
					<button type="submit" class="btn btn btn-warning">Discharge</button>
				</form>
			</div>
		</c:forEach>
	</div>

</body>
</html>