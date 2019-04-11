package servlet;

import dao.CartDAO;
import dao.CreditCardDAO;
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
 * @Date: 2018/12/22
 * @Todo:
 */
@WebServlet("/buyCart")
public class BuyAllServlet extends HttpServlet {
    private final CreditCardDAO creditCardDAO = new CreditCardDAO();
    private final CartDAO cartDAO = new CartDAO();
    private final GotoPageUtil util = new GotoPageUtil();
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
            int consume = Integer.parseInt(c);
            try{
                int money = creditCardDAO.getMoney( (int)session.getAttribute("userId"));
                if(money >= consume){
                    if(consume > 0.5 * money){
                        creditCardDAO.consume((int)session.getAttribute("userId"), consume/2);
                        util.gotoSuccessPage(this.getServletContext(), req, resp, "buyCart","goods","您得到半价优惠!");
                    }else{
                        creditCardDAO.consume((int)session.getAttribute("userId"), consume);
                        util.gotoSuccessPage(this.getServletContext(), req, resp,"buyCart","goods", "您得支付成功!");
                    }
                    cartDAO.deleteAll((int)session.getAttribute("userId"));
                }else{
                    util.gotoErrorPage(this.getServletContext(), req, resp, "buyCart","cart","您的余额不足!");
                }
            }catch (Exception e){
                e.printStackTrace();
                util.gotoErrorPage(this.getServletContext(), req, resp, "cart","buyCart","未知错误");
            }
        }
    }
}
