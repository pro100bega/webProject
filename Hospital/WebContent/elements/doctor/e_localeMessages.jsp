<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
<fmt:setLocale value="${sessionScope.localeName}" />
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
<fmt:message bundle="${local}" key="button.discharge" var="dischargeButton" />
<fmt:message bundle="${local}" key="button.edit" var="editButton" />
<fmt:message bundle="${local}" key="placeholder.finalDiagnosis" var="finalDiagnosis" />
<fmt:message bundle="${local}" key="message.patientInfo" var="patientInfo" />
<fmt:message bundle="${local}" key="error.discharge" var="dischargeError" />
<fmt:message bundle="${local}" key="placeholder.search" var="searchPlaceholder" />
<fmt:message bundle="${local}" key="message.name" var="nameMessage" />
<fmt:message bundle="${local}" key="message.surname" var="surnameMessage" />
<fmt:message bundle="${local}" key="message.diagnosis" var="diagnosisMessage" />
<fmt:message bundle="${local}" key="button.addNewPatient" var="addNewPatientButton" />
<fmt:message bundle="${local}" key="heading.newPatient" var="newPatientHeading" />
<fmt:message bundle="${local}" key="button.newPatient" var="newPatientButton" />