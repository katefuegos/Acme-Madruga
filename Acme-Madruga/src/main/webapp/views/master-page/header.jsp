<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="images/logo.png" alt="Sample Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message
						code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/administrator/list.do"><spring:message
						code="master.page.administrator.actors" /></a></li>
					
				</ul></li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
			<li><a class="fNiv" href="brotherhood/list.do"><spring:message
						code="master.page.brotherhood" /></a></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
			<li><a class="fNiv" href="brotherhood/list.do"><spring:message
						code="master.page.brotherhood" /></a></li>

			<li><a class="fNiv" href="box/actor/list.do"><spring:message
						code="master.page.box" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="message/actor/exchangeMessage.do"><spring:message
								code="master.page.message.exchange" /></a></li>
					<security:authorize access="hasRole('ADMIN')">
						<li><a href="message/administrator/broadcastMessage.do"><spring:message
									code="master.page.message.broadcast" /></a></li>
					</security:authorize>

				</ul></li>


		</security:authorize>

		<security:authorize access="hasRole('BROTHERHOOD')">
			<li><a class="fNiv" href="procession/brotherhood/list.do"><spring:message
						code="master.page.myProcessions" /></a></li>
			<li><a class="fNiv" href="request/brotherhood/list.do"><spring:message
						code="master.page.request" /></a></li>
			<li><a class="fNiv" href="enrolment/brotherhood/list.do"><spring:message
						code="master.page.enrolment" /></a></li>
		</security:authorize>




	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

