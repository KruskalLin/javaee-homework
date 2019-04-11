package servlet;

import dao.GoodsDAO;
import entity.Goods;
import utils.GotoPageUtil;
import utils.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/21
 * @Todo:
 */
@WebServlet("/page")
public class PageServlet extends HttpServlet {
    private final GoodsDAO goodsDAO = new GoodsDAO();
    private final GotoPageUtil util = new GotoPageUtil();
    private static final int PAGE_SIZE = 3;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("/login");
        } else {
            byte[] cat = new byte[1000];
            req.getInputStream().read(cat);
            String c = new String(cat);
            c = c.trim();
            session.setAttribute("pageNum", Integer.parseInt(c));
            List<Goods> goods = new ArrayList<>();
            try {
                goods = goodsDAO.getFilGoods((String) session.getAttribute("category"));
            }catch (Exception e){
                e.printStackTrace();
                util.gotoErrorPage(this.getServletContext(), req, resp, "page","cart","未知错误");
            }
            StringBuilder items = new StringBuilder();
            Page<Goods> page = new Page<>((Integer)session.getAttribute("pageNum"), PAGE_SIZE, goods.size());
            for(int i = 0; i < PAGE_SIZE && (page.getStartIndex() + i) < page.getTotalRecord(); i++){
                items.append(getItemString(goods.get(page.getStartIndex() + i)));
            }
            resp.getWriter().write(items.toString());
        }
    }


    private String getItemString(Goods goods){
        return "<div class=\"item\">\n" +
                "        <div class=\"buttons\">\n" +
                "            <button class=\"cart-btn cart\" value=\"" + goods.getId() + "\">放入购物车</button>\n" +
                "            <button class=\"buy-btn buy\" value=\"" + goods.getPrice() + "\">立即购买</button>\n" +
                "        </div>\n" +
                "        <div class=\"image\">\n" +
                "            <img src=\"materials/item.png\" alt=\"\"/>\n" +
                "        </div>\n" +
                "        <div class=\"description\">\n" +
                "            <span>" + goods.getName() + "</span>\n" +
                "            <span>" + goods.getProperty() + "</span>\n" +
                "            <span>" + goods.getCategories().getType() + "</span>\n" +
                "        </div>\n" +
                "        <div class=\"description\">\n" +
                "        </div>\n" +
                "        <div class=\"total-price\">￥" + goods.getPrice() + "</div>\n" +
                "    </div>\n";
    }
}
