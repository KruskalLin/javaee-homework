<%--
  Created by IntelliJ IDEA.
  User: Popping Lim
  Date: 2019/1/2
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>错误啦</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" charset="utf-8"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/box.css">
</head>
<body>
<div id="error-box">
    <div class="face2">
        <div class="eye"></div>
        <div class="eye right"></div>
        <div class="mouth sad"></div>
    </div>
    <div class="shadow move"></div>
    <div class="message"><h1 class="alert">出错啦</h1><p class="money">未找到用户名或密码错误</div>
    <button class="button-box" onclick="direct()"><h1 class="red">回到上一页</h1></button>
</div>
<script type="text/javascript">
    function direct() {
        $(location).attr('href', '${pageContext.request.contextPath}/login');
    }
</script>
</body>
</html>
