package service.impl;

import dao.CreditCardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
public class CreditCardServiceImpl implements CreditCardService {
    @Autowired
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
