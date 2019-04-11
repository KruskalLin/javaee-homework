package controller;

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

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/22
 * @Todo:
 */
@WebServlet("/mall/buyCart")
@Component
public class BuyAllServlet extends HttpServlet {

    private final GotoPageUtil util = new GotoPageUtil();
    @Autowired
    private CreditCardService creditCardService;
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

        int consume = Integer.parseInt(req.getParameter("total"));
        try {
            int money = creditCardService.getMoney((int) session.getAttribute("userId"));
            if (money >= consume) {
                if (consume > 0.5 * money) {
                    creditCardService.consume((int) session.getAttribute("userId"), consume / 2);
                    util.gotoSuccessPage(req, resp, "buyCart", "goods", "您得到半价优惠!");
                } else {
                    creditCardService.consume((int) session.getAttribute("userId"), consume);
                    util.gotoSuccessPage(req, resp, "buyCart", "goods", "您得支付成功!");
                }
                cartService.deleteAllCartGoods((int) session.getAttribute("userId"));
            } else {
                util.gotoErrorPage(req, resp, "buyCart", "cart", "您的余额不足!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            util.gotoErrorPage(req, resp, "cart", "buyCart", "未知错误");
        }

    }
}
