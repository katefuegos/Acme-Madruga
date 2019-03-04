
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

<form:form action="finder/member/update.do" modelAttribute="finder">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="lastUpdate" />
	<form:hidden path="processions" />




	<form:label path="keyword">
		<spring:message code="finder.keyword" />
	</form:label>
	<form:input path="keyword" />
	<form:errors cssClass="error" path="keyword" />
	<br />


	<form:label path="dateMin">
		<spring:message code="finder.dateMin" />
	</form:label>
	<form:input path="dateMin" placeholder="yyyy/mm/dd" />
	<form:errors cssClass="error" path="dateMin" />
	<br />

	<form:label path="dateMax">
		<spring:message code="finder.dateMax" />
	</form:label>
	<form:input path="dateMax" placeholder="yyyy/mm/dd" />
	<form:errors cssClass="error" path="dateMax" />
	<br />

	<form:label path="nameArea">
		<spring:message code="finder.namearea" />
	</form:label>
	<form:select id="areas" path="namearea">
		<form:option value="" label="------" />
		<form:options items="${areas}" />
	</form:select>

	

	<input type="submit" name="save"
		value="<spring:message code="finder.save"/>" />

	<input type="button" name="cancel"
		value="<spring:message code="finder.cancel" />"
		onclick="javascript: relativeRedir('finder/member/listProcessions.do');" />

	<br />


</form:form>