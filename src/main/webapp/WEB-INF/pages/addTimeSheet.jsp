<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vvv
  Date: 17.03.2020
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>Добавление нового табеля</div>
<c:url value="/${id_department}/add" var="addurl"/>
<form action="${addurl}" method="post">
    <label for="period">Период</label>
    <input type="hidden" value="${id_department}" name="department">
    <input type="text" name="period" id="period"> <!--name должно совпадать с полями в модели-->
    <input type="submit" value="Добавить">
</form>
</body>
</html>
