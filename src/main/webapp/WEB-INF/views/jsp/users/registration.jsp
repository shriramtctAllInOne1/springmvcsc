<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../fragments/header.jsp" />
<div class="container">
	<c:choose>
		<c:when test="${userForm['new']}">
			<h1><spring:message code="label.adduser"/></h1>
		</c:when>
		<c:otherwise>
			<h1><spring:message code="label.updateUser"/></h1>
		</c:otherwise>
	</c:choose>
	<br />
<spring:url value="/user/createUser"  var="userActionUrl" />
	<form:form class="form-horizontal" method="post" modelAttribute="userForm" action="${userActionUrl}">
		<form:hidden path="id" />
		<spring:bind path="name">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label"><spring:message code="label.Name"/></label>
				<div class="col-sm-10">
					<form:input path="name" type="text" class="form-control " id="name" placeholder="Name" />
					<form:errors path="name" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<spring:bind path="email">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label"><spring:message code="label.Email"/></label>
				<div class="col-sm-10">
					<form:input path="email" class="form-control" id="email" placeholder="Email" />
					<form:errors path="email" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<spring:bind path="password">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label"><spring:message code="label.Password"/></label>
				<div class="col-sm-10">
					<form:input path="password" class="form-control" id="password" placeholder="password" />
					<form:errors path="password" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<spring:bind path="confirmPassword">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label"><spring:message code="label.ConfirmPassword"/></label>
				<div class="col-sm-10">
					<form:input path="confirmPassword" class="form-control" id="password" placeholder="password" />
					<form:errors path="confirmPassword" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<spring:bind path="loginId">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label"><spring:message code="label.LoginId"/></label>
				<div class="col-sm-10">
					<form:input path="loginId" rows="5" class="form-control" id="loginId" placeholder="loginId" />
					<form:errors path="loginId" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<spring:bind path="sex">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label"><spring:message code="label.Sex"/></label>
				<div class="col-sm-10">
					<label class="radio-inline"> <form:radiobutton path="sex" value="M" /> <spring:message code="label.MALE"/>
					</label> <label class="radio-inline"> <form:radiobutton path="sex" value="F" /><spring:message code="label.Female"/>
					</label> <br />
					<form:errors path="sex" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${userForm['new']}">
						<button type="submit" class="btn-lg btn-primary pull-right"><spring:message code="label.Add"/></button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn-lg btn-primary pull-right"><spring:message code="label.Update"/></button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>

</div>

<div class="container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<h1><spring:message code="label.ALLUSER"/></h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th><spring:message code="label.ID"/></th>
					<th><spring:message code="label.Name"/></th>
					<th><spring:message code="label.Email"/></th>
					<th><spring:message code="label.LoginId"/></th>
					<th><spring:message code="label.Password"/></th>
					<th><spring:message code="label.Sex"/></th>
					<th><spring:message code="label.Action"/></th>
				</tr>
			</thead>

			<c:forEach var="user" items="${users}">
				<tr>
					<td>
						${user.id}
					</td>
					<td>${user.name}</td>
					<td>${user.email}</td>
					<td>${user.loginId}</td>
					<td>${user.password}</td>
					<td>${user.sex}</td>
					<td>
						<spring:url value="/user/${user.id}" var="userUrl" />
						<spring:url value="/user/${user.id}/delete" var="deleteUrl" /> 
						<spring:url value="/user/${user.id}/update" var="updateUrl" />
						<button class="btn btn-info" onclick="location.href='${userUrl}'"><spring:message code="label.Query"/></button>
						<button class="btn btn-primary" onclick="location.href='${updateUrl}'"><spring:message code="label.Update"/></button>
						<button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')"><spring:message code="label.Delete"/></button></td>
				</tr>
			</c:forEach>
		</table>

	</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>