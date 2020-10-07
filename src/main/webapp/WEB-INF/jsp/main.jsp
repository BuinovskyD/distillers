<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Distillers</title>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/webjars/bootstrap/4.5.0/css/bootstrap.min.css"/>" rel="stylesheet"/>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<br>
<div class="container">
    <br><br>
    <h3>Hello <sec:authentication property="name"/>!</h3>
    <p>
        <spring:message code="app.description"/>
    </p>
    <br>
    <sec:authorize access="hasAuthority('ADMIN')">
        <a class="btn btn-primary btn-lg" href="/users" role="button">
            <spring:message code="app.admin_panel_btn"/>
        </a>
    </sec:authorize>
    <a class="btn btn-primary btn-lg" href="/orders" role="button">
        <spring:message code="app.user_panel_btn"/>
    </a>
</div>
</body>
</html>
