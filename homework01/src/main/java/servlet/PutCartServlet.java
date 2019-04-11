package servlet;

import dao.CartDAO;
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
@WebServlet("/putCart")
public class PutCartServlet extends HttpServlet{
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
            try {
                cartDAO.saveCartGoods(Integer.parseInt(c), (int) session.getAttribute("userId"), -1);
            }catch (Exception e){
                e.printStackTrace();
                util.gotoErrorPage(this.getServletContext(), req, resp, "putCart","goods","未知错误");
            }
        }
    }
}
