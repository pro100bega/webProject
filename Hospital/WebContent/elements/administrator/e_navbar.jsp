<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${sessionScope.authorisedUser.type != 'admin'}">
	<c:redirect url="index.jsp" />
</c:if>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
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
				<li><a href="about.jsp"><c:out value="${about}"></c:out></a></li>
				<li><a href="contact.jsp"><c:out value="${contact}"></c:out></a></li>
				<li class="active"><a href="controller?command=GET_ADMIN_INFO">
						<c:out value="${adminPanel}"></c:out>
				</a></li>
			</ul>
			<form class="navbar-nav navbar-right" action="controller"
				method="get">
				<input type="hidden" name="command" value="SET_LOCALE"> <input
					type="hidden" name="localeName" value="ru_RU"> <input
					type="hidden" name="redirect"
					value="controller?command=GET_ADMIN_INFO">
				<button type="submit" id="ru-button"></button>
			</form>
			<form class="navbar-nav navbar-right" action="controller"
				method="get">
				<input type="hidden" name="command" value="SET_LOCALE"> <input
					type="hidden" name="localeName" value="en_US"> <input
					type="hidden" name="redirect"
					value="controller?command=GET_ADMIN_INFO">
				<button type="submit" id="en-button"></button>
			</form>
			<form class="navbar-form navbar-right" action="controller"
				method="get">
				<div class="form-group has-feedback">
					<input type="hidden" name="command" value="FIND_LOG"> <input
						type="text" class="form-control" name="searchData"
						autofocus="true" placeholder="${searchPlaceholder}"
						value="${requestScope.searchData}"
						pattern="^[A-Za-z0-9\._: ]{1,18}$"> <span
						class="glyphicon glyphicon-search form-control-feedback"></span>
				</div>
			</form>
			<form class="navbar-form navbar-right" action="controller"
				method="post">
				<input type="hidden" name="command" value="SIGN_OUT"> <span
					style="color: #9e9e9e;"> <c:out
						value="${sessionScope.authorisedUser.username}">
					</c:out> <span class="glyphicon glyphicon-user"></span>
				</span>
				<button type="submit" class="btn btn btn-danger">
					<c:out value="${signOutButton}"></c:out>
				</button>
			</form>
		</div>
		<!--/.nav-collapse -->
	</div>
</div>