<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Редактирование</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userForm.css">
</head>
<body>

    <div class="user-form">

        <form action="" method="post" accept-charset="UTF-8">
            <input type="text" placeholder="Какая цель?" name="name"
                   <c:if test="${not empty cashSaving}">value="<c:out value="${cashSaving.getName()}" />"</c:if>>
            <input type="number" placeholder="Сумма" name="sum"
                   <c:if test="${not empty cashSaving}">value="<c:out value="${cashSaving.getSum()}" />"</c:if>>
            <input type="submit" value="Сохранить">
        </form>

        <a class="user-form__a" href="${pageContext.request.contextPath}/profile">В профиль</a>
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
