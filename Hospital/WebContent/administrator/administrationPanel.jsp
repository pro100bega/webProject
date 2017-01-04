<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../elements/administrator/e_localeMessages.jsp"%>

<script src="scripts/jquery-3.1.1.js"></script>

<link rel="stylesheet" href="css/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="css/starter-template.css">
<link rel="stylesheet" href="css/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/dashboard.css">

<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<title>${adminPanelTitle}</title>
</head>
<body>
	<%@include file="../elements/administrator/e_navbar.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="#"> <c:out
								value="${databaseLogMessage}">
							</c:out>
					</a></li>
					<li><a
						href="controller?command=GET_REPORTS&currentPage=1&reportsPerPage=13">
							<c:out
								value="${bugsMessage} 
							(${requestScope.unreadReportsCount})">
							</c:out>
					</a></li>
					<li><a href="#"> <c:out value="${userListMessage}">
							</c:out>
					</a></li>
					<li><a href="#"> <c:out value="${doctorListMessage}">
							</c:out>
					</a></li>
					<li><a href="#"> <c:out value="${nurseListMessage}">
							</c:out>
					</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"
				id="databaseLogPanel">
				<div class="page-header">
					<h2 class="subheader">
						<c:out value="${databaseLogMessage}"></c:out>
					</h2>
				</div>
				<div class="panel panel-default">
					<table class="table">
						<thead>
							<tr>
								<td>Id</td>
								<td><c:out value="${logMessageHeading}"></c:out></td>
								<td><c:out value="${logTableNameHeading}"></c:out></td>
								<td><c:out value="${logRowIdHeading}"></c:out></td>
								<td><c:out value="${logTimeHeading}"></c:out></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="log" items="${requestScope.logList}">
								<tr>
									<td><c:out value="${log.id}"></c:out>
									<td><c:out value="${log.message}"></c:out>
									<td><c:out value="${log.tableName}"></c:out>
									<td><c:out value="${log.rowId}"></c:out>
									<td><c:out value="${log.time}"></c:out>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>