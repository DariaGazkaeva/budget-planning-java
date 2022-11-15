<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html lang="ru">
<t:head title="Редактирование">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userForm.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <script src="${pageContext.request.contextPath}/js/category.js"></script>
    <script src="${pageContext.request.contextPath}/js/defaultDate.js"></script>
</t:head>
<t:body>
    <jsp:include page="parts/_header.jsp" />
    <button class="create-category-widget__button">Создать новую категорию</button>
    <button class="delete-category-widget__button">Удалить категорию</button>

    <div class="user-form create-category-widget display-none">
        <p>Создать категорию:</p>
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
                <c:if test="${category.authorId == userId}">
                    <li>
                        <a class="delete-category-widget__a" href="${pageContext.request.contextPath}/delete-category?id=${category.id}">
                            <c:out value="${category.name}" />
                        </a>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </div>

    <div class="user-form">
        <form action="" method="post" class="edit-money-operation-form">
            <select name="category" id="categorySelect" required>
                <c:forEach items="${categories}" var="category">

                    <c:choose>
                        <c:when test="${not empty moneyOperation and moneyOperation.categoryId == category.id}">
                            <option selected value="${category.id}">
                                <c:out value="${category.name}" />
                            </option>
                        </c:when>
                        <c:when test="${empty moneyOperation or moneyOperation.categoryId != category.id}">
                            <option value="${category.id}">
                                <c:out value="${category.name}" />
                            </option>
                        </c:when>
                    </c:choose>

                </c:forEach>
            </select>

            <input type="number" step="0.01" placeholder="Сумма" name="sum" required
                   <c:if test="${not empty moneyOperation}">value="<c:out value="${moneyOperation.sum}" />"</c:if>>
            <input type="date" placeholder="Дата" name="date" class="date-money-operation" required
                   <c:if test="${not empty moneyOperation}">value="<c:out value="${moneyOperation.date}" />"</c:if>>
            <input type="text" placeholder="Описание" name="description"
                   <c:if test="${not empty moneyOperation}">value="<c:out value="${moneyOperation.description}" />"</c:if>>
            <input type="submit" value="Сохранить">
        </form>
        <a class="user-form__a" href="${pageContext.request.contextPath}/history?type=${type}">Назад</a>
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

    <jsp:include page="parts/_footer.jsp" />
</t:body>
</html>
