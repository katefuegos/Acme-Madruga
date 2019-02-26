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

<form:form action="${requestURI}" modelAttribute="area">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker" />
	<form:hidden path="brotherhood" />
	<form:hidden path="floats" />
	

	<form:label path="area">
		<spring:message code="brotherhood.area"></spring:message>
	</form:label>
	<form:select id="area" path="area">
		<form:options items="${areas}" />
	</form:select>
	<br />
	
		<input type="submit" name="save"
			value="<spring:message code="area.save" />" 
			onclick="javascript: relativeRedir('index.do');" />
			
		<input type="button" name="cancel"
			value="<spring:message code="area.cancel" />"
			onclick="javascript: relativeRedir('index.do');" />

</form:form>
