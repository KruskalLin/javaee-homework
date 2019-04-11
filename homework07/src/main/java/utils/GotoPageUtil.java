package utils;

import org.springframework.context.annotation.Bean;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/21
 * @Todo:
 */
public class GotoPageUtil {
    public void gotoErrorPage(HttpServletRequest req, HttpServletResponse resp, String originUrl, String url, String errorMessage) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        session.setAttribute("errorMessage", errorMessage);
        session.setAttribute("returnUrl", req.getRequestURL().toString().replaceAll(originUrl, url));
        resp.sendRedirect("/mall/error");
    }

    public void gotoSuccessPage(HttpServletRequest req, HttpServletResponse resp, String originUrl, String url, String successMessage) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        session.setAttribute("successMessage", successMessage);
        session.setAttribute("returnUrl", req.getRequestURL().toString().replaceAll(originUrl, url));
        resp.sendRedirect("/mall/success");
    }

    public void gotoLoginErrorPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("loginError.jsp").forward(req,resp);
    }
}
