<%@tag description="Default head" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="title" required="true" %>

<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <jsp:doBody />
</head>
