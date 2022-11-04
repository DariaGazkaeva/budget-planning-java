<%--
  Created by IntelliJ IDEA.
  User: dgazk
  Date: 04.11.2022
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/optionSelection.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <script src="${pageContext.request.contextPath}/js/linkListener.js"></script>
</head>

<body>
    <div class="option-selection">
        <a href="${pageContext.request.contextPath}/register">Зарегистрироваться</a>
        <a href="${pageContext.request.contextPath}/entry">Войти</a>
    </div>
</body>

</html>
