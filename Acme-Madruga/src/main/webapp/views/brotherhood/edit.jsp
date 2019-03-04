<%--
 * edit.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="${requestURI}" modelAttribute="brotherhood">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="title" />
	<form:hidden path="establishmentDate" />
	<form:hidden path="pictures" />
	<form:hidden path="isSpammer" />
	<form:hidden path="userAccount" />
	<form:hidden path="name" />
	<form:hidden path="middleName" />
	<form:hidden path="surname" />
	<form:hidden path="photo" />
	<form:hidden path="email" />
	<form:hidden path="address" />
	<form:hidden path="phone" />
	
	
	

	<form:label path="area">
		<spring:message code="brotherhood.area"></spring:message>
	</form:label>
	<form:select id="area" path="area">
		<form:options items="${areas}" />
	</form:select>
	<br />
	
		<input type="submit" name="save"
			value="<spring:message code="brotherhood.save" />" 
			onclick="javascript: relativeRedir('index.do');" />
			
		<input type="button" name="cancel"
			value="<spring:message code="brotherhood.cancel" />"
			onclick="javascript: relativeRedir('index.do');" />

</form:form>
