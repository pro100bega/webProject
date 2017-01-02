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
<fmt:message bundle="${local}" key="navbar.contacts" var="contact"/>
<fmt:message bundle="${local}" key="navbar.adminPanel" var="adminPanel"/>
<fmt:message bundle="${local}" key="navbar.doctorPanel" var="doctorPanel"/>
<fmt:message bundle="${local}" key="message.welcome" var="welcomeMessage"/>
<fmt:message bundle="${local}" key="message.bugReportFirstPart" var="bugReportFirstMessage"/>
<fmt:message bundle="${local}" key="message.link" var="linkMessage"/>
<fmt:message bundle="${local}" key="message.bugReportSecondPart" var="bugReportSecondMessage"/>
<fmt:message bundle="${local}" key="message.about" var="aboutMessage"/>
<fmt:message bundle="${local}" key="button.signIn" var="signInButton"/>
<fmt:message bundle="${local}" key="button.signUp" var="signUpButton"/>
<fmt:message bundle="${local}" key="button.signOut" var="signOutButton"/>
<fmt:message bundle="${local}" key="title.home" var="homeTitle"/>
<fmt:message bundle="${local}" key="title.about" var="aboutTitle"/>
<fmt:message bundle="${local}" key="title.contacts" var="contactTitle"/>
<fmt:message bundle="${local}" key="heading.signIn" var="signInHeading"/>
<fmt:message bundle="${local}" key="heading.contactInformation" var="contactInformationHeading"/>
<fmt:message bundle="${local}" key="heading.phoneNumber" var="phoneNumberHeading"/>
<fmt:message bundle="${local}" key="heading.email" var="emailHeading"/>
<fmt:message bundle="${local}" key="heading.vk" var="vkHeading"/>
<fmt:message bundle="${local}" key="heading.skype" var="skypeHeading"/>
<fmt:message bundle="${local}" key="error.signIn" var="signInError"/>
<fmt:message bundle="${local}" key="placeholder.username" var="usernamePlaceholder"/>
<fmt:message bundle="${local}" key="placeholder.password" var="passwordPlaceholder"/>