package dao.impl;

import dao.CreditCardDAO;
import entity.User;
import utils.DatabaseUtils;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/21
 * @Todo:
 */
@Stateless
public class CreditCardDAOBean implements CreditCardDAO{

    @PersistenceUnit(name = "jpa")
    private EntityManagerFactory factory;

    @PersistenceContext
    private EntityManager entityManager;

    public CreditCardDAOBean() {
        factory = Persistence.createEntityManagerFactory("jpa");
        entityManager = factory.createEntityManager();
    }

    @Override
    synchronized public void updateCreditCardMoneyByUserId(int id, int money) throws SQLException, NamingException {
        User user = entityManager.find(User.class, id);
        user.getCreditCard().setMoney(money);
        entityManager.flush();
    }

    @Override
    synchronized public int findCreditCardMoneyByUserId(int id) throws SQLException, NamingException {
        User user = entityManager.find(User.class, id);
        entityManager.clear();
        return user.getCreditCard().getMoney();
    }

}
