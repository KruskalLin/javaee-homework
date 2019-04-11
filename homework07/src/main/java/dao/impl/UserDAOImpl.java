package dao.impl;

import dao.UserDAO;
import entity.User;
import org.springframework.stereotype.Repository;
import utils.HibernateUtil;

import javax.naming.NamingException;
import javax.persistence.*;
import java.sql.SQLException;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/20
 * @Todo:
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Override
    synchronized public User findUserByUsername(String username) throws SQLException, NamingException {
        User user = (User) HibernateUtil.getSession().createSQLQuery("SELECT * FROM user WHERE username = " + "'" + username + "'").addEntity(User.class).uniqueResult();
        HibernateUtil.getSession().clear();
        return user;
    }
}
