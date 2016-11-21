<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<fmt:setLocale value="${sessionScope.localeName}"/>
<fmt:setBundle basename="resources.localization" var="local"/>
<fmt:message bundle="${local}" key="navbar.projectName" var="projectName"/>
<fmt:message bundle="${local}" key="navbar.home" var="home"/>
<fmt:message bundle="${local}" key="navbar.about" var="about"/>
<fmt:message bundle="${local}" key="navbar.contact" var="contact"/>
<fmt:message bundle="${local}" key="message.welcome" var="welcomeMessage"/>
<fmt:message bundle="${local}" key="button.signOut" var="signOutButton"/>
<fmt:message bundle="${local}" key="title.home" var="homeTitle"/>

<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/starter-template.css">
<link rel="stylesheet"
	href="css/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="content/theme.css">

<title>Insert title here</title>
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
				<a class="navbar-brand" href="#"><c:out value="${projectName}"></c:out></a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#"><c:out value="${home}"></c:out></a></li>
					<li><a href="#about"><c:out value="${about}"></c:out></a></li>
					<li><a href="#contact"><c:out value="${contact}"></c:out></a></li>
				</ul>
				<form class="navbar-nav navbar-right" action="controller"
					method="post">
					<input type="hidden" name="command" value="SET_LOCALE"> <input
						type="hidden" name="localeName" value="ru_RU"> <input
						type="hidden" name="lastPage" value="index.jsp">
					<button type="submit" class="btn btn-xs btn-default">ru</button>
				</form>
				<form class="navbar-nav navbar-right" action="controller"
					method="post">
					<input type="hidden" name="command" value="SET_LOCALE"> <input
						type="hidden" name="localeName" value="en_US"> <input
						type="hidden" name="lastPage" value="index.jsp">
					<button type="submit" class="btn btn-xs btn-default">en</button>
				</form>
				<c:if test="${sessionScope.authorisedUser eq null}">

					<form class="navbar-form navbar-right" action="signIn"
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
							type="hidden" name="lastPage" value="index.jsp">
						<c:out value="${sessionScope.authorisedUser.username}"></c:out>
						<button type="submit" class="btn btn btn-danger">
							<c:out value="${signOutButton}"></c:out>
						</button>
					</form>
				</c:if>
			</div>
</body>
</html>