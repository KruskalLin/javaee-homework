package service;

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
public interface CreditCardService {
    public int getMoney(int userId) throws SQLException, NamingException, InstantiationException, IllegalAccessException;
    public void consume(int userId, int money) throws SQLException, NamingException, InstantiationException, IllegalAccessException;
}
