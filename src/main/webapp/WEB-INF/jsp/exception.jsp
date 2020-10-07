<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="container">
    <br>
    <h3><spring:message code="app.error"/></h3>
    <p>${exception.message}</p>
    <br>
    <a class="btn btn-primary btn-mini" href="${pageContext.request.contextPath}/" role="button"><spring:message code="app.home_btn"/></a>

    <!--
    <c:forEach items="${exception.stackTrace}" var="ste">
        ${ste}
    </c:forEach>
    -->
</div>
</body>
</html>
