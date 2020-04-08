<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vvv
  Date: 16.03.2020
  Time: 8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet" type="text/css">
    <script src="/res/jquery-3.4.1.js"></script>
    <script src="/js/bootstrap.js"></script>
    <title>${department}</title>
</head>
<body>
<h4>Табеля подразделения ${department}</h4>
<a href="/${department.id}/add">Добавить табель</a>
::
<a href="/">Назад к подразделениям</a>
<table class="table table-hover">
    <thead class="bg-primary text-light">
        <th>№</th>
        <th>Период</th>
        <th>Сотрудники</th>
    </thead>
    <c:forEach var="ts" items="${timeSheets}">
        <tr>
            <th>${ts.id}</th>
            <td><a class="text-dark" href="/${department.id}/${ts.id}/">${ts.stringPeriod}</a></td>
            <td>${ts.employeesString}</td>
        </tr>
    </c:forEach>
</table>
<a href="/${department.id}/add">Добавить табель</a>
::
<a href="/">Назад к подразделениям</a>
</body>
</html>
