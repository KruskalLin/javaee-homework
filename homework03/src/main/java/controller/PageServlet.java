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

import static utils.GoodsTemplate.getItemString;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/21
 * @Todo:
 */
@WebServlet("/mall/page")
public class PageServlet extends HttpServlet {
    private final ServiceFactory factory = new ServiceFactory();
    private final GotoPageUtil util = new GotoPageUtil();
    private static final int PAGE_SIZE = 3;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        int pageNum = Integer.parseInt(req.getParameter("page"));
        session.setAttribute("pageNum", pageNum);
        List<Goods> goods = new ArrayList<>();
        try {
            goods = factory.getGoodsService().getFilGoods((String) session.getAttribute("category"));
        } catch (Exception e) {
            e.printStackTrace();
            util.gotoErrorPage(req, resp, "page", "cart", "未知错误");
        }
        StringBuilder items = new StringBuilder();
        Page<Goods> page = new Page<>((Integer) session.getAttribute("pageNum"), PAGE_SIZE, goods.size());
        for (int i = 0; i < PAGE_SIZE && (page.getStartIndex() + i) < page.getTotalRecord(); i++) {
            items.append(getItemString(goods.get(page.getStartIndex() + i)));
        }
        resp.getWriter().write(items.toString());
    }
}
