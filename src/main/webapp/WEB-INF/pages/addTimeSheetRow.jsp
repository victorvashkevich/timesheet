<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: vvv
  Date: 18.03.2020
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование строки табеля</title>
</head>
<body>
<form:form action="/${id_department}/${id_timesheet}/add" method="post" modelAttribute="timeSheetRow">
    <form:select path="employee"> <%-- сслыка на поле в модели timeSheetRow, преобразование строка в сотрудника делается через конвертер  --%>
        <c:forEach items="${employees}" var="emp">
            <form:option value="${emp.id}">${emp.name}</form:option>
        </c:forEach>
    </form:select>
    <input type="submit" value="Далее">
</form:form>
</body>
</html>
