package service.impl;

import dao.UserDAO;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    @Override
    synchronized public User getUserByUsername(String username) throws SQLException, NamingException, InstantiationException, IllegalAccessException {
        return userDAO.findUserByUsername(username);
    }
}
