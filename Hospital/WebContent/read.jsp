<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="by.htp6.hospital.bean.Patient" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="content/Style.css">
<title>Patients</title>
</head>
<body>
	<h1>Read.jsp</h1>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Surname</th>
			<th>Diagnosis</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach var="patient" items="${requestScope.patientList}">
			
			<tr>
				<td><c:out value="${patient.id}"></c:out></td>
				<td><c:out value="${patient.name}"></c:out></td>
				<td><c:out value="${patient.surname}"></c:out></td>
				<td><c:out value="${patient.diagnosis}"></c:out></td>
				
			</tr>
		</c:forEach>
	</table>
	<form action="addPatient.jsp" method="post">
		<input type="submit" value="Add patient">
	</form>
</body>
</html>