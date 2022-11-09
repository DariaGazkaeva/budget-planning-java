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
</head>
<body>
    <div class="option-selection">
<%--todo add labels--%>
        <form action="" method="post">
            <input type="text" placeholder="Какая цель?" name="name"
            <c:if test="${not empty name}">value="<c:out value="${name}" />"</c:if>
            >
            <input type="number" placeholder="Сумма" name="sum">
            <input type="submit" value="Сохранить">
        </form>

    </div>

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
