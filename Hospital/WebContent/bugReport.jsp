<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="elements/index/e_localeMessages.jsp"%>

<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/starter-template.css">
<link rel="stylesheet" href="css/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/signin.css">

<script src="scripts/jquery-3.1.1.js"></script>
<script src="scripts/jquery.validate.min.js"></script>
<script src="scripts/validation.js"></script>
<script src="css/bootstrap/js/bootstrap.js"></script>

<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">

<title><c:out value="${bugReportTitle}"></c:out></title>
</head>
<body>

	<%@ include file="elements/index/e_navbar.jsp"%>

	<div class="container">
		<div class="page-header">
			<h1>
				<c:out value="${bugsHeading}"></c:out>
			</h1>
		</div>
		<div class="col-sm-6">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="SEND_REPORT">
				<div class="form-group has-feedback">
					<label> <c:out value="${headerHeading}"></c:out>
					</label> <input type="text" class="form-control" name="header"
						pattern="^[A-Za-z \-\.,!?]{5,20}$|^[А-Яа-я \-\.,!\?]{5,20}$"
						placeholder="${headerPlaceholder}"
						maxlength="20" required> <span
						class="glyphicon glyphicon-paperclip form-control-feedback">
					</span>
				</div>
				<div class="form-group has-feedback">
					<label><c:out value="${messageHeading}"></c:out></label>
					<textarea rows="10" class="form-control" name="message"
						pattern="^[A-Za-z \.,!\?\-]{5,20}$|^[А-Яа-я \.,!\?\-]{5,255}$"
						placeholder="${messagePlaceholder}"
						maxlength="255" required></textarea>
					<span class="glyphicon glyphicon-envelope form-control-feedback">
					</span>
				</div>
				<button type="submit" class="btn btn btn-success">
					<span class="glyphicon glyphicon-send"></span>
					<c:out value="${sendButton}"></c:out>
				</button>
			</form>
		</div>
	</div>

</body>
</html>