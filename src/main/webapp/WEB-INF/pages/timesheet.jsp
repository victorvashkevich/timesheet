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
    <title></title>
</head>
<body>
<div>${timeSheet.department}</div>
<div>Период ${timeSheet.period}</div>
<c:set var="count" scope="page" value="1"/>
<table class="table">
    <tr>
        <th>№ п/п</th>
        <th>Сотрудник</th>
        <с:forEach var="i" begin="1" end="31">
            <th>${i}</th>
        </с:forEach>
        <th></th>
    </tr>
    <c:forEach var="timeSheetRow" items="${timeSheetRows}" varStatus="counter">
        <tr>
            <td>${counter.count}</td>
            <td>${timeSheetRow.employee}</td>
            <td>${timeSheetRow.day1}</td>
            <td>${timeSheetRow.day2}</td>
            <td>${timeSheetRow.day3}</td>
            <td>${timeSheetRow.day4}</td>
            <td>${timeSheetRow.day5}</td>
            <td>${timeSheetRow.day6}</td>
            <td>${timeSheetRow.day7}</td>
            <td>${timeSheetRow.day8}</td>
            <td>${timeSheetRow.day9}</td>
            <td>${timeSheetRow.day10}</td>
            <td>${timeSheetRow.day11}</td>
            <td>${timeSheetRow.day12}</td>
            <td>${timeSheetRow.day13}</td>
            <td>${timeSheetRow.day14}</td>
            <td>${timeSheetRow.day15}</td>
            <td>${timeSheetRow.day16}</td>
            <td>${timeSheetRow.day17}</td>
            <td>${timeSheetRow.day18}</td>
            <td>${timeSheetRow.day19}</td>
            <td>${timeSheetRow.day20}</td>
            <td>${timeSheetRow.day21}</td>
            <td>${timeSheetRow.day22}</td>
            <td>${timeSheetRow.day23}</td>
            <td>${timeSheetRow.day24}</td>
            <td>${timeSheetRow.day25}</td>
            <td>${timeSheetRow.day26}</td>
            <td>${timeSheetRow.day27}</td>
            <td>${timeSheetRow.day28}</td>
            <td>${timeSheetRow.day29}</td>
            <td>${timeSheetRow.day30}</td>
            <td>${timeSheetRow.day31}</td>
            <td>
                <a href="/${timeSheet.department.id}/${timeSheet.id}/${timeSheetRow.id}">Редактировать</a>
            </td>
            <td>
               <%-- <a href="/${timeSheet.department.id}/${timeSheet.id}/delete/${timeSheetRow.id}">Удалить</a> --%>
               <a href="/${timeSheet.department.id}/${timeSheet.id}/delete/${timeSheetRow.id}/">Удалить</a>
            </td>
            <c:set var="count" value="${count+1}" scope="page"/>
        </tr>
    </c:forEach>
</table>
<c:url value="/${timeSheet.department.id}/${timeSheet.id}/add" var="addurl"/>
<a href="${addurl}">Добавить строку</a>
<a href="/${timeSheet.department.id}">Назад к табелям</a>
</body>
</html>
