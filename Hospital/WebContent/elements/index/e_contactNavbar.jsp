<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation"">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp"><c:out value="${projectName}"></c:out></a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="index.jsp"><c:out value="${home}"></c:out></a></li>
				<li><a href="about.jsp"><c:out value="${about}"></c:out></a></li>
				<li class="active"><a href="contact.jsp"><c:out value="${contact}"></c:out></a></li>
				<c:if test="${sessionScope.authorisedUser.type eq 'doctor'}">
					<li><a 
					href="controller?command=GET_PATIENT_LIST"> <c:out
								value="${doctorPanel}"></c:out></a></li>
				</c:if>
				<c:if test="${sessionScope.authorisedUser.type eq 'admin'}">
					<li><a href="controller?command=GET_ADMIN_INFO"> <c:out
								value="${adminPanel}"></c:out></a></li>
				</c:if>
				<c:if test="${sessionScope.authorisedUser.type eq 'nurse'}">
					<li><a 
					href="controller?command=GET_PATIENT_LIST"> <c:out
								value="${nursePanel}"></c:out></a></li>
				</c:if>
			</ul>
			<form class="navbar-nav navbar-right" action="controller"
				method="get">
				<input type="hidden" name="command" value="SET_LOCALE"> <input
					type="hidden" name="localeName" value="ru_RU"> <input
					type="hidden" name="redirect" value="contact.jsp">
				<button type="submit" id="ru-button"></button>
			</form>
			<form class="navbar-nav navbar-right" action="controller"
				method="get">
				<input type="hidden" name="command" value="SET_LOCALE"> <input
					type="hidden" name="localeName" value="en_US"> <input
					type="hidden" name="redirect" value="contact.jsp">
				<button type="submit" id="en-button"></button>
			</form>
			<c:if test="${sessionScope.authorisedUser eq null}">
				<form class="navbar-form navbar-right" action="signUp.jsp"
					method="get">
					<button type="submit" class="btn btn btn-primary">
						<c:out value="${signUpButton}"></c:out>
					</button>
				</form>
			</c:if>
			<c:if test="${not(sessionScope.authorisedUser eq null)}">
				<form class="navbar-form navbar-right" action="controller"
					method="get">
					<input type="hidden" name="command" value="SIGN_OUT">
					<span style="color:#9e9e9e;">
					<c:out value="${sessionScope.authorisedUser.username}"></c:out>
					</span>
					<button type="submit" class="btn btn btn-danger">
						<c:out value="${signOutButton}"></c:out>
					</button>
				</form>
			</c:if>
		</div>
		<!--/.nav-collapse -->
	</div>
</div>