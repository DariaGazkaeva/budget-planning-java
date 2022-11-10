<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Вход</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userForm.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/submitButton.css">
</head>
<body>
    <div class="user-form">
        <h1>Войти</h1>
        <form action="" method="POST">
            <input type="email" placeholder="Введите email" name="email" required
            <c:if test="${not empty email}"> value="${email}" </c:if>><br>
            <br>
            <input type="password" placeholder="Введите пароль" name="password" required><br>
            <br>
            <input type="submit" value="Войти">
            <br>
        </form>
        <a href="${pageContext.request.contextPath}/">Назад</a>
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
