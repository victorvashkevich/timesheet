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
    <title>${department}</title>
</head>
<body>
<div class="headers">Табеля подразделения ${department}</div>
<table class="timeSheetTable">
    <tr>
        <th class="timeSheetTd">№</th>
        <th>Период</th>
        <th class="timeSheetTd">Сотрудники</th>
    </tr>
    <c:forEach var="ts" items="${timeSheets}">
        <tr>
            <td class="timeSheetTd">${ts.id}</td>
            <td class="timeSheetTdPeriod"><a href="/${department.id}/${ts.id}/">${ts.stringPeriod}</a></td>
            <td class="timeSheetTdEmployee">${ts.employeesString}</td>
        </tr>
    </c:forEach>
</table>
<a href="/${department.id}/add">Добавить табель</a>
::
<a href="/">Назад к подразделениям</a>
</body>
</html>
