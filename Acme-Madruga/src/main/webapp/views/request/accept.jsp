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

<form:form action="${requestURI}" modelAttribute="requestForm">

	<form:hidden path="id" />
	<form:hidden path="reasonReject" />

	<form:label path="roow">
		<spring:message code="request.row" />:
	</form:label>
	<form:input path="roow" />
	<form:errors cssClass="error" path="roow" />
	<br />

	<form:label path="coluumn">
		<spring:message code="request.column" />:
	</form:label>
	<form:input path="coluumn" />
	<form:errors cssClass="error" path="roow" />
	<br />

	<input type="submit" name="save"
		value="<spring:message code="request.save" />" />

	<input type="button" name="cancel"
		value="<spring:message code="request.cancel" />"
		onclick="javascript: relativeRedir('request/brotherhood/list.do');" />
	<br />
	
</form:form>