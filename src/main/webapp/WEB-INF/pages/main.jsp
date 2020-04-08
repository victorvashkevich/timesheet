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
    <script src="/res/js/bootstrap.js"></script>
    <title>Табель:${username}</title>
</head>
<body>
<div class="headers">${username}</div>
<div>Доступные подразделения: </div>
<table>
    <c:forEach var="userdepartment" items="${userDepartments}">
    <tr>
        <!--<td>${userdepartment.code}</td>
            <td>${userdepartment.name}</td>-->
        <td>${userdepartment.code}</td>
        <!--<td><a href="/timesheets/${userdepartment.id}">${userdepartment.name}</a></td>-->
        <td><a href="/${userdepartment.id}/">${userdepartment.name}</a></td>
    </tr>
</c:forEach>
</table>
<br>
<table>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.name}</td>
            <td>${user.domain}</td>
        </tr>
    </c:forEach>
</table>
<button type="button" class="btn btn-primary" data-toggle="popover"
        title="Сообщение" data-content="Ура, Bootstrap 4 работает">
    Поднеси ко мне курсор
</button>
<script>
    $(function () {
        $('[data-toggle="popover"]').popover({trigger:'hover'});
    });
</script>
</body>
</html>
