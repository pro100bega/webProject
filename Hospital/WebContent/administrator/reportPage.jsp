<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/reportDisplay.tld" prefix="ctg"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../elements/administrator/e_localeMessages.jsp"%>

<script src="scripts/jquery-3.1.1.js"></script>

<link rel="stylesheet" href="css/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="css/starter-template.css">
<link rel="stylesheet" href="css/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/dashboard.css">

<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<title>${bugReportTitle}</title>
</head>
<body>
	<%@include file="../elements/administrator/e_reportNavbar.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="controller?command=GET_ADMIN_INFO"> <c:out
								value="${databaseLogMessage}">
							</c:out>
					</a></li>
					<li class="active"><a href="controller?command=GET_REPORTS">
							<c:out
								value="${bugsMessage} 
							(${sessionScope.unreadReportsCount})">
							</c:out>
					</a></li>
					<li><a
						href="controller?command=GET_USER_LIST&currentPage=1&usersPerPage=9">
							<c:out value="${userListMessage}">
							</c:out>
					</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="page-header">
					<h2 class="subheader">
						<c:out value="${messageMessage} № ${requestScope.report.id}">
						</c:out>
					</h2>
				</div>

				<p>
					<label> <c:out value="${headerMessage}:"></c:out>
					</label><br>
					<c:out value="${requestScope.report.header}"></c:out>
				</p>

				<p>
					<label> <c:out value="${messageMessage}:"></c:out>
					</label><br>
					<c:out value="${requestScope.report.message}"></c:out>
				</p>

				<p>
					<label> <c:out value="${timeMessage}:"></c:out>
					</label><br>
					<c:out value="${requestScope.report.time}"></c:out>
				</p>

				<form action="controller" method="get">
					<input type="hidden" name="command" value="GET_REPORTS">
					<button type="submit" class="btn btn btn-primary">
						<c:out value="${returnButton}"></c:out>
					</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>