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
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css">
    <title>${employee.name}:${timeSheet.period} </title>
</head>
<body>

<div class="headers">${employee.name}</div>
<div>Период: ${timeSheet.stringPeriod}</div>
<br>
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
    <table>
    <c:forEach var="i" begin="1" end="${timeSheet.numberOfDays}">
       <c:set var="number" scope="page" value="day${i}"/>
        <tr>
            <td>
                <label for="dayOfMonth">${i}</label>
            </td>
            <td>
                <form:input path="${number}" value="${hoursWorked[number]}" id="dayOfMonth"/><br> <%-- если value заполнено - значение берется из него, иначе из модели по атрибуту path --%>
            </td>
        </tr>
    </c:forEach>
    </table>
    <c:if test="${timeSheetRow.id==0}">
        <input type="submit" value="Добавить">
    </c:if>
    <c:if test="${timeSheetRow.id!=0}">
        <input type="submit" value="Изменить">
    </c:if>
</form:form>
</body>
</html>
