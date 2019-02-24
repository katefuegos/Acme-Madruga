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


<display:table name="enrolmentForms" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="member.name" titleKey="enrolment.member" />
	<display:column property="enrolment.momentEnrol"
		titleKey="enrolment.momentEnrol" />
	<jstl:if test="${lang=='ES'}">
		<display:column property="enrolment.positionES"
			titleKey="enrolment.position" />
	</jstl:if>
	<jstl:if test="${lang=='EN'}">
		<display:column property="enrolment.positionEN"
			titleKey="enrolment.position" />
	</jstl:if>
	<display:column property="enrolment.momentDropOut"
		titleKey="enrolment.momentDropOut" />

	<security:authorize access="hasRole('BROTHERHOOD')">
		<display:column>
			<jstl:if test="${row.enrolment.accepted==false}">
				<a
					href="enrolment/brotherhood/enrol.do?enrolmentId=${row.enrolment.id}">
					<spring:message code="enrolment.enrol" />
				</a>
			</jstl:if>
		</display:column>
		<display:column>
			<jstl:if test="${row.enrolment.accepted==true}">
				<a
					href="enrolment/brotherhood/dropout.do?enrolmentId=${row.enrolment.id}">
					<spring:message code="enrolment.dropOut" />
				</a>
			</jstl:if>
		</display:column>
	</security:authorize>
</display:table>