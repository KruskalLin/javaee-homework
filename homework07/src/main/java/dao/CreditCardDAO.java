package dao;

import org.springframework.stereotype.Repository;

import javax.naming.NamingException;
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
