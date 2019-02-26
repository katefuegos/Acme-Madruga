<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p>


	<spring:message code="floaat.title"></spring:message> <jstl:out value="${floaat.title}"></jstl:out>
	<br />
	
	<spring:message code="floaat.description"></spring:message> <jstl:out value="${floaat.description}"></jstl:out>
	<br />
	
	<spring:message code="floaat.pictures"></spring:message> <jstl:out value="${floaat.pictures}"></jstl:out>
	<br />
	


<!-- Boton return -->

			
	<security:authorize access="hasRole('BROTHERHOOD')">	
				<button type="button"
					onclick="javascript: relativeRedir('floaat/brotherhood/list.do')">
					<spring:message code="floaat.return" />
				</button>
			</security:authorize>