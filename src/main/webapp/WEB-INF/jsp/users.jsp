<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<br>
<div class="container">
    <h3 align="center"><spring:message code="user.table_name"/></h3>
    <hr>
    <table class="table table-striped">
        <thead class="thead-light">
        <tr>
            <th scope="col"><spring:message code="user.table.name"/></th>
            <th scope="col"><spring:message code="user.table.email"/></th>
            <th scope="col"><spring:message code="user.table.role"/></th>
<%--            <th scope="col"></th>--%>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <c:forEach var="user" items="${users}">
            <jsp:useBean id="user" type="ru.buinovsky.distillers.model.User"/>
            <tr>
                <td>${user.name}</td>
                <td><a href="mailto:${user.email}">${user.email}</a></td>
                <td>${user.role.toString()}</td>
<%--                <td><a href="/distillers/orders/${user.id}"><spring:message code="user.table.orders"/></a></td>--%>
                <td><a href="/distillers/users/edit/${user.id}"><spring:message code="user.table.update"/></a></td>
                <td><a href="/distillers/users/delete/${user.id}"><spring:message code="user.table.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
    <hr/>
    <a class="btn btn-primary btn-mini" href="/distillers/users/add" role="button">
        <spring:message code="user.table.add_user"/>
    </a>
    <a class="btn btn-primary btn-mini" href="/distillers/orders/all" role="button">
        <spring:message code="user.table.all_orders_btn"/>
    </a>
</div>
</body>
</html>
