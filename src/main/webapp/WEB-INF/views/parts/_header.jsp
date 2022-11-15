<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <img class="logo" src="${pageContext.request.contextPath}/img/logo.png" alt="Логотип">
    <nav class="header__nav">
        <ul class="nav__ul">
            <li class="nav__li"><a href="${pageContext.request.contextPath}/add-money-operation?type=income" class="nav__a">
                Добавить доход
            </a></li>
            <li class="nav__li"><a href="${pageContext.request.contextPath}/add-money-operation?type=outcome" class="nav__a">
                Добавить расход
            </a></li>
            <li class="nav__li"><a href="${pageContext.request.contextPath}/add-cash-saving" class="nav__a">
                Добавить сбережение
            </a></li>
            <li class="nav__li"><a href="${pageContext.request.contextPath}/history?type=income" class="nav__a">
                История доходов
            </a></li>
            <li class="nav__li"><a href="${pageContext.request.contextPath}/history?type=outcome" class="nav__a">
                История расходов
            </a></li>
        </ul>
    </nav>
    <a href="${pageContext.request.contextPath}/logout" class="nav__logout">
        Выход
    </a>
</header>
