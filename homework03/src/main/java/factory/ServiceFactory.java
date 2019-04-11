package factory;

import service.CartService;
import service.CreditCardService;
import service.GoodsService;
import service.UserService;
import service.impl.CartServiceImpl;
import service.impl.CreditCardServiceImpl;
import service.impl.GoodsServiceImpl;
import service.impl.UserServiceImpl;
import utils.Singleton;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/31
 * @Todo:
 */
public class ServiceFactory {
    public CartService getCartService() throws IllegalAccessException, InstantiationException {
        return Singleton.getInstance(CartServiceImpl.class);
    }
    public CreditCardService getCreditCardService() throws IllegalAccessException, InstantiationException {
        return Singleton.getInstance(CreditCardServiceImpl.class);
    }
    public GoodsService getGoodsService() throws IllegalAccessException, InstantiationException {
        return Singleton.getInstance(GoodsServiceImpl.class);
    }
    public UserService getUserService() throws IllegalAccessException, InstantiationException {
        return Singleton.getInstance(UserServiceImpl.class);
    }
}
