<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setLocale value="${sessionScope.localeName}"/>
<fmt:setBundle basename="resources.localization" var="local"/>
<fmt:message bundle="${local}" key="navbar.projectName" var="projectName"/>
<fmt:message bundle="${local}" key="navbar.home" var="home"/>
<fmt:message bundle="${local}" key="navbar.about" var="about"/>
<fmt:message bundle="${local}" key="navbar.contact" var="contact"/>
<fmt:message bundle="${local}" key="navbar.adminPanel" var="admin"/>
<fmt:message bundle="${local}" key="navbar.doctorPanel" var="doctorPanel"/>
<fmt:message bundle="${local}" key="message.welcome" var="welcomeMessage"/>
<fmt:message bundle="${local}" key="button.signIn" var="signInButton"/>
<fmt:message bundle="${local}" key="button.signUp" var="signUpButton"/>
<fmt:message bundle="${local}" key="button.signOut" var="signOutButton"/>
<fmt:message bundle="${local}" key="title.home" var="homeTitle"/>
<fmt:message bundle="${local}" key="heading.signIn" var="signInHeading"/>
<fmt:message bundle="${local}" key="error.signIn" var="signInError"/>
<fmt:message bundle="${local}" key="placeholder.username" var="usernamePlaceholder"/>
<fmt:message bundle="${local}" key="placeholder.password" var="passwordPlaceholder"/>