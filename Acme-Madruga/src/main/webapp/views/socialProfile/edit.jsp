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

<form:form action="${requestURI}" modelAttribute="socialProfile">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="actor" />



	<form:label path="nick">
		<spring:message code="socialProfile.nick" />
	</form:label>
	<form:input path="nick" readonly="${isRead }"/>
	<form:errors cssClass="error" path="nick" />
	<br />

	<form:label path="nameSocialNetwork">
		<spring:message code="socialProfile.nameSocialNetwork" />
	</form:label>
	<form:input path="nameSocialNetwork" readonly="${isRead }"/>
	<form:errors cssClass="error" path="nameSocialNetwork" />
	<br />

	<form:label path="linkSocialNetwork">
		<spring:message code="socialProfile.linkSocialNetwork" />
	</form:label>
	<form:input path="linkSocialNetwork" readonly="${isRead }"/>
	<form:errors cssClass="error" path="linkSocialNetwork" />
	<br />

	<jstl:if test="${isRead==false }">
		<input type="submit" name="save"
			value="<spring:message code="socialProfile.save"/>" />


		<input type="button" name="cancel"
			value="<spring:message code="socialProfile.cancel" />"
			onclick="javascript: relativeRedir('socialProfile/list.do');" />

		<br />
	</jstl:if>

	<jstl:if test="${isRead == true }">
		<input type="button" name="cancel"
			value="<spring:message code="socialProfile.back" />"
			onclick="javascript: relativeRedir('socialProfile/list.do');" />
	</jstl:if>

</form:form>