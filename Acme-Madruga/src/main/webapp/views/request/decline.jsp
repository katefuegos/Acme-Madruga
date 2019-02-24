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

<form:form action="${requestURI}" modelAttribute="request">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="member" />
	<form:hidden path="procession" />
	<form:hidden path="status" />
	<form:hidden path="roow" />
	<form:hidden path="coluumn" />

	<form:label path="reasonReject">
		<spring:message code="request.reasonReject" />:
	</form:label>
	<form:input path="reasonReject" />
	<form:errors cssClass="error" path="reasonReject" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="request.save" />" />

	<input type="button" name="cancel"
		value="<spring:message code="request.cancel" />"
		onclick="javascript: relativeRedir('request/brotherhood/list.do');" />
	<br />
	
</form:form>