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
<input type="hidden" value="${timeSheet.period}" id="period">
<input type="hidden" value="${timeSheet.id}" id="timeSheetId">

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
                $('#timeSheetTable').append('<tr id="newTr'+rowCount+'"></tr>');
                $('#newTr'+rowCount).append('<td class="timeSheetTd">'+rowCount+'</td>');
                $('#newTr'+rowCount).append('<td class="timeSheetTd" id="empTd'+rowCount+'"></td>');
                $('#empTd'+rowCount).append('<select class="selEmp" id="selectEmployee'+rowCount+'"></select>');
                $.each(data,function (index, employee) {
                    $('#selectEmployee'+rowCount).append('<option value="'+employee.id+'">'+employee.shortName+'</option>');
                })
                for (let i=1; i<=31;i++) {
                    $('#newTr'+rowCount).append('<td class="timeSheetTd"><input type="text" size="4" id="inp'+rowCount+i+'"></td>');
                }
                $('#newTr'+rowCount).append('<td class="timeSheetTd"><a href="#" id="saveLink">Сохранить</a></td>');
                $('#newTr'+rowCount).append('<td class="timeSheetTd">Удалить</td>');
            },
            error: function (e) {
                $('#dResp').html(e.responseText);
            }
        });
    });
    $('#timeSheetTable').on('change','.selEmp', function () {

        let selectedValue = $('.selEmp option:selected').val();
        //alert($(this).prop("id"));
        //alert($(this).closest('tr').prop("id"));
        $(this).closest('tr').find('td').each(function (cell) {
            //alert($(this).html())
            //console.log($(this).html())
        })

        let row = $(this).closest('tr');
        let cellHtml =$('td', row).eq(0).html();

        for (let i=1; i<=31; i++) {
        }

        $.ajax({
            url: '/fuck1',
            method: 'get',
            data: ({
                EmpId: selectedValue,
                period: $('#period').val()
            }),
            dataType: 'json',
            success: function (data) {

                //$('#dResp').html(JSON.stringify(data));
                //let json = JSON.stringify(data);
                //alert($(this).closest("tr").id);
                //alert(data['day1']);

                for (let i=1; i<=31;i++) {
                    $('#inp'+cellHtml+i).val(data['day'+i])
                }
            },
            error: function (e) {
                $('#dResp').html(e.responseText);
            }
        });

    });
    $('#timeSheetTable').on('click','#saveLink', function () {

        let selectedValueEmp = $(this).closest('tr').find('.selEmp').val();
        let timeSheetId=$('#timeSheetId').val()

        let row = $(this).closest('tr');
        let cellHtml =$('td', row).eq(0).html();


        let day1 = $('#inp'+cellHtml+'1').val();
        let day2 = $('#inp'+cellHtml+'2').val();

        $.ajax({
            url: '/fuck3',
            method: 'post',
            data: {timeSheetId: timeSheetId, empId: selectedValueEmp, day1: day1, day2: day2},
            success: function (data) {
                $('#dResp').html(data);
            },
            error: function (e) {
                $('#dResp').html(e.responseText);
            }
        })

    })
</script>
</body>
</html>
