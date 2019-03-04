
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

<a href="finder/member/update.do"> <spring:message
		code="finder.update" />
</a><br/>

<display:table name="processions" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="ticker" titleKey="procession.ticker" />

		<display:column property="title" titleKey="procession.title" />

	<display:column property="moment" titleKey="procession.moment" />
	
	<display:column titleKey="procession.area">
		<jstl:forEach var="entry" items="${row.brotherhood.area.name}">
			<jstl:if test="${lang==entry.key}">
				${entry.value}
			</jstl:if>
		</jstl:forEach>
	</display:column>




</display:table>


