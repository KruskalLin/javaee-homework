<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Goods" %>
<%@ page import="static utils.GoodsTemplate.getItemString" %>
<%@ page import="entity.CartGoods" %><%--
  Created by IntelliJ IDEA.
  User: Popping Lim
  Date: 2018/12/31
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="customTags" uri="http://mycompany.com" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Shopping Cart</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" charset="utf-8"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
    <meta name="robots" content="noindex,follow"/>
    <style>
        <%
        for (int i = 5; i <= (int)session.getAttribute("cartGoodsNum"); i += 2) {
            out.print(".item:nth-child(" + i + ") {\n" +
            "        border-top:  1px solid #E1E8EE;\n" +
            "        border-bottom:  1px solid #E1E8EE;\n" +
            "      }");
        }
        %>
    </style>
</head>
<body>
<customTags:checkLogin/>
<%@ include file="header.jsp" %>
<div class="shopping-cart" style="height: <% out.print((240 + 85 * (int)session.getAttribute("cartGoodsNum")));%>">
    <div class="title">
        购物车
        <div id="buy" class="cart-btn" style="float: right;">
            购买
        </div>
        <div style="float: right;display: flex;margin-right: 30px;">
            总价:
            <p id="total" style="font-weight: bold">￥<% out.print(session.getAttribute("totalPrice"));%></p>
        </div>
    </div>
    <%
        List<CartGoods> goods = new ArrayList<>();
        try {
            goods = new ServiceFactory().getCartService().getAllCartGoods((int) session.getAttribute("userId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < goods.size(); i++) {
            out.println(getItemString(goods.get(i)));
        }
    %>
</div>

<script type="text/javascript">
    var total = "<% out.print(session.getAttribute("totalPrice"));%>";

    $('.minus-btn').on('click', function (e) {
        e.preventDefault();
        var $this = $(this);
        var $input = $this.closest('div').find('input');
        var value = parseInt($input.val());

        if (value > 1) {
            value = value - 1;
        }
        $.post('./setQuantity?goodsId=' + $this.val() + '&value=' + value, '', function (data) {
            window.location.reload()
        })

    });

    $('.plus-btn').on('click', function (e) {
        e.preventDefault();
        var $this = $(this);
        var $input = $this.closest('div').find('input');
        var value = parseInt($input.val());

        if (value < 5) {
            value = value + 1;
        } else {
            value = 5;
        }

        $.post('./setQuantity?goodsId=' + $this.val() + '&value=' + value, '', function (data) {
            window.location.reload()
        })
    });

    $('.delete-btn').on('click', function (e) {
        e.preventDefault();
        var $this = $(this);
        $.post('./deleteCart?id=' + $this.attr('id'), '', function (data) {
            window.location.reload()
        })

    });

    $('#buy').on('click', function (e) {
        e.preventDefault();
        $.post('./buyCart?total=' + total, '', function (data) {
            document.write(data)
        })
    });


</script>
</body>
</html>
