<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html lang="ru">
<t:head title="Дневник расходов и доходов">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/optionSelection.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</t:head>

<t:body>
    <div class="option-selection">
        <a href="${pageContext.request.contextPath}/register">Зарегистрироваться</a>
        <a href="${pageContext.request.contextPath}/entry">Войти</a>
    </div>
</t:body>

</html>
