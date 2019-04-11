package config;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/25
 * @Todo:
 */
@WebListener
public class CounterListener implements HttpSessionAttributeListener {
    private static AtomicInteger count = new AtomicInteger(0);


    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        if ("userId".equals(se.getName())) {
            count.getAndIncrement();
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        if ("userId".equals(se.getName())) {
            count.getAndDecrement();
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    synchronized public static int getCount() {
        return count.get();
    }
}
