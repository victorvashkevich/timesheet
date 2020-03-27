<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vvv
  Date: 16.03.2020
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css">
    <title>${timeSheet.department}: ${timeSheet.period} </title>
</head>
<body>
<div class="headers">${timeSheet.department}</div>
<div>Период: ${timeSheet.period}</div>
<c:set var="count" scope="page" value="1"/>
<table class="timeSheetTable">
    <tr>
        <th class="timeSheetTd">№ п/п</th>
        <th class="timeSheetTd">Сотрудник</th>
        <с:forEach var="i" begin="1" end="${timeSheet.numberOfDays}">
            <th class="timeSheetTd">${i}</th>
        </с:forEach>
        <th colspan="2" class="timeSheetTd">Действия</th>
    </tr>
    <c:forEach var="timeSheetRow" items="${timeSheetRows}" varStatus="counter">
        <tr>
            <td class="timeSheetTd">${counter.count}</td>
            <td class="timeSheetTdEmployee">${timeSheetRow.employee}</td>
            <c:forEach var="j" begin="1" end="${timeSheet.numberOfDays}">
                <c:set var="myvar" scope="page" value="day${j}"/>
                <td class="timeSheetTd">${timeSheetRow[myvar]}</td>
            </c:forEach>
            <td class="timeSheetTd">
                <a href="/${timeSheet.department.id}/${timeSheet.id}/${timeSheetRow.id}">Изменить</a>
            </td>
            <td class="timeSheetTd">
               <a href="/${timeSheet.department.id}/${timeSheet.id}/delete/${timeSheetRow.id}/">Удалить</a>
            </td>
            <c:set var="count" value="${count+1}" scope="page"/>
        </tr>
    </c:forEach>
</table>
<c:url value="/${timeSheet.department.id}/${timeSheet.id}/add" var="addurl"/>
<a href="${addurl}">Добавить строку</a>
::
<a href="/${timeSheet.department.id}">Назад к табелям</a>
</body>
</html>
