package tag;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/31
 * @Todo:
 */
public class CheckLogin extends BodyTagSupport {
    @Override
    public int doStartTag() throws JspException {
        if (pageContext.getSession().getAttribute("userId") == null) {
            try {
                ((HttpServletResponse) pageContext.getResponse()).sendRedirect("/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SKIP_PAGE;
    }
}
