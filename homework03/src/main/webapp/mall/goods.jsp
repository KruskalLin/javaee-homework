<%--
  Created by IntelliJ IDEA.
  User: Popping Lim
  Date: 2018/12/31
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="customTags" uri="/WEB-INF/tags.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Goods List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/goods.css">
    <meta name="robots" content="noindex,follow"/></head>
<body>
<customTags:checkLogin/>
<%@ include file="header.jsp" %>
<div class="shopping-cart">
    <div class="title">
        商品列表
        <div id="cart" class="cart-btn" style="float: right;background: #ffbe76">
            前往购物车
        </div>
    </div>
    <div id="category">
        <ul class="classify">
            <li><a onclick="cat('DailyUse')">生活用品</a></li>
            <li><a onclick="cat('Books')">书籍课本</a></li>
            <li><a onclick="cat('Electronics')">电子商品</a></li>
            <li><a onclick="cat('Sports')">运动健身</a></li>
        </ul>
    </div>
    <div id="items"></div>
    <ul id="pagination-demo" class="pagination-sm"></ul>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/jquery.twbsPagination.min.js"></script>
<script type="text/javascript">
    $('#pagination-demo').twbsPagination({
        totalPages: <% out.print(session.getAttribute("allPages"));%>,
        visiblePages: 5,
        next: 'Next',
        prev: 'Prev',
        onPageClick: function (event, page) {
            $.ajax({
                url: './page?page=' + page,
                type: 'POST',
                processData: false,
                dataType: "text",
                success: function(data) {
                $("#items").html(data)
                $('.cart').on('click', function(e) {
                    e.preventDefault();
                    $.post('./putCart', 'val=' + $(this).val(), function(data) {
                        window.location.reload()
                    });
                });
                $('.buy').on('click', function(e) {
                    e.preventDefault();
                    $.post('./buy', 'val=' + $(this).val(), function(data) {
                        document.write(data)
                    });
                });
            }
        })
        }
    });


    $('#cart').on('click', function(e) {
        e.preventDefault();
        $(location).attr('href', '${pageContext.request.contextPath}/mall/cart');
    });

    function cat(categories) {
        $.post('./category?cat=' + categories, '', function(data) {
            window.location.reload()
        })
    }
</script>

</body>
</html>
