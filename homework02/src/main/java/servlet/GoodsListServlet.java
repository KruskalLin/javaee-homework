package servlet;

import config.CounterListener;
import dao.CartDAO;
import dao.CreditCardDAO;
import dao.GoodsDAO;
import entity.Goods;
import utils.GotoPageUtil;
import utils.Page;

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

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/20
 * @Todo:
 */
@WebServlet("/mall/goods")
public class GoodsListServlet extends HttpServlet {
    private final GoodsDAO goodsDAO = new GoodsDAO();
    private final CartDAO cartDAO = new CartDAO();
    private final CreditCardDAO creditCardDAO = new CreditCardDAO();
    private final GotoPageUtil util = new GotoPageUtil();
    private static final int PAGE_SIZE = 3;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        ServletContext context = this.getServletContext();
        InputStream in = context.getResourceAsStream("/goods.html");
        byte[] content = new byte[10000];
        in.read(content);
        String s = new String(content);
        s = s.trim();
        s = s.replaceAll("ReplacePeopleHere",""+CounterListener.getCount());
        s = s.replaceAll("ReplaceNameHere", (String) session.getAttribute("username"));
        s = s.replaceAll("ReplaceLogoutURLHere", req.getRequestURL().toString().replaceAll("mall/goods", "login"));
        s = s.replaceAll("ReplaceCartURLHere", req.getRequestURL().toString().replaceAll("goods", "cart"));
        List<Goods> goods = new ArrayList<>();
        try {
            goods = goodsDAO.getFilGoods((String) session.getAttribute("category"));
            s = s.replaceAll("ReplaceNumHere", "" + cartDAO.getAllCartGoods((int) session.getAttribute("userId")).size());
            s = s.replaceAll("ReplaceBalanceHere", "" + creditCardDAO.getMoney((int) session.getAttribute("userId")));
        } catch (Exception e) {
            e.printStackTrace();
            util.gotoErrorPage(this.getServletContext(), req, resp, "goods", "goods", "未知错误");
            return;
        }
        Page<Goods> page = new Page<>((Integer) session.getAttribute("pageNum"), PAGE_SIZE, goods.size());
        s = s.replaceAll("allPages", "" + page.getTotalPage());
        resp.getWriter().write(s);
        in.close();
    }


}