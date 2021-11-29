<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021/11/20
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.requset.contextPath}/js/jquery-3.6.0.js"></script>
</head>
<body>
    <input type="button" value="ajax" onclick="send_json();">
    <script>
        function send_json(){
            //ajax json
            var user = {id:1,name:"boy"};
            var userJson = JSON.stringify(user);

            //contentType:表示当前请求携带的数据时json格式
            //下面是一个ajax请求，发送的数据是一个json格式字符串userJson,发送到json/test4中
            //只能用post @requestBody
            $.ajax({
                url:"${pageContext.request.contextPath}/json/test4",
                type:"post",
                data:userJson,
                contentType:"application/json",
                success:function (ret){
                    alert(ret);
                }
            })
        }
    </script>
</body>
</html>
