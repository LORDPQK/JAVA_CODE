<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021/7/30
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传页面</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/moreUpload"  enctype="multipart/form-data" method="post">
        用户名：<input type="text" name="username"><br>
        文件：<input type="file" name="file1"><br>
             <input type="file" name="file2"><br>
        <input type="submit" value="上传">
    </form>>

</body>
</html>
