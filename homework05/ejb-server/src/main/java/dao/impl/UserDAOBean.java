package dao.impl;

import dao.UserDAO;
import entity.CreditCard;
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
 * @Date: 2018/12/20
 * @Todo:
 */
@Stateless
public class UserDAOBean implements UserDAO{
    @PersistenceUnit(name = "jpa")
    private EntityManagerFactory factory;

    @PersistenceContext
    private EntityManager entityManager;

    public UserDAOBean() {
        factory = Persistence.createEntityManagerFactory("jpa");
        entityManager = factory.createEntityManager();
    }

    @Override
    synchronized public User findUserByUsername(String username) throws SQLException, NamingException {
        User user = (User) entityManager.createNativeQuery("SELECT * FROM user WHERE username = ?", User.class).setParameter(1, username).getSingleResult();
        entityManager.clear();
        return user;
    }
}
