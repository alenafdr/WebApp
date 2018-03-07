
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored ="false" %>
<html>
<head>
    <title>List</title>
    <meta charset="UTF-8">
</head>
<body>

<jsp:include page="fragment/forms/${entity}.jsp" />

<h3>${message}</h3>

<jsp:include page="fragment/lists/${entity}.jsp" />

</body>
</html>
