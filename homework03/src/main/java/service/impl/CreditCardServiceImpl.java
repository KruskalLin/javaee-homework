package service.impl;

import factory.DAOFactory;
import service.CreditCardService;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2019/1/1
 * @Todo:
 */
public class CreditCardServiceImpl implements CreditCardService {
    private final DAOFactory daoFactory = new DAOFactory();

    @Override
    synchronized public int getMoney(int userId) throws SQLException, NamingException, InstantiationException, IllegalAccessException {
        return daoFactory.getCreditCardDAO().findCreditCardMoneyByUserId(userId);
    }

    @Override
    synchronized public void consume(int userId, int money) throws SQLException, NamingException, InstantiationException, IllegalAccessException {
        daoFactory.getCreditCardDAO().updateCreditCardMoneyByUserId(userId, money);
    }
}
