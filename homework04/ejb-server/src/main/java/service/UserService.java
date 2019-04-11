package service;

import entity.User;

import javax.ejb.Remote;
import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2019/1/1
 * @Todo:
 */
@Remote
public interface UserService {
    public User getUserByUsername(String username) throws SQLException, NamingException, InstantiationException, IllegalAccessException;
}
