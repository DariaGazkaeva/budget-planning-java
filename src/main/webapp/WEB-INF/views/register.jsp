<%--
  Created by IntelliJ IDEA.
  User: dgazk
  Date: 04.11.2022
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userForm.css">
</head>
<body>
    <div class="user-form">
        <h1>Регистрация</h1>
        <form action="" method="POST">
            <input type="text" placeholder="Как к Вам обращаться?" name="name" required><br>
            <br>
            <input type="text" placeholder="Введите email" name="email" required><br>
            <br>
            <input type="text" placeholder="Придумайте пароль" name="password" required><br>
            <br>
            <input type="text" placeholder="Повторите пароль" name="password" required><br>
        </form>
        <a href="${pageContext.request.contextPath}/">Назад</a>
    </div>

</body>
</html>
