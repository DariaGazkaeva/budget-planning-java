<%--
  Created by IntelliJ IDEA.
  User: dgazk
  Date: 10.11.2022
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактировать</title>
</head>
<body>
    <form action="" method="post">
        <div class="option-selection">
            <select name="type-money-operation" id="type-money-operation" required>
                <option value="income" <c:if test="${not empty moneyOperation and moneyOperation.getIncome() == true}">selected</c:if>>Доход</option>
                <option value="outcome" <c:if test="${not empty moneyOperation and moneyOperation.getIncome() == false}">selected</c:if>Расход</option>
            </select>
            <%--        TODO сделать создание своей категории (поэтому выбор категории не required)--%>
            <%--            TODO чтобы выпаал нужный селект категорий в зависимости от типа операции--%>
            <select name="category-money-operation" id="category-money-operation"
                    <c:if test="${not empty moneyOperation}">value="<c:out value="${moneyOperation.getCategoryId()}" />"</c:if>>
                <option value="1">Продукты</option>
                <option value="2">Общественный транспорт</option>
            </select>
            <input type="number" placeholder="Сумма" name="sum"
                   <c:if test="${not empty moneyOperation}">value="<c:out value="${moneyOperation.getSum()}" />"</c:if>>
            <input type="date" placeholder="Дата" name="date"
                   <c:if test="${not empty moneyOperation}">value="<c:out value="${moneyOperation.getDate()}" />"</c:if>>
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
