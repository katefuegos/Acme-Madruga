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

<%-- <h3>
	<spring:message code="administrator.dashboard.C1" />
</h3>

<ul>
	<li><spring:message code="administrator.avg" />: <jstl:out value="${avgC1}"/></li>
	<li><spring:message code="administrator.max" />: <jstl:out value="${maxC1}"/></li>
	<li><spring:message code="administrator.min" />: <jstl:out value="${minC1}"/></li>
	<li><spring:message code="administrator.stddev" />: <jstl:out value="${stddevC1}"/></li>
</ul>
<br />
<br />
<h3>
	<spring:message code="administrator.dashboard.C2" />
</h3>

	<p>	<spring:message code="administrator.dashboard.name" />:
		<a href="brotherhood/display.do?id=${idLargest}"><jstl:out value="${nameLargest}"/></a></p>
	<p><spring:message code="administrator.dashboard.count" />:<jstl:out value="${countLargest}"/></p>
<br />
<br />

<h3>
	<spring:message code="administrator.dashboard.C3" />
</h3>

	<p>	<spring:message code="administrator.dashboard.name" />:
		<a href="brotherhood/display.do?id=${idSmallest}"><jstl:out value="${nameSmallest}"/></a></p>
	<p><spring:message code="administrator.dashboard.count" />:<jstl:out value="${countLargest}"/></p>
<br />
<br />


<h3>
	<spring:message code="administrator.dashboard.C4" />
</h3>
<jstl:forEach var="entry" items="${statusCount}">
  <jstl:out value="${entry.key}"/> --- <jstl:out value="${entry.value}"/><br>
</jstl:forEach>
<br />
<br /> --%>
<p>
Error procession:  
<jstl:out value="${excP }"/>
</p>
<p>
Error member: 
<jstl:out value="${excM }"/>
</p>


<h3>
	<spring:message code="administrator.dashboard.C5" />
</h3>
<display:table name="processionsC5" id="row" class="displaytag">
	<display:column property="ticker" titleKey="administrator.procession.ticker" />
	<display:column property="title" titleKey="administrator.procession.title" />
	<display:column property="moment" titleKey="administrator.procession.moment" />
	<display:column property="brotherhood.title" titleKey="administrator.procession.procession.brotherhood" />
</display:table> 

<br />
<br />

<h3>
	<spring:message code="administrator.dashboard.C7" />
</h3>
<display:table name="queryC7" id="row" class="displaytag">
	<display:column property="name" titleKey="actor.name" />
	<display:column property="userAccount.username" titleKey="actor.username" />
	<display:column property="email" titleKey="actor.email" />
	<display:column property="phone" titleKey="actor.phone" />
</display:table> 
<br />
<br />
<h3>
	<spring:message code="administrator.dashboard.C8" />
</h3>

<jstl:if test="${lang=='EN' }">
<jstl:forEach var="entry" items="${positionEN}">
  <jstl:out value="${entry.key}"/> --- <jstl:out value="${entry.value}"/><br>
</jstl:forEach>
</jstl:if>
<p>${lang }</p>
<jstl:if test="${lang=='ES' }">
<jstl:forEach var="entry" items="${positionES}">
  <jstl:out value="${entry.key}"/> --- <jstl:out value="${entry.value}"/><br>
</jstl:forEach>
</jstl:if>


<br />
<br />

<h3>
	<spring:message code="administrator.dashboard.B1" />
</h3>
<ul>
	<li><spring:message code="administrator.avg" />: <jstl:out value="${avgB1}"/></li>
	<li><spring:message code="administrator.max" />: <jstl:out value="${maxB1}"/></li>
	<li><spring:message code="administrator.min" />: <jstl:out value="${minB1}"/></li>
	<li><spring:message code="administrator.stddev" />: <jstl:out value="${stddevB1}"/></li>
</ul>

<display:table name="areaQueryB1" id="row" class="displaytag">
	<display:column property="name" titleKey="administrator.dashboard.name" />
	<display:column property="ratio" titleKey="administrator.ratio" />
	<display:column property="count" titleKey="administrator.dashboard.count" />
</display:table> 


<br />
<br />

<h3>
	<spring:message code="administrator.dashboard.B2" />
</h3>
<ul>
	<li><spring:message code="administrator.avg" />: <jstl:out value="${avgB2}"/></li>
	<li><spring:message code="administrator.max" />: <jstl:out value="${maxB2}"/></li>
	<li><spring:message code="administrator.min" />: <jstl:out value="${minB2}"/></li>
	<li><spring:message code="administrator.stddev" />: <jstl:out value="${stddevB2}"/></li>
</ul>
<br />
<br />
<h3>
	<spring:message code="administrator.dashboard.B3" />
</h3>
<ul>
	<li><spring:message code="administrator.ratio.finder.empty" />:<jstl:out value="${queryB3FinderResultEmpty}"/> </li>
	<li><spring:message code="administrator.ratio.finder.notEmpty" />: <jstl:out value="${queryB3FinderResultNotEmpty}"/></li>
</ul>
<br />
<br />
