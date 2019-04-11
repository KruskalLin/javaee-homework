package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
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
 * @Date: 2018/12/20
 * @Todo:
 */
@WebServlet("/mall/buy")
@Component
public class BuyServlet extends HttpServlet {
    private final GotoPageUtil util = new GotoPageUtil();

    @Autowired
    private CreditCardService creditCardService;
    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        int consume = Integer.parseInt(req.getParameter("val"));
        try {
            int money = creditCardService.getMoney((int) session.getAttribute("userId"));
            if (money >= consume) {
                creditCardService.consume((int) session.getAttribute("userId"), consume);
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
