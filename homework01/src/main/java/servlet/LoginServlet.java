package servlet;

import dao.UserDAO;
import entity.User;
import utils.GotoPageUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/17
 * @Todo:
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    private final UserDAO userDAO = new UserDAO();
    private final GotoPageUtil util = new GotoPageUtil();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/login.html");
        dispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            User user = userDAO.getUserByUsername(username);
            if (user == null || !user.getPassword().equals(password)) {
                util.gotoErrorPage(this.getServletContext(), req, resp,"login","login", "未找到用户名或密码错误");
            } else {
                HttpSession session = req.getSession(true);
                session.setAttribute("userId", user.getId());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("category", "ALL");
                session.setAttribute("pageNum", 1);
                resp.sendRedirect("/goods");
            }
        } catch (Exception e) {
            e.printStackTrace();
            util.gotoErrorPage(this.getServletContext(), req, resp, "login","login", "未知错误");
        }
    }

}
