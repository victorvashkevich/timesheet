<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vvv
  Date: 20.03.2020
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>${employee.name}</div>
<div>${timeSheet.period}</div>
<div> часы${hoursWorked.day1}ч</div>
<c:if test="${timeSheetRow.id==0}">
    <c:url value="/${timeSheet.department.id}/${timeSheet.id}/add/${employee.id}" var="editUrl"/>
</c:if>
<c:if test="${timeSheetRow.id!=0}">
    <c:url value="/${timeSheet.department.id}/${timeSheet.id}/${timeSheetRow.id}" var="editUrl"/>
</c:if>
<form:form action="${editUrl}" method="post" modelAttribute="timeSheetRow">
    <input type="hidden" value="${employee.id}" name="employee"> <%--по employee.id через конвертер найдет сотрудника в модели --%>
    <c:if test="${timeSheetRow.id!=0}">
        <input type="hidden" value="${timeSheetRow.id}" name="id">
        <input type="hidden" value="${timeSheet.id}" name="timeSheet">
    </c:if>
    <form:input path="day1" value="${hoursWorked.day1}"/><br>
    <form:input path="day2" value="${hoursWorked.day2}"/><br>
    <form:input path="day3" value="${hoursWorked.day3}"/><br>
    <form:input path="day4" value="${hoursWorked.day4}"/><br> <%-- если value заполнено - значение берется из него, иначе из модели по атрибуту path --%>
    <form:input path="day5" value="${hoursWorked.day5}"/><br>
    <form:input path="day6" value="${hoursWorked.day6}"/><br>
    <form:input path="day7" value="${hoursWorked.day7}"/><br>
    <form:input path="day8" value="${hoursWorked.day8}"/><br>
    <form:input path="day9" value="${hoursWorked.day9}"/><br>
    <form:input path="day10" value="${hoursWorked.day10}"/><br>
    <form:input path="day11" value="${hoursWorked.day11}"/><br>
    <form:input path="day12" value="${hoursWorked.day12}"/><br>
    <form:input path="day13" value="${hoursWorked.day13}"/><br>
    <form:input path="day14" value="${hoursWorked.day14}"/><br>
    <form:input path="day15" value="${hoursWorked.day15}"/><br>
    <form:input path="day16" value="${hoursWorked.day16}"/><br>
    <form:input path="day17" value="${hoursWorked.day17}"/><br>
    <form:input path="day18" value="${hoursWorked.day18}"/><br>
    <form:input path="day19" value="${hoursWorked.day19}"/><br>
    <form:input path="day20" value="${hoursWorked.day20}"/><br>
    <form:input path="day21" value="${hoursWorked.day21}"/><br>
    <form:input path="day22" value="${hoursWorked.day22}"/><br>
    <form:input path="day23" value="${hoursWorked.day23}"/><br>
    <form:input path="day24" value="${hoursWorked.day24}"/><br>
    <form:input path="day25" value="${hoursWorked.day25}"/><br>
    <form:input path="day26" value="${hoursWorked.day26}"/><br>
    <form:input path="day27" value="${hoursWorked.day27}"/><br>
    <form:input path="day28" value="${hoursWorked.day28}"/><br>
    <form:input path="day29" value="${hoursWorked.day29}"/><br>
    <form:input path="day30" value="${hoursWorked.day30}"/><br>
    <form:input path="day31" value="${hoursWorked.day31}"/><br>
    <c:if test="${timeSheetRow.id==0}">
        <input type="submit" value="Добавить">
    </c:if>
    <c:if test="${timeSheetRow.id!=0}">
        <input type="submit" value="Изменить">
    </c:if>
</form:form>
</body>
</html>
