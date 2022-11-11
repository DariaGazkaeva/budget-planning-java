<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <title>Записи</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
    <table class="money-operation">
        <tr>
            <th>Категория</th>
            <th>Сумма</th>
            <th>Дата</th>
            <th>Описание</th>
            <th></th>
            <th></th>
        </tr>
        <c:set var="count" value="0" />
        <c:forEach items="${moneyOperations}" var="operation">
            <tr>
                <c:forEach items="${categories}" var="category" begin="${count}" end="${count}">
                    <th>${category.getName()}</th>
                    <c:set var="count" value="${count + 1}" />
                </c:forEach>

                <th>${operation.getSum()}</th>
                <th>${operation.getDate()}</th>
                <th>${operation.getDescription()}</th>
                <th>
                    <a href="${pageContext.request.contextPath}/edit-money-operation?id=${operation.getId()}">Редактировать</a>
                </th>
                <th>
                    <a href="${pageContext.request.contextPath}/delete-money-operation?id=${operation.getId()}">Удалить</a>
                </th>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
