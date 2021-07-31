
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021/7/30
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
<head>
    <title>下载文件列表</title>
</head>
<body>
    <h2>下载文件列表</h2>
    <table>
        <tr>
            <th>文件名</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${map}" var="entry">
            <tr>
            <td>
                ${entry.value}
            </td>
            <td>
            <a href="${pageContext.request.contextPath}/down?filename=${entry.key}">下载</a>
            </td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
