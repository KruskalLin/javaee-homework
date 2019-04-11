package dao;

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
public interface CreditCardDAO {
    public void updateCreditCardMoneyByUserId(int userId, int money) throws SQLException, NamingException;
    public int findCreditCardMoneyByUserId(int userId) throws SQLException, NamingException;
}
