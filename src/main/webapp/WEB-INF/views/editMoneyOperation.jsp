<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Редактирование</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userForm.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <script src="${pageContext.request.contextPath}/js/category.js"></script>
</head>
<body>
    <button class="create-category-widget__button">Создать новую категорию</button>
    <button class="delete-category-widget__button">Удалить какую-то категорию</button>

    <div class="user-form create-category-widget display-none">
        <form action="${pageContext.request.contextPath}/add-category" method="post">
            <input type="text" name="ame" placeholder="Название категории" id="categoryName" required>
            <select name="income" id="categoryIncome">
                <option value="true" selected>Доход</option>
                <option value="false">Расход</option>
            </select>
            <input type="submit" class="create-category-widget__send" value="Сохранить">
        </form>
    </div>
    <div class="user-form delete-category-widget display-none">
        <p>Удалить категорию:</p>
        <ul>
            <c:forEach var="category" items="${categories}">

                <li>
                    <a class="delete-category-widget__a" href="${pageContext.request.contextPath}/delete-category?id=${category.getId()}">
                        <c:out value="${category.name}" />
                    </a>
                </li>

            </c:forEach>
        </ul>
    </div>

    <div class="user-form">

        <form action="" method="post" class="edit-money-operation-form">
            <select name="category" id="categorySelect">
                <c:forEach items="${categories}" var="category">

                    <c:choose>
                        <c:when test="${not empty moneyOperation and moneyOperation.getCategoryId() == category.getId()}">
                            <option selected value="${category.id}">
                                <c:out value="${category.name}" />
                            </option>
                        </c:when>
                        <c:when test="${empty moneyOperation or moneyOperation.getCategoryId() != category.getId()}">
                            <option value="${category.id}">
                                <c:out value="${category.name}" />
                            </option>
                        </c:when>
                    </c:choose>

                </c:forEach>
            </select>

            <input type="number" placeholder="Сумма" name="sum"
                   <c:if test="${not empty moneyOperation}">value="<c:out value="${moneyOperation.getSum()}" />"</c:if>>
            <input type="date" placeholder="Дата" name="date"
                   <c:if test="${not empty moneyOperation}">value="<c:out value="${moneyOperation.getDate()}" />"</c:if>>
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
