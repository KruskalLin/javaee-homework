package factory;

import service.CartService;
import service.CreditCardService;
import service.GoodsService;
import service.UserService;

import javax.ejb.EJB;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/31
 * @Todo:
 */
public class ServiceFactory {

    private CartService cartService;

    private CreditCardService creditCardService;

    private GoodsService goodsService;

    private UserService userService;

//    @EJB(beanName="CartServiceBean")
    public CartService getCartService() throws IllegalAccessException, InstantiationException {
        return EJBFactory.getEJB("ejb:/ejb-server-1.0-SNAPSHOT/CartServiceBean!service.CartService");
//        return cartService;
    }
//    @EJB(beanName="CreditCardServiceBean")
    public CreditCardService getCreditCardService() throws IllegalAccessException, InstantiationException {
        return EJBFactory.getEJB("ejb:/ejb-server-1.0-SNAPSHOT/CreditCardServiceBean!service.CreditCardService");
//        return creditCardService;
    }
//    @EJB(beanName="GoodsServiceBean")
    public GoodsService getGoodsService() throws IllegalAccessException, InstantiationException {
        return EJBFactory.getEJB("ejb:/ejb-server-1.0-SNAPSHOT/GoodsServiceBean!service.GoodsService");
//        return goodsService;
    }
//    @EJB(beanName="UserServiceBean")
    public UserService getUserService() throws IllegalAccessException, InstantiationException {
        return EJBFactory.getEJB("ejb:/ejb-server-1.0-SNAPSHOT/UserServiceBean!service.UserService");
//        return userService;
    }
}
