package service.impl;

import dao.CartDAO;
import entity.CartGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartDAO cartDAO;

    @Override
    synchronized public List<CartGoods> getAllCartGoods(int userId) throws SQLException, NamingException, InstantiationException, IllegalAccessException {
        return cartDAO.findAllCartGoodsByUserId(userId);
    }

    @Override
    synchronized public void saveCartGoods(int goodsId, int userId, int quantity) throws SQLException, NamingException, InstantiationException, IllegalAccessException {
        List<CartGoods> cartGoods = cartDAO.findAllCartGoodsByUserId(userId);
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
            cartDAO.insertCartGoods(goodsId, userId);
        }else{
            cartDAO.updateCartGoodsQuantity(goodsId, userId, quantity);
        }
    }

    @Override
    synchronized public void deleteCartGoods(int goodsId, int userId) throws SQLException, NamingException, InstantiationException, IllegalAccessException {
        cartDAO.deleteCartGoodsByGoodsId(goodsId, userId);
    }

    @Override
    synchronized public void deleteAllCartGoods(int userId) throws SQLException, NamingException, InstantiationException, IllegalAccessException {
        cartDAO.deleteAllCartGoods(userId);
    }
}
