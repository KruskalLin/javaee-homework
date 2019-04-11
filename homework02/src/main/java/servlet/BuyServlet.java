package servlet;

import dao.CreditCardDAO;
import utils.GotoPageUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/20
 * @Todo:
 */
@WebServlet("/mall/buy")
public class BuyServlet extends HttpServlet {
    private final CreditCardDAO creditCardDAO = new CreditCardDAO();
    private final GotoPageUtil util = new GotoPageUtil();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        byte[] cat = new byte[1000];
        req.getInputStream().read(cat);
        String c = new String(cat);
        c = c.trim();
        int consume = Integer.parseInt(c);
        try {
            int money = creditCardDAO.getMoney((int) session.getAttribute("userId"));
            if (money >= consume) {
                creditCardDAO.consume((int) session.getAttribute("userId"), consume);
                util.gotoSuccessPage(this.getServletContext(), req, resp, "buy", "goods", "您得支付成功!");
            } else {
                util.gotoErrorPage(this.getServletContext(), req, resp, "buy", "goods", "您的余额不足!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            util.gotoErrorPage(this.getServletContext(), req, resp, "buy", "goods", "未知错误");
        }

    }
}
