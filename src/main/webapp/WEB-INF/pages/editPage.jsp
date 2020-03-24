<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vvv
  Date: 02.03.2020
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:if test="${empty enterprise.name}">
        <title>Add</title>
    </c:if>

    <c:if test="${!empty enterprise.name}">
        <title>Edit</title>
    </c:if>

</head>
<body>
<c:if test="${empty enterprise.name}">
    <c:url value="/add" var = "var"/>
</c:if>
<c:if test="${!empty enterprise.name}">
    <c:url value="/edit" var = "var"/>
</c:if>

<form action="${var}" method="post">
    <c:if test="${!empty enterprise.name}">
        <input type="hidden" name="code" value="${enterprise.code}">
    </c:if>
    <label for="name">Name</label>
    <input type="text" name="name" id="name">
    <c:if test="${empty enterprise.name}">
        <input type="submit" value="Add new enterprise">
    </c:if>
    <c:if test="${!empty enterprise.name}">
        <input type="submit" value="Edit enterprise">
    </c:if>
</form>
</body>
</html>
