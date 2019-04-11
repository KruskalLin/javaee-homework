package service.impl;

import entity.User;
import factory.DAOFactory;
import service.UserService;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2019/1/1
 * @Todo:
 */
public class UserServiceImpl implements UserService{
    private final DAOFactory daoFactory = new DAOFactory();

    @Override
    synchronized public User getUserByUsername(String username) throws SQLException, NamingException, InstantiationException, IllegalAccessException {
        return daoFactory.getUserDAO().findUserByUsername(username);
    }
}
