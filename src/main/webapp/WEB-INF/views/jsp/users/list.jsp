<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>

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