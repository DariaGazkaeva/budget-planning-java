<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<head>
    <title>Профиль</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/optionSelection.css">
</head>
<body>
    <div class="container">

        <div class="header">
            <img class="logo" src="${pageContext.request.contextPath}/img/logo.png" alt="Логотип">
            <h1>Дневник расходов и доходов</h1>
        </div>

        <div class="sidebar">
            <p class="greetings">Добрый день, ${name}!</p>

            <c:if test="${not empty cashSavings}">
                <div class="cash-savings">
                    <h2>Сбережения</h2>
                    <br>
                    <c:forEach items="${cashSavings}" var="cash">
                        <div class="cash-saving">
                            <h3>${cash.getName()}</h3>
                            <p>
                                <fmt:formatNumber value="${cash.getSum()}" pattern="#,##0.00" />
                            </p>
                            <a href="${pageContext.request.contextPath}/edit-cash-saving?id=${cash.getId()}">Редактировать</a>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
<%--TODO конвертер валют--%>
        </div>

        <div class="main">

            <div class="month-data">
                <a class="main-widget" href="${pageContext.request.contextPath}/history?type=outcome">Мои расходы с начала месяца: ${outcomeSumMonth}</a>
                <a class="main-widget" href="${pageContext.request.contextPath}/history?type=income">Мои доходы с начала месяца: ${incomeSumMonth}</a>
            </div>

            <div class="control-buttons">
                <a class="control-button" href="${pageContext.request.contextPath}/add-money-operation?type=income">Добавить доход</a>
                <a class="control-button" href="${pageContext.request.contextPath}/add-money-operation?type=outcome">Добавить расход</a>
                <a class="control-button" href="${pageContext.request.contextPath}/add-cash-saving">Добавить сбережение</a>
            </div>

        </div>

    </div>
</body>
</html>
