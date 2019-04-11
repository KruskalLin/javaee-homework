package controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import service.CartService;
import utils.GotoPageUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/20
 * @Todo:
 */
@WebServlet("/mall/setQuantity")
@Component

public class SetQuantityServlet extends HttpServlet {
    private final GotoPageUtil util = new GotoPageUtil();

    @Autowired
    private CartService cartService;
    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        int goodsId = Integer.parseInt(req.getParameter("goodsId"));
        int quantity = Integer.parseInt(req.getParameter("value"));
        try {
            cartService.saveCartGoods(goodsId, (int) session.getAttribute("userId"), quantity);
        } catch (Exception e) {
            e.printStackTrace();
            util.gotoErrorPage(req, resp, "setQuantity", "cart", "未知错误");
        }
    }

}
