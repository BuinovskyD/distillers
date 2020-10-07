<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/webjars/bootstrap/4.5.0/css/bootstrap.min.css"/>" rel="stylesheet"/>

    <c:if test="${empty order.customerName}">
        <title><spring:message code="order.form.add_order"/></title>
    </c:if>
    <c:if test="${!empty order.customerName}">
        <title><spring:message code="order.form.upd_order"/></title>
    </c:if>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<br>
<div class="container">
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <form:form action="/orders/edit" method="post">
                <input type="hidden" name="id" value="${order.id}"/>

                <div class="form-group">
                    <label for="date"><spring:message code="order.table.date"/></label>
                    <input type="date" value="${order.date}" name="date" class="form-control" id="date">
                </div>
                <div class="form-group">
                    <label for="customerName"><spring:message code="order.table.customer_name"/></label>
                    <input type="text" value="${order.customerName}" name="customerName" class="form-control"
                           id="customerName">
                </div>
                <div class="form-group">
                    <label for="customerPhone"><spring:message code="order.table.customer_phone"/></label>
                    <input type="text" value="${order.customerPhone}" name="customerPhone" class="form-control"
                           id="customerPhone">
                </div>
                <div class="form-group">
                    <label for="customerAddress"><spring:message code="order.table.customer_address"/></label>
                    <input type="text" value="${order.customerAddress}" name="customerAddress" class="form-control"
                           id="customerAddress">
                </div>
                <div class="form-group">
                    <label><spring:message code="order.table.product"/></label>
                    <select name="product" class="form-control">
                        <option>ESSEN_12L</option>
                        <option>ESSEN_20L</option>
                        <option>ESSEN_30L</option>
                        <option>MAGNUM_22L</option>
                        <option>MAGNUM_32L</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-success"><spring:message code="app.from.save_btn"/></button>
                <a class="btn btn-secondary" href="/orders" role="button"><spring:message
                        code="app.from.cancel_btn"/></a>
            </form:form>
        </div>
        <div class="col-sm"></div>
    </div>
</div>
</body>
</html>