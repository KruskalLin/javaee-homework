package service.impl;

import dao.CreditCardDAO;
import service.CreditCardService;

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
@Stateless(name = "CreditCardServiceBean")
public class CreditCardServiceBean implements CreditCardService {

    @EJB
    private CreditCardDAO creditCardDAO;

    @Override
    synchronized public int getMoney(int userId) throws SQLException, NamingException, InstantiationException, IllegalAccessException {
        return creditCardDAO.findCreditCardMoneyByUserId(userId);
    }

    @Override
    synchronized public void consume(int userId, int money) throws SQLException, NamingException, InstantiationException, IllegalAccessException {
        creditCardDAO.updateCreditCardMoneyByUserId(userId, money);
    }
}
