<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021/11/29
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table width="500px" align="center" border="1px">
        <tr>
            <td>id</td>
            <td>username</td>
            <td>password</td>
            <td>gender</td>
            <td>regist_time</td>
        </tr>
    </table>

    <tbody>
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.gender}</td>
                <td>${user.regist_time}</td>
            </tr>
        </c:forEach>
    </tbody>

</body>
</html>
