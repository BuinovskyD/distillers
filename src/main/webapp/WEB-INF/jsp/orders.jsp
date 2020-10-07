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
    <h3 align="center"><spring:message code="order.table_name"/></h3>
    <hr>
    <form action="${pageContext.request.contextPath}/orders/filter" method="get">
        <div class="form-group">
            <label for="startDate"><spring:message code="order.form.start_date"/></label>
            <input type="date" value="${param.startDate}" name="startDate" class="form-control" id="startDate">
        </div>
        <div class="form-group">
            <label for="endDate"><spring:message code="order.form.end_date"/></label>
            <input type="date" value="${param.endDate}" name="endDate" class="form-control" id="endDate">
        </div>

        <button type="submit" class="btn btn-secondary pull-right">
            <spring:message code="order.form.submit_button"/>
        </button>
    </form>
    <hr>
    <br>
    <a class="btn btn-primary btn-mini my-2 my-sm-0" href="${pageContext.request.contextPath}/orders/add" role="button">
        <spring:message code="order.form.add_order"/>
    </a>
    <br><br>
    <table class="table table-striped">
        <thead class="thead-light">
        <tr>
            <th><spring:message code="order.table.date"/></th>
            <th><spring:message code="order.table.customer_name"/></th>
            <th><spring:message code="order.table.customer_phone"/></th>
            <th><spring:message code="order.table.customer_address"/></th>
            <th><spring:message code="order.table.product"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach var="order" items="${orders}">
            <jsp:useBean id="order" type="ru.buinovsky.distillers.model.Order"/>
            <tr>
                <td>${order.date}</td>
                <td>${order.customerName}</td>
                <td>${order.customerPhone}</td>
                <td>${order.customerAddress}</td>
                <td>${order.product.toString()}</td>
                <td><a href="/distillers/orders/edit/${order.id}"><spring:message code="order.table.update"/></a></td>
                <td><a href="/distillers/orders/delete/${order.id}"><spring:message code="order.table.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>