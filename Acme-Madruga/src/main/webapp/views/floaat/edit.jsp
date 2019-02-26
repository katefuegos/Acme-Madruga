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

<form:form action="${requestURI}" modelAttribute="configurationForm">



	<form:label path="title">
		<spring:message code="floaat.title" />
	</form:label>
	<form:input path="title"/>
	<form:errors cssClass="error" path="title" />
	<br />

	<form:label path="description">
		<spring:message code="floaat.description" />
	</form:label>
	<form:input path="description"/>
	<form:errors cssClass="error" path="description" />
	<br />

	<form:label path="pictures">
		<spring:message code="floaat.pictures" />
	</form:label>
	<form:input path="pictures" />
	<form:errors cssClass="error" path="pictures" />
	<br />

		<input type="submit" name="save"
			value="<spring:message code="floaat.save" />" 
			onclick="javascript: relativeRedir('float/brotherhood/list.do');" />
			
		<jstl:if test="${floaat.id != 0}">

		<input type="submit" name="delete"
			value="<spring:message code="floaat.delete" />"
			onclick="javascript: return confirm('<spring:message code="floaat.confirm.delete" />')" />

		</jstl:if>

		<input type="button" name="cancel"
			value="<spring:message code="floaat.cancel" />"
			onclick="javascript: relativeRedir('float/brotherhood/list.do');" />

</form:form>
