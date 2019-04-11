<%--
  Created by IntelliJ IDEA.
  User: Popping Lim
  Date: 2018/12/31
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>支付成功</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" charset="utf-8"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/box.css">
</head>
<body>
<div id="success-box">
    <div class="face">
        <div class="eye"></div>
        <div class="eye right"></div>
        <div class="mouth happy"></div>
    </div>
    <div class="shadow scale"></div>
    <div class="message"><h1 class="alert">支付成功</h1><p class="money"><% out.print(session.getAttribute("successMessage")); %></p></div>
    <button class="button-box" onclick="direct()"><h1 class="green">返回上个页面</h1></button>
</div>
<script type="text/javascript">
    function direct() {
        $(location).attr('href', '<% out.print(session.getAttribute("returnUrl"));%>');
    }
</script>
</body>
</html>
