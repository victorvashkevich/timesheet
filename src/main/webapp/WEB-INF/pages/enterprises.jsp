<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vvv
  Date: 28.02.2020
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Enterprises</title>
</head>
<body>
    <title>Enterprises</title>
    <!--<div> ${myvar}</div>-->
    <table>
    <tr>
        <th>code</th>
        <th>name</th>
    </tr>
     <!--<div>${pageContext.request.userPrincipal.name}</div>-->
    <c:forEach var="enterprise" items ="${enterprisesList}">
        <tr>
            <td>${enterprise.code}</td>
            <td>${enterprise.name}</td>
            <td>
                <a href="/edit/${enterprise.code}">edit</a>
                <a href="/delete/${enterprise.code}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<h2>Add</h2>
<c:url value="/add" var="add"/>
    <a href="${add}">Add new enterprise</a>
</body>
</html>
