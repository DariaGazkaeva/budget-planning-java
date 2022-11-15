<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html lang="ru">
<t:head title="Вход">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userForm.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/submitButton.css">
</t:head>

<t:body>

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
        <a class="user-form__a" href="${pageContext.request.contextPath}/">Назад</a>
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
</t:body>
</html>
