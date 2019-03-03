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

<form:form action="position/administrator/create.do"
	modelAttribute="positionForm">

	<form:hidden path="id" />
	
	<form:label path="nameEN">
		<spring:message code="position.nameEN" />:
	</form:label>
	<form:input path="nameEN" />
	<form:errors cssClass="error" path="nameEN" />
	<br />
	
	<form:label path="nameES">
		<spring:message code="position.nameES" />:
	</form:label>
	<form:input path="nameES" />
	<form:errors cssClass="error" path="nameES" />
	<br />

	<br />

	<input type="submit" name="save"
		value="<spring:message code="position.save" />" />

	<input type="button" name="cancel"
		value="<spring:message code="position.cancel" />"
		onclick="javascript: relativeRedir('position/administrator/list.do');" />
	<br />

</form:form>