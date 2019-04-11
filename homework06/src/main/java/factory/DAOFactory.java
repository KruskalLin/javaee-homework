package factory;

import dao.CartDAO;
import dao.CreditCardDAO;
import dao.GoodsDAO;
import dao.UserDAO;
import dao.impl.*;
import utils.Singleton;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2019/1/1
 * @Todo:
 */
public class DAOFactory {
    public CartDAO getCartDAO() throws IllegalAccessException, InstantiationException {
        return Singleton.getInstance(CartDAOImpl.class);
    }
    public CreditCardDAO getCreditCardDAO() throws IllegalAccessException, InstantiationException {
        return Singleton.getInstance(CreditCardDAOImpl.class);
    }
    public GoodsDAO getGoodsDAO() throws IllegalAccessException, InstantiationException {
        return Singleton.getInstance(GoodsDAOImpl.class);
    }
    public UserDAO getUserDAO() throws IllegalAccessException, InstantiationException {
        return Singleton.getInstance(UserDAOImpl.class);
    }


}
