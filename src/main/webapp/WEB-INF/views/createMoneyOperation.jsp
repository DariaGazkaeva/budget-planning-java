<%--
  Created by IntelliJ IDEA.
  User: dgazk
  Date: 09.11.2022
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Создать</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/optionSelection.css">
</head>
<body>
    <form action="" method="post">
        <div class="option-selection">
            <select name="type-money-operation" id="type-money-operation" required>
                <option value="income">Доход</option>
                <option value="outcome">Расход</option>
            </select>
            <%--        TODO сделать создание своей категории (поэтому выбор категории не required)--%>
<%--            TODO чтобы выпаал нужный селект категорий в зависимости от типа операции--%>
            <select name="category-money-operation" id="category-money-operation">
                <option value="1">Продукты</option>
                <option value="2">Общественный транспорт</option>
            </select>
            <input type="number" placeholder="Сумма" name="sum">
            <input type="date" placeholder="Дата" name="date">
            <input type="submit" value="Сохранить">
        </div>
    </form>


    <c:if test="${not empty errors}">
        <div class="user-form">
            <c:forEach var="error" items="${errors}">
                <p>
                    <c:out value="${error}"/>
                </p>
            </c:forEach>
        </div>
    </c:if>
</body>
</html>
