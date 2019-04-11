package dao.impl;

import dao.CreditCardDAO;
import entity.User;
import utils.HibernateUtil;

import javax.naming.NamingException;
import javax.persistence.*;
import java.sql.SQLException;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/21
 * @Todo:
 */
public class CreditCardDAOImpl implements CreditCardDAO {


    @Override
    synchronized public void updateCreditCardMoneyByUserId(int id, int money) throws SQLException, NamingException {
        User user = (User) HibernateUtil.getSession().createSQLQuery("SELECT * FROM USER WHERE id = " + id).addEntity(User.class).uniqueResult();
        user.getCreditCard().setMoney(money);
        HibernateUtil.getSession().flush();
    }

    @Override
    synchronized public int findCreditCardMoneyByUserId(int id) throws SQLException, NamingException {
        User user = (User) HibernateUtil.getSession().createSQLQuery("SELECT * FROM USER WHERE id = " + id).addEntity(User.class).uniqueResult();
        HibernateUtil.getSession().clear();
        return user.getCreditCard().getMoney();
    }

}
