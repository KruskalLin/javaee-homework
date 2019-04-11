package servlet;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/20
 * @Todo:
 */

import dao.CartDAO;
import dao.CreditCardDAO;
import entity.CartGoods;
import entity.Goods;
import utils.GotoPageUtil;
import utils.Page;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private final CartDAO cartDAO = new CartDAO();
    private final CreditCardDAO creditCardDAO = new CreditCardDAO();
    private final GotoPageUtil util = new GotoPageUtil();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("/login");
        } else {
            ServletContext context = this.getServletContext();
            InputStream in = context.getResourceAsStream("/cart.html");
            byte[] content = new byte[10000];
            in.read(content);
            String s = new String(content);
            s = s.trim();
            s = s.replaceAll("ReplaceNameHere", (String)session.getAttribute("username"));
            s = s.replaceAll("ReplaceLogoutURLHere", req.getRequestURL().toString().replaceAll("cart", "login"));
            List<CartGoods> goods = new ArrayList<>();
            try {
                goods = cartDAO.getAllCartGoods((int) session.getAttribute("userId"));
                s = s.replaceAll("ReplaceNumHere", "" + cartDAO.getAllCartGoods((int)session.getAttribute("userId")).size());
                s = s.replaceAll("ReplaceBalanceHere", "" + creditCardDAO.getMoney((int)session.getAttribute("userId")));
            }catch (Exception e){
                e.printStackTrace();
                util.gotoErrorPage(this.getServletContext(), req, resp, "cart","cart","未知错误");
                return;
            }
            StringBuilder items = new StringBuilder();
            for(int i = 0; i < goods.size(); i++){
                items.append(getItemString(goods.get(i)));
            }
            s = s.replaceAll("423", "" + (240 + 85 * goods.size()));
            if(goods.size() > 3) {
                String r = "";
                for(int i = 5;i<=goods.size();i+=2) {
                    r += ".item:nth-child(" + i + ") {\n" +
                            "        border-top:  1px solid #E1E8EE;\n" +
                            "        border-bottom:  1px solid #E1E8EE;\n" +
                            "      }";
                }
                s = s.replaceAll("/\\*ReplaceCSSHere\\*/", r);
            }
            s = s.replaceAll("ReplaceItemHere", items.toString());

            int price = 0;
            for(int i = 0; i < goods.size(); i++){
                price += goods.get(i).getQuantity() * goods.get(i).getGoods().getPrice();
            }
            s = s.replaceAll("ReplaceTotalPriceHere", ""+ price);
            resp.getWriter().write(s);
            in.close();
        }
    }

    private String getItemString(CartGoods cartGoods){
        return " <div class=\"item\">\n" +
                "        <div class=\"buttons\">\n" +
                "          <span id=\"" + cartGoods.getGoods().getId() + "\" class=\"delete-btn\"></span>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"image\">\n" +
                "          <img src=\"materials/item.png\" alt=\"\" />\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"description\">\n" +
                "          <span>" + cartGoods.getGoods().getName() +"</span>\n" +
                "          <span>" + cartGoods.getGoods().getProperty() +"</span>\n" +
                "          <span>"+ cartGoods.getGoods().getCategories().getType() +"</span>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"quantity\">\n" +
                "          <button value=\"" + cartGoods.getGoods().getId() + "\" class=\"plus-btn\" type=\"button\" name=\"button\">\n" +
                "            <img src=\"materials/plus.svg\" alt=\"\" />\n" +
                "          </button>\n" +
                "          <input type=\"text\" name=\"name\" value=\"" + cartGoods.getQuantity() + "\">\n" +
                "          <button value=\"" + cartGoods.getGoods().getId() + "\"  class=\"minus-btn\" type=\"button\" name=\"button\">\n" +
                "            <img src=\"materials/minus.svg\" alt=\"\" />\n" +
                "          </button>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"total-price\">￥"+ cartGoods.getGoods().getPrice() * cartGoods.getQuantity() +"</div>\n" +
                "      </div>\n";
    }
}