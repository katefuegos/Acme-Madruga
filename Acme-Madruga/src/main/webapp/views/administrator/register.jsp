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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="register/administrator/newActor.do" modelAttribute="actorForm">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />
	<form:hidden path="userAccount.enabled" />
	<form:hidden path="auth" />
	<form:hidden path="title" />
	<form:hidden path="pictures" />
	<form:hidden path="area" />


	<acme:textbox code="actor.userAccount.username" path="userAccount.username"/>
	<acme:password code="actor.userAccount.password" path="userAccount.password"/>
	<acme:textbox code="actor.name" path="name"/>
	<acme:textbox code="actor.middleName" path="middleName"/>
	<acme:textbox code="actor.surname" path="surname"/>
	<acme:textbox code="actor.photo" path="photo"/>
	<acme:textbox code="actor.email" path="email"/>
	<acme:textbox code="actor.phone" path="phone"/>
	<acme:textbox code="actor.address" path="address"/>

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
	
	<form:label path="checkTerms">
		<spring:message code="actor.check" />
	</form:label>
	<form:checkbox path="checkTerms" readonly="${isRead}" />
	<form:errors cssClass="error" path="checkTerms" />
	<br />

	<acme:submit name="save" code="actor.save"/>
	<acme:cancel url="welcom/index.do" code="message.cancel"/>

</form:form>