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
            <td class="timeSheetTd" id="">${counter.count}</td>
            <td class="timeSheetTdEmployee">${timeSheetRow.employee}</td>
            <c:forEach var="j" begin="1" end="${timeSheet.numberOfDays}">
                <c:set var="myvar" scope="page" value="day${j}"/>
                <td class="timeSheetTdDay">${timeSheetRow[myvar]}</td>
            </c:forEach>
            <td class="timeSheetTd">
                <%--<a href="/${timeSheet.department.id}/${timeSheet.id}/${timeSheetRow.id}" class="editLink">Изменить</a> --%>
                <a href="#" class="editLink">Изменить</a>
            </td>
            <td class="timeSheetTd">
                <a href="/${timeSheet.department.id}/${timeSheet.id}/delete/${timeSheetRow.id}/" class="deleteLink">Удалить</a>
            </td>
            <td style="display: none" id="rowId">${timeSheetRow.id}</td>
            <td style="display: none" id="empId">${timeSheetRow.employee.id}</td>
            <c:set var="count" value="${count+1}" scope="page"/>
        </tr>
    </c:forEach>
</table>

<c:url value="/${timeSheet.department.id}/${timeSheet.id}/add" var="addurl"/>

<%--<a href="${addurl}">Добавить строку</a> сделаем добавление строки на клиенте--%>
<a href="#" id="addRow">Добавить строку</a>
::
<a href="/${timeSheet.department.id}/">Назад к табелям</a>
::
<a href="/">Назад к подразделениям</a>
<div id="responseError"></div>
<script>
    $('#addRow').click(function () {

        $.ajax({
            url: '/addRow',
            method: 'get',
            data: ({
                id: $('#departmentId').val()
            }),
            dataType: 'json',
            success: function (data) {
                let rowCount = $('#timeSheetTable tr').length;
                $('#timeSheetTable').append('<tr id="newTr'+rowCount+'"></tr>');
                $('#newTr'+rowCount).append('<td class="timeSheetTd">'+rowCount+'</td>');
                $('#newTr'+rowCount).append('<td class="timeSheetTdEmployee" id="empTd'+rowCount+'"></td>');
                $('#empTd'+rowCount).append('<select class="selEmp" id="selectEmployee'+rowCount+'"></select>');
                $.each(data,function (index, employee) {
                    //$('#selectEmployee'+rowCount).append('<option value="'+employee.id+'">'+employee.shortName+'</option>');
                    $('#selectEmployee'+rowCount).append('<option value="'+employee.id+'">'+employee.name+'</option>');
                })
                for (let i=1; i<=31;i++) {
                    $('#newTr'+rowCount).append('<td class="timeSheetTdDay"><input type="text" size="4" id="inp'+rowCount+i+'"></td>');
                }
                $('#newTr'+rowCount).append('<td class="timeSheetTd"><a href="#" class="saveLink">Сохранить</a></td>');
                $('#newTr'+rowCount).append('<td class="timeSheetTd"><a href="#" class="cancelLink">Отменить</a></td>');
                $('#newTr'+rowCount).append('<td style="display: none" id="rowId"></td>');
                $('#newTr'+rowCount).append('<td style="display: none" id="empId"></td>');
            },
            error: function (e) {
                $('#responseError').html(e.responseText);
            }
        });
    });
    $('#timeSheetTable').on('change','.selEmp', function () {

        let selectedValue = $('.selEmp option:selected').val();

        let row = $(this).closest('tr');
        let cellHtml =$('td', row).eq(0).html();

        for (let i=1; i<=31; i++) {
        }

        $.ajax({
            url: '/getHours',
            method: 'get',
            data: ({
                employee: selectedValue,
                period: $('#period').val()
            }),
            dataType: 'json',
            success: function (data) {

                for (let i=1; i<=31;i++) {
                    $('#inp'+cellHtml+i).val(data['day'+i])
                }
            },
            error: function (e) {
                $('#responseError').html(e.responseText);
            }
        });

    });
    $('#timeSheetTable').on('click','.saveLink', function () {

        let selectedValueEmp = $(this).closest('tr').find('.selEmp').val();
        let timeSheetId=$('#timeSheetId').val();
        let rowIdTd = $(this).closest('tr').find('#rowId');
        let rowId = rowIdTd.html();
        let empIdTd = $(this).closest('tr').find('#empId');
        let empId = empIdTd.html();

        rowId = (rowId == '') ? 0 : rowId;
        selectedValueEmp = (empId !='') ? empId : selectedValueEmp;

        let row = $(this).closest('tr');
        let cellHtml =$('td', row).eq(0).html();

        let rowJSON = '{"employee":{"id":"'+selectedValueEmp+'"},"timeSheet": {"id":'+timeSheetId+'},"id":'+rowId+',';

        for (let i=1; i<=31; i++) {
            rowJSON=rowJSON+'"day'+i+'":"'+$('#inp'+cellHtml+i).val()+'",';
        }

        rowJSON = rowJSON.substring(0,rowJSON.length-1)+"}";

        $.ajax({
            url: '/saveRow',
            method: 'post',
            data: rowJSON,
            contentType: 'application/json',
            success: function (data) {
                for (let i=1; i<=31; i++) {
                    $('#inp'+cellHtml+i).parent().html($('#inp'+cellHtml+i).val());
                    $('#inp'+cellHtml+i).remove();
                    $('#selectEmployee'+cellHtml).parent().html($('#selectEmployee'+cellHtml+' option:selected').html());
                    $('#selectEmployee'+cellHtml+' option:selected').remove();
                }
                rowIdTd.html(data); //вернули с бэка номер строки
                empIdTd.html(selectedValueEmp);
                let saveEditLink = row.find('.saveLink');
                saveEditLink.removeClass("saveLink");
                saveEditLink.addClass("editLink");
                saveEditLink.html("Изменить");

                let cancelDeleteLink = row.find('.cancelLink');
                cancelDeleteLink.removeClass("cancelLink");
                cancelDeleteLink.addClass("deleteLink");
                row.find('.deleteLink').attr("href", "/"+$('#departmentId').val()+"/"+$('#timeSheetId').val()+"/delete/"+data+"/");
                cancelDeleteLink.html("Удалить");

            },
            error: function (e) {
                $('#responseError').html(e.responseText);
            }
        })
    })

    $('#timeSheetTable').on('click','.deleteLink', function () {
        return confirm("Удалить строку?")
    })

    $('#timeSheetTable').on('click','.editLink', function () {

        let row = $(this).closest('tr');
        let rowNumber =$('td', row).eq(0).html();
        let saveEditLink = row.find('.editLink');
        saveEditLink.removeClass("editLink");
        saveEditLink.addClass("saveLink");
        saveEditLink.html("Сохранить");

        let cancelDeleteLink = row.find('.deleteLink');
        cancelDeleteLink.removeClass("deleteLink");
        cancelDeleteLink.addClass("cancelLink");
        cancelDeleteLink.html("Отменить");

        let i = 1;
        $('td.timeSheetTdDay',row).each(function () {
            let dayVal = $(this).html();
            $(this).html("");
            $(this).append('<input type="text" size="4" id="inp'+rowNumber+i+'" value="'+dayVal+'">');
            i++;
        })
    })

    $('#timeSheetTable').on('click','.cancelLink', function () {

         let rowIdTd = $(this).closest('tr').find('#rowId');
         let rowId = rowIdTd.html();

         if (rowId=='') {
             $(this).closest('tr').remove();
         }
         else {
             let cancelLink = $(this).closest('tr').find('.cancelLink');
             cancelLink.attr('href', "/"+$('#departmentId').val()+"/"+$('#timeSheetId').val()+"/");
         }
    })
</script>
</body>
</html>
