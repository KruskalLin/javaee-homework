package utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public void gotoErrorPage( ServletContext context, HttpServletRequest req, HttpServletResponse resp, String originUrl, String url, String errorMessage) throws IOException {
        InputStream in = context.getResourceAsStream("/error.html");
        byte[] content = new byte[10000];
        in.read(content);
        String s = new String(content);
        s = s.trim();
        s = s.replaceAll("ReplaceCodeHere", errorMessage);
        s = s.replaceAll("ReplaceURLHere", req.getRequestURL().toString().replaceAll(originUrl, url));
        resp.getWriter().write(s);
        in.close();
    }

    public void gotoSuccessPage( ServletContext context, HttpServletRequest req, HttpServletResponse resp, String originUrl, String url, String errorMessage) throws IOException {
        InputStream in = context.getResourceAsStream("/success.html");
        byte[] content = new byte[10000];
        in.read(content);
        String s = new String(content);
        s = s.trim();
        s = s.replaceAll("ReplaceCodeHere", errorMessage);
        s = s.replaceAll("ReplaceURLHere", req.getRequestURL().toString().replaceAll(originUrl, url));
        resp.getWriter().write(s);
        in.close();
    }
}
