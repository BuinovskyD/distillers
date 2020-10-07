<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/webjars/bootstrap/4.5.0/css/bootstrap.min.css"/>" rel="stylesheet"/>

    <c:if test="${empty user.name}">
        <title><spring:message code="user.table.add_user"/></title>
    </c:if>
    <c:if test="${!empty user.name}">
        <title><spring:message code="user.table.upd_user"/></title>
    </c:if>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<br>
<div class="container">
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <form:form action="/distillers/users/edit" method="post">
                <c:if test="${!empty user.name}">
                    <input type="hidden" name="id" value="${user.id}">
                </c:if>

                <div class="form-group">
                    <label for="name"><spring:message code="user.table.name"/></label>
                    <input type="text" value="${user.name}" name="name" class="form-control" id="name">
                </div>
                <div class="form-group">
                    <label for="email"><spring:message code="user.table.email"/></label>
                    <input type="text" value="${user.email}" name="email" class="form-control" id="email">
                </div>
                <div class="form-group">
                    <label for="password"><spring:message code="user.table.password"/></label>
                    <input type="text" value="${user.password}" name="password" class="form-control" id="password">
                </div>
                <div class="form-group">
                    <label><spring:message code="user.table.role"/></label>
                    <select name="role" class="form-control">
                        <option>ADMIN</option>
                        <option>MANAGER</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-success"><spring:message code="app.from.save_btn"/></button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/users" role="button"><spring:message code="app.from.cancel_btn"/></a>
            </form:form>
        </div>
        <div class="col-sm"></div>
    </div>
</div>
</body>
</html>
