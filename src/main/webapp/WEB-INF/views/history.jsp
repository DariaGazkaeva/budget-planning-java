<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html lang="ru">
<t:head title="Записи">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tables.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userForm.css">
</t:head>

<t:body>
    <jsp:include page="parts/_header.jsp" />
    <div class="user-form">
        <p>Посмотреть за другой период:</p>
        <form action="" method="POST">
            <input type="date" name="start"
                <c:if test="${not empty startDate}">
                    value="${startDate}"
                </c:if>
            >
            <input type="date" name="end"
                <c:if test="${not empty endDate}">
                       value="${endDate}"
                </c:if>
            >
            <input type="submit" value="Посмотреть">
        </form>
    </div>

    <c:if test="${empty errors}">
        <table class="money-operation">
            <tr>
                <th>Категория</th>
                <th>Сумма</th>
                <th>Дата</th>
                <th>Описание</th>
                <th></th>
                <th></th>
            </tr>
            <c:set var="count" value="0" />
            <c:forEach items="${moneyOperations}" var="operation">
                <tr>
                    <c:forEach items="${categories}" var="category" begin="${count}" end="${count}">
                        <th>${category.name}</th>
                        <c:set var="count" value="${count + 1}" />
                    </c:forEach>

                    <th>${operation.sum}</th>
                    <th>${operation.date}</th>
                    <th>${operation.description}</th>
                    <th>
                        <a href="${pageContext.request.contextPath}/edit-money-operation?id=${operation.id}">Редактировать</a>
                    </th>
                    <th>
                        <a href="${pageContext.request.contextPath}/delete-money-operation?id=${operation.id}">Удалить</a>
                    </th>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${not empty errors}">
        <div class="user-form">
            <c:forEach var="error" items="${errors}">
                <p>
                    <c:out value="${error}"/>
                </p>
            </c:forEach>
        </div>
    </c:if>

    <a class="user-form user-form__a" href="${pageContext.request.contextPath}/profile">В профиль</a>

    <jsp:include page="parts/_footer.jsp" />
</t:body>
</html>
