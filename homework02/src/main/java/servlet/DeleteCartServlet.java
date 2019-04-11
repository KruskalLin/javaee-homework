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
 * @Date: 2018/12/22
 * @Todo:
 */
@WebServlet("/mall/deleteCart")
public class DeleteCartServlet extends HttpServlet {
    private final CartDAO cartDAO = new CartDAO();
    private final GotoPageUtil util = new GotoPageUtil();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        byte[] cat = new byte[1000];
        req.getInputStream().read(cat);
        String c = new String(cat);
        c = c.trim();
        try {
            cartDAO.deleteCartGoods(Integer.parseInt(c), (int) session.getAttribute("userId"));
        } catch (Exception e) {
            e.printStackTrace();
            util.gotoErrorPage(this.getServletContext(), req, resp, "deleteCart", "cart", "未知错误");
        }

    }
}
