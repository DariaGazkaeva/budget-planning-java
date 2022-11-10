<%--
  Created by IntelliJ IDEA.
  User: dgazk
  Date: 10.11.2022
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактировать</title>
</head>
<body>
    <form action="" method="post" accept-charset="UTF-8">
        <input type="text" placeholder="Какая цель?" name="name"
               <c:if test="${not empty cashSaving}">value="<c:out value="${cashSaving.getName()}" />"</c:if>>
        <input type="number" placeholder="Сумма" name="sum"
               <c:if test="${not empty cashSaving}">value="<c:out value="${cashSaving.getSum()}" />"</c:if>>
        <input type="submit" value="Сохранить">
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
