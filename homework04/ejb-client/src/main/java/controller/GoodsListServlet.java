package controller;

import entity.Goods;
import factory.ServiceFactory;
import utils.GotoPageUtil;
import utils.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    private final ServiceFactory serviceFactory = new ServiceFactory();
    private final GotoPageUtil util = new GotoPageUtil();
    private static final int PAGE_SIZE = 3;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        List<Goods> goods = new ArrayList<>();
        try {
            goods = serviceFactory.getGoodsService().getFilGoods((String) session.getAttribute("category"));
        } catch (Exception e) {
            e.printStackTrace();
            util.gotoErrorPage(req, resp, "goods", "goods", "未知错误");
            return;
        }
        Page<Goods> page = new Page<>((Integer) session.getAttribute("pageNum"), PAGE_SIZE, goods.size());
        session.setAttribute("allPages", page.getTotalPage());
        req.getRequestDispatcher("goods.jsp").forward(req, resp);
    }


}