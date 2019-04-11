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

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/20
 * @Todo:
 */
@WebServlet("/mall/goods")
@Component
public class GoodsListServlet extends HttpServlet {
    private final GotoPageUtil util = new GotoPageUtil();
    private static final int PAGE_SIZE = 3;
    @Autowired
    private GoodsService goodsService;
    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        List<Goods> goods = new ArrayList<>();
        try {
            goods = goodsService.getFilGoods((String) session.getAttribute("category"));
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