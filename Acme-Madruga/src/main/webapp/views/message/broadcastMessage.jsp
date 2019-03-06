<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="${action}" modelAttribute="messageForm">

	<form:hidden path="message.id" />
	<form:hidden path="message.version" />
	<form:hidden path="message.moment" />
	<form:hidden path="message.sender" />
	<form:hidden path="message.recipient" />
	<form:hidden path="message.box" />
	

	<acme:textbox code="message.subject" path="subject"/>
	<acme:textbox code="message.body" path="body"/>
	<acme:textbox code="message.tags" path="tags"/>

	<form:label path="message.priority">
		<spring:message code="message.priority" />:
	</form:label>
	<form:select id="priorities" path="message.priority">
		<form:options items="${priorities}"/>
	</form:select>
	<form:errors cssClass="error" path="message.priority" />
	<br />

	<acme:submit name="save" code="message.save"/>
	<acme:cancel url="welcome/index.do" code="message.cancel"/>

</form:form>
