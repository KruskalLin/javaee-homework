package utils;
import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2019/1/18
 * @Todo:
 */

public class HibernateUtil {
    private static Session session;

    public static Session getSession() {
        if (session != null){
            return session;
        }
        Configuration config = new Configuration().configure(ClassLoader.getSystemResource("hibernate.cfg.xml"));

        session = config.buildSessionFactory().openSession();
        return session;
    }
}