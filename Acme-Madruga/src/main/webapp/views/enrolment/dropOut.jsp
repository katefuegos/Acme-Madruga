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

<form:form action="${requestURI}" modelAttribute="enrolmentForm">

	<form:hidden path="id" />

	<spring:message code="enrolment.sureDropOut"></spring:message>
	<br>
	<input type="submit" name="save"
		value="<spring:message code="enrolment.yes" />" />

	<input type="button" name="cancel"
		value="<spring:message code="enrolment.cancel" />"
		onclick="javascript: relativeRedir('enrolment/member/list.do');" />
	<br />

</form:form>