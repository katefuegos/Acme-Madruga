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

<form:form action="${requestURI}" modelAttribute="processionForm">
	<form:label path="title">
		<spring:message code="procession.title" />:
	</form:label>
	<form:input path="title" readonly="${isRead}" />
	<form:errors cssClass="error" path="title" />
	<br />

	<form:label path="description">
		<spring:message code="procession.description" />:
	</form:label>
	<form:textarea path="description" readonly="${isRead}" />
	<form:errors cssClass="error" path="description" />
	<br />

	<form:label path="moment">
		<spring:message code="procession.moment" />:
	</form:label>
	<form:input path="moment" readonly="${isRead}"
		placeholder="yyyy/mm/dd HH:mm" />
	<form:errors cssClass="error" path="moment" />
	<br />

	<form:label path="draftMode">
		<spring:message code="procession.draftMode" />
	</form:label>
	<form:checkbox path="draftMode" />
	<form:errors path="draftMode" cssClass="error" />
	<br />

	<jstl:if test="${isRead == false}">
		<input type="submit" name="save"
			value="<spring:message code="procession.save" />" />
		<jstl:if test="${id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="procession.delete" />"
				onclick="javascript: return confirm('<spring:message code="procession.confirmDelete" />')" />
		</jstl:if>
		<input type="button" name="cancel"
			value="<spring:message code="procession.cancel" />"
			onclick="javascript: relativeRedir('procession/brotherhood/list.do');" />
		<br />

	</jstl:if>


	<jstl:if test="${isRead == true}">

		<input type="button" name="back"
			value="<spring:message code="procession.back" />"
			onclick="javascript: relativeRedir('/procession/brotherhood/list.do');" />
		<br />

	</jstl:if>

</form:form>