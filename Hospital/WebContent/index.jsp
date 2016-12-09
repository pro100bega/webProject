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
<c:if test="${sessionScope.localeName eq null}">
	<script src="scripts/messages_ru.js"></script>
</c:if>
<c:if test="${sessionScope.localeName eq 'ru_RU'}">
	<script src="scripts/messages_ru.js"></script>
</c:if>
<script type="text/javascript">
			$(function() {
				$('.form-signin').fadeIn(1000);
			});
		</script>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<title><c:out value="${homeTitle}"></c:out></title>
</head>
<body>
	<div class="background-image"></div>
	<%@include file="elements/index/e_navbar.jsp"%>
	<div class="container">
		<div class="starter-template">
			<h1>
				<c:out value="${welcomeMessage}"></c:out>
			</h1>
			<c:if test="${sessionScope.authorisedUser eq null}">
				<form action="controller" class="form-signin" method="post"
					style="display:none;">
					<c:if test="${requestScope.error eq null}">
						<h2 class="form-signin-heading">
							<c:out value="${signInHeading}"></c:out>
						</h2>
					</c:if>
					<c:if test="${requestScope.error eq true}">
						<div class="alert alert-danger">
							<strong><c:out value="${signInError}"></c:out></strong>
						</div>
					</c:if>
					<input type="hidden" name="command" value="SIGN_IN"> <input
						type="text" class="form-control" name="username"
						placeholder="${usernamePlaceholder}" required
						pattern="[0-9a-zA-Z_]{6,20}"> <input type="password"
						class="form-control" name="password"
						placeholder="${passwordPlaceholder}" required
						pattern="[0-9a-zA-Z]{6,20}"> <input type="submit"
						class="btn btn-lg btn-success btn-block" value="${signInButton}">
				</form>
			</c:if>
			<p class="lead"></p>
		</div>
	</div>
	<!-- /.container -->
	<div class="container">
	</div>
</body>
</html>