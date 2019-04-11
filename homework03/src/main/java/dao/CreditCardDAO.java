package dao;

import utils.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2019/1/1
 * @Todo:
 */
public interface CreditCardDAO {
    public void updateCreditCardMoneyByUserId(int userId, int money) throws SQLException, NamingException;
    public int findCreditCardMoneyByUserId(int userId) throws SQLException, NamingException;
}
