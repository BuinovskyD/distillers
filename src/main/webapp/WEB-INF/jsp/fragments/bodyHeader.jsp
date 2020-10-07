<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a href="${pageContext.request.contextPath}/" class="navbar-brand"><img
                src="https://yt3.ggpht.com/a/AATXAJxLYGf2I6dqafE-OB3RByRlSk8Oh5HcCfitIg=s900-c-k-c0xffffffff-no-rj-mo"
                alt="logo" width="30" height="30"/></a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/"><h5>Distillers</h5></a>
                </li>
            </ul>
            <a class="btn btn-success my-2 my-sm-0" type="button" href="<c:url value="/logout"/>"><spring:message code="app.logout_btn"/></a>
            <a href="?lang=en">En</a>|<a href="?lang=ru">Ru</a>
        </div>
    </div>
</nav>
