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

<h3>
	<spring:message code="administrator.dashboard.C1" />
</h3>

<ul>
	<li><spring:message code="administrator.avg" />: ${avgC1}</li>
	<li><spring:message code="administrator.max" />: ${maxC1}</li>
	<li><spring:message code="administrator.min" />: ${minC1}</li>
	<li><spring:message code="administrator.stddev" />: ${stddevC1}</li>
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
<br />
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
<%-- 
<h3>
	<spring:message code="administrator.dashboard.C6" />
</h3>
<ul>
	<li><spring:message code="administrator.ratio" />: ${queryC6}</li>
</ul>
<br />
<br />
<h3>
	<spring:message code="administrator.dashboard.C7" />
</h3>
<ul>
	<li><spring:message code="administrator.ratio" />: ${queryC7}</li>
</ul>
<br />
<br />
<h3>
	<spring:message code="administrator.dashboard.C8" />
</h3>
<ul>
	<li><spring:message code="administrator.ratio" />: ${queryC8}</li>
</ul>
<br />
<br />
<h3>
	<spring:message code="administrator.dashboard.C9" />
</h3>
<display:table name="queryC9" id="row" requestURI="${requestURI}"
	class="displaytag">
	<display:column property="name" titleKey="actor.name" />
</display:table>
<br />
<br />
<h3>
	<spring:message code="administrator.dashboard.C10" />
</h3>
<display:table name="queryC10" id="row" requestURI="${requestURI}"
	class="displaytag">
	<display:column property="name" titleKey="actor.name" />
</display:table>
<br />
<br />

<h3>
	<spring:message code="administrator.dashboard.B1" />
</h3>
<ul>
	<li><spring:message code="administrator.avg" />: ${avgB1}</li>
	<li><spring:message code="administrator.max" />: ${maxB1}</li>
	<li><spring:message code="administrator.min" />: ${minB1}</li>
	<li><spring:message code="administrator.stddev" />: ${stddevB1}</li>
</ul>
<br />
<br />

<h3>
	<spring:message code="administrator.dashboard.B2" />
</h3>
<ul>
	<li><spring:message code="administrator.avg" />: ${avgB2}</li>
	<li><spring:message code="administrator.max" />: ${maxB2}</li>
	<li><spring:message code="administrator.min" />: ${minB2}</li>
	<li><spring:message code="administrator.stddev" />: ${stddevB2}</li>
</ul>
<br />
<br />
<h3>
	<spring:message code="administrator.dashboard.B3" />
</h3>
<ul>
	<li><spring:message code="administrator.ratio" />: ${queryC6}</li>
</ul>
<br />
<br />
<h3>
	<spring:message code="administrator.dashboard.B4" />
</h3>
<display:table name="queryB4" id="row" requestURI="${requestURI}"
	class="displaytag">
	<display:column property="name" titleKey="actor.name" />
</display:table>
<br />
<br />


<h3>
	<spring:message code="administrator.dashboard.B5" />
</h3>
<display:table name="queryB5" id="row" requestURI="${requestURI}"
	class="displaytag">
	<display:column property="name" titleKey="actor.name" />
</display:table> 

--%>
<br />
<br />
