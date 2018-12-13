<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login page</title>
		<link href="<c:url value='/resources/static/css/bootstrap.css' />"  rel="stylesheet"></link>
		<link href="<c:url value='/resources/static/css/app.css' />" rel="stylesheet"></link>
		<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
	</head>
	<body>
		<div id="mainWrapper">
			<div class="login-container">
			Language : <a href="?language=en">English</a>|<a href="?language=zh_CN">Chinese</a>
			
				<div class="login-card">
					<div class="login-form">
						<c:url var="loginUrl" value="j_spring_security_check" />
						<%-- <form action="${loginUrl}" method="post" class="form-horizontal"> --%>
						<form:form name='loginForm' action="/j_spring_security_check" method='POST' modelAttribute="loginForm">
							<c:if test="${param.error != null}">
								<div class="alert alert-danger">
									<p>Invalid username and password.</p>
								</div>
							</c:if>
							<c:if test="${param.logout != null}">
								<div class="alert alert-success">
									<p>You have been logged out successfully.</p>
								</div>
							</c:if>
							<div class="input-group input-sm">
								<label class="input-group-addon"><i class="fa fa-user"></i>&nbsp<spring:message code="label.username" /></label>
								<form:input type="text" class="form-control" path ="loginId" id="loginId" name="loginId" placeholder="Enter Username" required="required"/>
							</div>
							<div class="input-group input-sm">
								<label class="input-group-addon"><i class="fa fa-lock"></i>&nbsp&nbsp<spring:message code="label.password" /></label> 
								<form:input type="password" class="form-control" path="password" id="password" name="password" placeholder="Enter Password" required="required"/>
							</div>
							<div class="input-group input-sm">
                              <div class="checkbox">
                                <label><input type="checkbox" id="rememberme" name="remember-me"><spring:message code="label.rememberme" /></label>  
                              </div>
                            </div>
							<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
								
							<div class="form-actions">
								<input type="submit"
									class="btn btn-block btn-primary btn-default" value="Log in">
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>

	</body>
</html>