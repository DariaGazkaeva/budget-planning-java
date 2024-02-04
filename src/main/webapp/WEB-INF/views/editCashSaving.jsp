<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html lang="ru">
<t:head title="Редактирование">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userForm.css">
</t:head>
<t:body>

    <jsp:include page="parts/_header.jsp" />
    <div class="user-form">

        <form action="" method="post" accept-charset="UTF-8">
            <input type="text" placeholder="Какая цель?" name="name"
                   <c:if test="${not empty cashSaving}">value="<c:out value="${cashSaving.name}" />"</c:if>>
            <input type="number" step="0.01" placeholder="Сумма" name="sum"
                   <c:if test="${not empty cashSaving}">value="<c:out value="${cashSaving.sum}" />"</c:if>>
            <input type="submit" value="Сохранить">
        </form>

        <c:if test="${not empty cashSaving}">
            <a href="${pageContext.request.contextPath}/delete-cash-saving?id=${cashSaving.id}">Удалить</a>
        </c:if>

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

    <jsp:include page="parts/_footer.jsp" />
</t:body>
</html>
