package dao;

import entity.User;

import javax.ejb.Local;
import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2019/1/1
 * @Todo:
 */
@Local
public interface UserDAO {
    public User findUserByUsername(String username) throws SQLException, NamingException;
}
