<%--
  Created by IntelliJ IDEA.
  User: dgazk
  Date: 15.11.2022
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error ${statusCode}</title>
</head>
<body>
    <h1>Error ${statusCode}</h1>
    <img src="https://http.cat/${statusCode}" alt="${statusCode}" />
</body>
</html>
