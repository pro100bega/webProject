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
<fmt:message bundle="${local}" key="navbar.contacts" var="contact" />
<fmt:message bundle="${local}" key="navbar.adminPanel"
	var="adminPanel" />
<fmt:message bundle="${local}" key="title.error" var="errorTitle" />
<fmt:message bundle="${local}" key="title.adminPanel" var="adminPanelTitle" />
<fmt:message bundle="${local}" key="title.bugReport" var="bugReportTitle" />
<fmt:message bundle="${local}" key="placeholder.search" var="searchPlaceholder" />
<fmt:message bundle="${local}" key="message.patientInfo" var="patientInfo" />
<fmt:message bundle="${local}" key="message.name" var="nameMessage" />
<fmt:message bundle="${local}" key="message.surname" var="surnameMessage" />
<fmt:message bundle="${local}" key="message.diagnosis" var="diagnosisMessage" />
<fmt:message bundle="${local}" key="message.databaseLog" var="databaseLogMessage" />
<fmt:message bundle="${local}" key="message.userList" var="userListMessage" />
<fmt:message bundle="${local}" key="message.doctorList" var="doctorListMessage" />
<fmt:message bundle="${local}" key="message.nurseList" var="nurseListMessage" />
<fmt:message bundle="${local}" key="message.header" var="headerMessage" />
<fmt:message bundle="${local}" key="message.message" var="messageMessage" />
<fmt:message bundle="${local}" key="message.time" var="timeMessage" />
<fmt:message bundle="${local}" key="button.signOut" var="signOutButton" />
<fmt:message bundle="${local}" key="button.newDoctor" var="newDoctorButton"/>
<fmt:message bundle="${local}" key="button.addNewDoctor" var="addNewDoctorButton"/>
<fmt:message bundle="${local}" key="button.return" var="returnButton"/>
<fmt:message bundle="${local}" key="heading.bugs" var="bugsMessage"/>
<fmt:message bundle="${local}" key="heading.log.message" var="logMessageHeading"/>
<fmt:message bundle="${local}" key="heading.log.tableName" var="logTableNameHeading"/>
<fmt:message bundle="${local}" key="heading.log.rowId" var="logRowIdHeading"/>
<fmt:message bundle="${local}" key="heading.log.time" var="logTimeHeading"/>
