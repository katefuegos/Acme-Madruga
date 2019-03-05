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




<form:form action="${requestURI}" modelAttribute="actorForm">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount" />
	<form:hidden path="auth" />
	<form:hidden path="auth" />
	<form:hidden path="area" />


	<jstl:if test="${isRead==true}">
		<img src="${actor.photo}" height="200px" width="200px" />
		<br />
	</jstl:if>

	<form:label path="name">
		<spring:message code="actor.name" />
	</form:label>
	<form:input path="name" readonly="${isRead}" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="middleName">
		<spring:message code="actor.middleName" />
	</form:label>
	<form:input path="middleName" readonly="${isRead}" />
	<form:errors cssClass="error" path="middleName" />
	<br />

	<form:label path="surname">
		<spring:message code="actor.surname" />
	</form:label>
	<form:input path="surname" readonly="${isRead}" />
	<form:errors cssClass="error" path="surname" />
	<br />

	<jstl:if test="${isRead == false}">
		<form:label path="photo">
			<spring:message code="actor.photo" />
		</form:label>
		<form:input path="photo" />
		<form:errors cssClass="error" path="photo" />
		<br />
	</jstl:if>

	<form:label path="email">
		<spring:message code="actor.email" />
	</form:label>
	<form:input path="email" readonly="${isRead}" />
	<form:errors cssClass="error" path="email" />
	<br />

	<form:label path="address">
		<spring:message code="actor.address" />
	</form:label>
	<form:input path="address" readonly="${isRead}" />
	<form:errors cssClass="error" path="address" />
	<br />

	<jstl:if test="${isRead == true}">
		<jstl:if test="${title != null}">
			<h3>
				<spring:message code="actor.title" />
				:
				<jstl:out value="${title}" />
			</h3>
		</jstl:if>
	</jstl:if>


	<form:label path="phone">
		<spring:message code="actor.phone" />
	</form:label>
	<form:input path="phone" id="tlf" readonly="${isRead}" />
	<form:errors path="phone" cssClass="error" />
	<br />

	<script type="text/javascript">
		function isValid() {
			var phoneRe = /^(((\+[1-9][0-9]{0,2}) \(([1-9][0-9]{0,2})\) (\d\d\d\d+))|((\+[1-9][0-9]{0,2}) (\d\d\d\d+))|((\d\d\d\d+)))$/;
			var digits = document.getElementById('tlf').value;
			var res = phoneRe.test(digits);
			if (res) {
				return true;
			} else {
				return confirm('<spring:message code="phone.confirm" />');
			}
		}
	</script>

	<jstl:if test="${isRead == true}">
		<jstl:if test="${establishmentDate != null}">
			<h3>
				<spring:message code="actor.establishmentDate" />
				:
				<jstl:out value="${establishmentDate}" />
			</h3>
		</jstl:if>
	</jstl:if>

<jstl:if test="${actorForm.auth != 'BROTHERHOOD'}">
	<form:hidden path="title" />
	<form:hidden path="pictures" />
	
</jstl:if>
	<jstl:if test="${actorForm.auth == 'BROTHERHOOD'}">

		<form:label path="title">
			<spring:message code="actor.title" />
		</form:label>
		<form:input path="title" readonly="${isRead}" />
		<form:errors cssClass="error" path="title" />
		<br />

		<form:label path="pictures">
			<spring:message code="actor.pictures" />
		</form:label>
		<form:input path="pictures" readonly="${isRead}" />
		<form:errors cssClass="error" path="pictures" />
		<br />

		

	</jstl:if>


	<jstl:if test="${isRead == false}">
		<br />
		<input type="submit" name="save"
			value='<spring:message code="actor.save"/>'
			onclick=" javascript: relativeRedir('welcome/index.do');">


		<input type="button" name="cancel"
			value="<spring:message code="actor.cancel" />"
			onclick="javascript: relativeRedir('welcome/index.do');" />
		<br />
	</jstl:if>

	<jstl:if test="${isRead == true}">
		<input type="button" name="back"
			value="<spring:message code="actor.back" />"
			onclick="javascript: relativeRedir('welcome/index.do');" />
		<br />

	</jstl:if>

</form:form>

