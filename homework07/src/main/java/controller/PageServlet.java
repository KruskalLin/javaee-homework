package controller;

import entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import service.GoodsService;
import utils.GotoPageUtil;
import utils.Page;

import javax.servlet.ServletConfig;
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
@Component

public class PageServlet extends HttpServlet {
    private final GotoPageUtil util = new GotoPageUtil();
    private static final int PAGE_SIZE = 3;
    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }
    @Autowired
    private GoodsService goodsService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        int pageNum = Integer.parseInt(req.getParameter("page"));
        session.setAttribute("pageNum", pageNum);
        List<Goods> goods = new ArrayList<>();
        try {
            goods = goodsService.getFilGoods((String) session.getAttribute("category"));
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
