package controller;

import factory.ServiceFactory;
import utils.GotoPageUtil;

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
@WebServlet("/mall/buy")
public class BuyServlet extends HttpServlet {
    private final GotoPageUtil util = new GotoPageUtil();
    private final ServiceFactory factory = new ServiceFactory();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        int consume = Integer.parseInt(req.getParameter("val"));
        try {
            int money = factory.getCreditCardService().getMoney((int) session.getAttribute("userId"));
            if (money >= consume) {
                factory.getCreditCardService().consume((int) session.getAttribute("userId"), consume);
                util.gotoSuccessPage(req, resp, "buy", "goods", "您得支付成功!");
            } else {
                util.gotoErrorPage(req, resp, "buy", "goods", "您的余额不足!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            util.gotoErrorPage(req, resp, "buy", "goods", "未知错误");
        }

    }
}
