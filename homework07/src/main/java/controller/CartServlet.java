package controller;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/20
 * @Todo:
 */

import entity.CartGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import service.CartService;
import service.CreditCardService;
import utils.GotoPageUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/mall/cart")
@Component
public class CartServlet extends HttpServlet {
    private final GotoPageUtil util = new GotoPageUtil();
    @Autowired
    private CartService cartService;
    @Autowired
    private CreditCardService creditCardService;
    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        List<CartGoods> goods = new ArrayList<>();
        try {
            goods = cartService.getAllCartGoods((int) session.getAttribute("userId"));
            session.setAttribute("cartGoodsNum", goods.size());
            session.setAttribute("balance", creditCardService.getMoney((int) session.getAttribute("userId")));
        } catch (Exception e) {
            e.printStackTrace();
            util.gotoErrorPage(req, resp, "cart", "cart", "未知错误");
            return;
        }
        int price = 0;
        for (int i = 0; i < goods.size(); i++) {
            price += goods.get(i).getQuantity() * goods.get(i).getGoods().getPrice();
        }
        session.setAttribute("totalPrice", price);
        req.getRequestDispatcher("cart.jsp").forward(req, resp);

    }
}