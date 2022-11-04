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
    <div class="registration-form">
        <h1>Регистрация</h1>
        <a href="${pageContext.request.contextPath}/">Назад</a>
        <form action="" method="POST">
            <h2>Как к Вам обращаться?</h2><br>
            <input type="text" placeholder="NAME" name="name" required><br>
            <h2>Введите email:</h2><br>
            <input type="text" placeholder="EMAIL" name="email" required><br>
            <h2>Придумайте пароль:</h2><br>
            <input type="text" placeholder="PASSWORD" name="password" required><br>
            <h2>Повторите пароль:</h2><br>
            <input type="text" placeholder="PASSWORD" name="password" required><br>
        </form>
    </div>

</body>
</html>
