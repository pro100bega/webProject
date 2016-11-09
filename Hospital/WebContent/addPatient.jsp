<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add patient</title>
</head>
<body>
	<form action="addPatient" method="post">
		<label> Patient name: </label> <br> 
		<input required maxlength="20" name="patientName"  placeholder="Name" autofocus> <br>
		<label>Patient surname: </label>
		<br>
		<input required maxlength="20" name="patientSurname" placeholder="Surname">
		<br>
		<label>Patient diagnosis:</label>
		<br>
		<textarea rows="4" required name="diagnosis" cols="40"
		 		maxlength="50" placeholder="Diagnosis"></textarea>
		<br>
		<input type="submit" value="Add patient">
	</form>
</body>
</html>