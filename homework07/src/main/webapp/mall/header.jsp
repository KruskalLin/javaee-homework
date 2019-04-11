<%@ page import="config.CounterListener" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="service.CartService"%>
<%@ page import="org.springframework.beans.factory.annotation.Value"%>
<%@ page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page import="org.springframework.web.context.support.SpringBeanAutowiringSupport"%>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="entity.CreditCard" %>
<%@ page import="service.CreditCardService" %>
<%@ page import="entity.Cart" %><%--
  Created by IntelliJ IDEA.
  User: Popping Lim
  Date: 2018/12/31
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%!
    @Override
    public void jspInit()
    {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                getServletConfig().getServletContext());
    }
    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private CartService cartService;
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/goods.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>
<header>
    <div class="header-left">
        <p style="color: white;">现存余额: <% try {
            out.print(creditCardService.getMoney((Integer) session.getAttribute("userId")));
        } catch (Exception e) {
            e.printStackTrace();
        } %></p>
    </div>
    <div class="header-right">
        <div  class="header-right-child" >
            <p><% out.print(CounterListener.getCount()); %> 人在线</p>
        </div>
        <div class="header-right-child" style="padding-right: 10px">
            <p id="carts-num"><% try {
                out.print(cartService.getAllCartGoods((Integer) session.getAttribute("userId")).size());
            } catch (Exception e) {
                e.printStackTrace();
            } %></p>
        </div>
        <div class="header-right-child">
            <p>商品在购物车中</p>
        </div>
        <div class="header-right-child">
            <p id="name"><%out.print(session.getAttribute("username"));%></p>
        </div>
        <div class="header-right-child">
            <a style="text-decoration:none;cursor: pointer;" onclick="logout()"><p style="font-weight: bold;color: #3dd28d">退出</p></a>
        </div>
    </div>
</header>
<script type="text/javascript">
    function logout() {
        $.post('/logout', function(data) {
            $(location).attr('href', '${pageContext.request.contextPath}/login');
        })
    }
</script>
</body>
</html>
