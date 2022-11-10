<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Создать</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/optionSelection.css">
</head>
<%--TODO создание своей категории--%>
<body>

    <form action="" method="post">
        <div class="option-selection">

            <select name="category">
                <c:forEach items="${categories}" var="category">
                    <option value="${category.getId()}">${category.getName()}</option>
                </c:forEach>
            </select>

            <input type="number" placeholder="Сумма" name="sum" required>
            <input type="date" placeholder="Дата" name="date" required>
            <input type="text" placeholder="Описание" name="description">
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
