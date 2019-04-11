package factory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2019/1/17
 * @Todo:
 */

public class EJBFactory {
    public static <T> T getEJB(String jndiPath) {
        Properties jndiProps = new Properties();
        jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiProps.put("jboss.naming.client.ejb.context", true);
        try {
            Context context = new InitialContext(jndiProps);
            return (T) context.lookup(jndiPath);
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }
}