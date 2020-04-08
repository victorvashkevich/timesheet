<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vvv
  Date: 28.02.2020
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet" type="text/css">
    <script src="/res/jquery-3.4.1.js"></script>
    <script src="/js/bootstrap.js"></script>
    <title>Табель:${username}</title>
</head>
<body>
<h4>${username}</h4>
<table class="table table-hover">
    <thead class="bg-primary text-light">
        <th>Код</th>
        <th>Подразделение</th>
    </thead>
    <c:forEach var="userdepartment" items="${userDepartments}">
    <tr>
        <td>${userdepartment.code}</td>
        <td><a class="text-dark" href="/${userdepartment.id}/">${userdepartment.name}</a></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
