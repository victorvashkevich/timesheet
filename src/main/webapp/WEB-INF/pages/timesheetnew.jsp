<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <script src="/res/jquery-3.4.1.js"></script>
    <title>${timeSheet.department}: ${timeSheet.period} </title>
</head>
<body>
<input type="hidden" value="${timeSheet.department.id}" id="departmentId">
<div class="headers">${timeSheet.department}</div>
<div>Период: ${timeSheet.stringPeriod}</div>
<c:set var="count" scope="page" value="1"/>
<table class="timeSheetTable" id="timeSheetTable">
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
<a href="${addurl}" id="add1">Добавить строку</a>
::
<a href="/${timeSheet.department.id}/">Назад к табелям</a>
::
<a href="/">Назад к подразделениям</a>

<input type="submit" id="addRow" value="click">
<div id="resp"></div>
<div id="dResp"></div>
<script>
    $('#addRow').click(function () {
        $.ajax({
            url: '/fuck',
            method: 'get',
            data: ({
                id: $('#departmentId').val()
            }),
           // contentType: 'application/json',
            //mimeType: 'application/json',
            dataType: 'json',
            success: function (data) {
              //  $('#resp').html(data);
                //$('#dResp').html($('#departmentId').val());
                //let json = JSON.stringify(data);
                //$('#resp').html(json);
                let rowCount = $('#timeSheetTable tr').length;
                $('#timeSheetTable').append('<tr id="newTr'+(rowCount+1)+'"></tr>');
                $('#newTr'+(rowCount+1)).append('<td class="timeSheetTd">'+rowCount+'</td>');
                $('#newTr'+(rowCount+1)).append('<td class="timeSheetTd" id="empTd'+(rowCount+1)+'"></td>');
                $('#empTd'+(rowCount+1)).append('<select id="selectEmployee'+(rowCount+1)+'"></select>');
                $.each(data,function (index, employee) {
                    $('#selectEmployee'+(rowCount+1)).append('<option value="'+employee.id+'">'+employee.shortName+'</option>');
                })
                for (let i=1; i<=31;i++) {
                    $('#newTr'+(rowCount+1)).append('<td class="timeSheetTd">'+i+'</td>');
                }
                $('#newTr'+(rowCount+1)).append('<td class="timeSheetTd">Изменить</td>');
                $('#newTr'+(rowCount+1)).append('<td class="timeSheetTd">Удалить</td>');
            },
            error: function (e) {
                $('#dResp').html(e.responseText);
            }
        });
    });
</script>
</body>
</html>
