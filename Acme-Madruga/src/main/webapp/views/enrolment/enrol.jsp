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

<form:form action="${requestURI}" modelAttribute="enrolment">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="brotherhood" />
	<form:hidden path="momentEnrol" />
	<form:hidden path="momentDropOut" />
	<form:hidden path="accepted" />

	<form:label path="positionEN">
		<spring:message code="enrolment.positionEN"></spring:message>
	</form:label>
	<form:select id="position" path="positionEN">
		<form:options items="${positions}" />
	</form:select>
	<br />

	<input type="submit" name="save"
		value="<spring:message code="enrolment.save" />" />

	<input type="button" name="cancel"
		value="<spring:message code="enrolment.cancel" />"
		onclick="javascript: relativeRedir('enrolment/brotherhood/list.do');" />
	<br />

</form:form>