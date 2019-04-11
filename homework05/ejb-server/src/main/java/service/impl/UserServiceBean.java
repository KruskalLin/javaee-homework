package service.impl;

import dao.UserDAO;
import entity.User;
import service.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2019/1/1
 * @Todo:
 */
@Stateless(name = "UserServiceBean")
public class UserServiceBean implements UserService{
    @EJB
    private UserDAO userDAO;

    @Override
    synchronized public User getUserByUsername(String username) throws SQLException, NamingException, InstantiationException, IllegalAccessException {
        return userDAO.findUserByUsername(username);
    }
}
