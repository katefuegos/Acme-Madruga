<%--
 * action-1.jsp
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
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="register/actor.do" modelAttribute="actorForm">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />
	<form:hidden path="userAccount.enabled" />
	<form:hidden path="auth" />


	<form:label path="userAccount.username">
		<spring:message code="actor.userAccount.username" />:
	</form:label>
	<form:input path="userAccount.username" />
	<form:errors cssClass="error" path="userAccount.username" />
	<br />

	<form:label path="userAccount.password">
		<spring:message code="actor.userAccount.password" />:
	</form:label>
	<form:password path="userAccount.password" />
	<form:errors cssClass="error" path="userAccount.password" />
	<br />

	<form:label path="name">
		<spring:message code="actor.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />


	<form:label path="middleName">
		<spring:message code="actor.middleName" />:
	</form:label>
	<form:input path="middleName" />
	<form:errors cssClass="error" path="middleName" />
	<br />


	<form:label path="surname">
		<spring:message code="actor.surname" />:
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />

	<form:label path="photo">
		<spring:message code="actor.photo" />:
	</form:label>
	<form:input path="photo" />
	<form:errors cssClass="error" path="photo" />
	<br />

	<form:label path="email">
		<spring:message code="actor.email" />
	</form:label>
	<form:input path="email" readonly="${isRead}" />
	<form:errors cssClass="error" path="email" />
	<br />

	<form:label path="phone">
		<spring:message code="actor.phone" />
	</form:label>
	<form:input path="phone" id="tlf" readonly="${isRead}" />
	<form:errors path="phone" cssClass="error" />
	<br />

	<form:label path="address">
		<spring:message code="actor.address" />
	</form:label>
	<form:input path="address" readonly="${isRead}" />
	<form:errors cssClass="error" path="address" />
	<br />


<jstl:if test="${actorForm.auth != 'BROTHERHOOD'}">
	<form:hidden path="title" />
	<form:hidden path="pictures" />
	<form:hidden path="area" />
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

		<form:label path="area">
			<spring:message code="actor.area" />:
			</form:label>
		<form:select id="areas" path="area">
			<form:options items="${areas}" itemValue="id" itemLabel="name" />
		</form:select>
		<form:errors cssClass="error" path="area" />
		<br>

	</jstl:if>

	<form:label path="checkTerms">
		<spring:message code="actor.check" />
	</form:label>
	<form:checkbox path="checkTerms" readonly="${isRead}" />
	<form:errors cssClass="error" path="checkTerms" />
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

	<input type="submit" name="save"
		value='<spring:message code="actor.save"/>'
		onclick=" javascript: return isValid();" />

	<input type="button" name="cancel"
		value="<spring:message code="message.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');" />
	<br />

</form:form>