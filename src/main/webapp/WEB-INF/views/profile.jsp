<%--
  Created by IntelliJ IDEA.
  User: dgazk
  Date: 06.11.2022
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Профиль</title>
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
            <c:forEach items="${cashSavings}" var="cash">
                <div class="cash-saving">
                    <h3>${cash.getName()}</h3>
                    <p>${cash.getSum()}</p>
                </div>
            </c:forEach>
<%--TODO конвертер валют--%>
        </div>

        <div class="main">

            <div class="month-data">
                <div class="option-selection">
                    <a>Мои расходы за месяц: ${outcomeSumMonth}</a>
                </div>

                <div class="option-selection">
                    <a>Мои доходы за месяц: ${incomeSumMonth}</a>
                </div>
            </div>

            <div class="control-buttons">
                <button class="add-cash-saving">Добавить сбережение</button>
                <button class="add-income">Добавить доход</button>
                <button class="add-outcome">Добавить расход</button>
            </div>

        </div>

    </div>
</body>
</html>
