<%--
  Created by IntelliJ IDEA.
  User: dgazk
  Date: 10.11.2022
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Записи</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
    <table class="money-operation">
        <c:forEach items="${moneyOperations}" var="operation">
            <tr class="money-operation">
                <th>${operation.getCategoryId()}</th>
                <th>${operation.getSum()}</th>
                <th>${operation.getDate()}</th>
                <th>${operation.getDescription()}</th>
                <th>
                    <a href="${pageContext.request.contextPath}/edit-money-operation?id=${operation.getId()}">Редактировать</a>
                </th>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
