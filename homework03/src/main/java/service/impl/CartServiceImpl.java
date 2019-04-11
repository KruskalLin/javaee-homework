package service.impl;

import entity.CartGoods;
import factory.DAOFactory;
import service.CartService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2019/1/1
 * @Todo:
 */
public class CartServiceImpl implements CartService{
    private final DAOFactory daoFactory = new DAOFactory();

    @Override
    synchronized public List<CartGoods> getAllCartGoods(int userId) throws SQLException, NamingException, InstantiationException, IllegalAccessException {
        return daoFactory.getCartDAO().findAllCartGoodsByUserId(userId);
    }

    @Override
    synchronized public void saveCartGoods(int goodsId, int userId, int quantity) throws SQLException, NamingException, InstantiationException, IllegalAccessException {
        List<CartGoods> cartGoods = daoFactory.getCartDAO().findAllCartGoodsByUserId(userId);
        boolean needInsert = true;
        for(CartGoods goods : cartGoods){
            if(goods.getGoods().getId() == goodsId){
                needInsert = false;
                if (quantity == -1){
                    quantity = goods.getQuantity() + 1;
                }
                break;
            }
        }
        if(needInsert){
            daoFactory.getCartDAO().insertCartGoods(goodsId, userId);
        }else{
            daoFactory.getCartDAO().updateCartGoodsQuantity(goodsId, userId, quantity);
        }
    }

    @Override
    synchronized public void deleteCartGoods(int goodsId, int userId) throws SQLException, NamingException, InstantiationException, IllegalAccessException {
        daoFactory.getCartDAO().deleteCartGoodsByGoodsId(goodsId, userId);
    }

    @Override
    synchronized public void deleteAllCartGoods(int userId) throws SQLException, NamingException, InstantiationException, IllegalAccessException {
        daoFactory.getCartDAO().deleteAllCartGoods(userId);
    }
}
