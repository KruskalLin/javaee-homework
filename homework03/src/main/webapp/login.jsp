<%--
  Created by IntelliJ IDEA.
  User: Popping Lim
  Date: 2018/12/31
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <meta name="robots" content="noindex,follow"/>
    <title>登入</title>
</head>
<body>
<div class="login-page">
    <div class="form">
        <form class="login-form" action="./login" method="post">
            <input id="username" name="username" type="text" placeholder="用户名"/>
            <input id="passowrd" name="password" type="password" placeholder="密码"/>
            <button type="submit">登入</button>
        </form>
    </div>
</div>
</body>
</html>
