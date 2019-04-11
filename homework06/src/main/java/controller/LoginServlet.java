package controller;

import entity.User;
import factory.ServiceFactory;
import utils.GotoPageUtil;

import javax.servlet.RequestDispatcher;
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
 * @Date: 2018/12/17
 * @Todo:
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final ServiceFactory factory = new ServiceFactory();
    private final GotoPageUtil util = new GotoPageUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
        dispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            User user = factory.getUserService().getUserByUsername(username);
            if (user == null || !user.getPassword().equals(password)) {
                util.gotoLoginErrorPage(req, resp);
            } else {
                HttpSession session = req.getSession(true);
                session.setAttribute("userId", user.getId());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("category", "ALL");
                session.setAttribute("pageNum", 1);
                resp.sendRedirect("/mall/goods");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
