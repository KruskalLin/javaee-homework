package dao.impl;

import dao.CartDAO;
import entity.Cart;
import entity.CartGoods;
import entity.Goods;
import utils.HibernateUtil;

import javax.naming.NamingException;
import javax.persistence.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/21
 * @Todo:
 */
public class CartDAOImpl implements CartDAO {

    @Override
    synchronized public List<CartGoods> findAllCartGoodsByUserId(int userId) throws SQLException, NamingException {
        Cart cart  = (Cart) HibernateUtil.getSession().createSQLQuery("SELECT * FROM cart where user_cart_id = " + userId).addEntity(Cart.class).uniqueResult();
        HibernateUtil.getSession().clear();
        return new ArrayList<>(cart.getCartGoods());
    }

    @Override
    synchronized public void insertCartGoods(int goodsId, int userId) throws SQLException, NamingException{
        Cart carts = (Cart) HibernateUtil.getSession().createSQLQuery("SELECT * FROM cart where user_cart_id = " + userId).addEntity(Cart.class).uniqueResult();
        Goods goods = (Goods) HibernateUtil.getSession().createSQLQuery("SELECT * FROM goods where id = " + goodsId).addEntity(Goods.class).uniqueResult();
        CartGoods cartGoods = new CartGoods();
        cartGoods.setQuantity(1);
        cartGoods.setGoods(goods);
        carts.getCartGoods().add(cartGoods);
        HibernateUtil.getSession().persist(cartGoods);
        HibernateUtil.getSession().flush();
    }

    @Override
    synchronized public void updateCartGoodsQuantity(int goodsId, int userId, int quantity) throws SQLException, NamingException{
        Cart cart = (Cart) HibernateUtil.getSession().createSQLQuery("SELECT * FROM cart where user_cart_id = " + userId).addEntity(Cart.class).uniqueResult();
        HibernateUtil.getSession().clear();
        CartGoods cartGoods =  (CartGoods) HibernateUtil.getSession().createSQLQuery("SELECT * FROM cartgoods where cart_goods_id = " + cart.getId() + " and goods_id = " + goodsId).addEntity(CartGoods.class).uniqueResult();
        cartGoods.setQuantity(quantity);
        HibernateUtil.getSession().flush();
    }


    @Override
    synchronized public void deleteCartGoodsByGoodsId(int goodsId, int userId) throws SQLException, NamingException {
        Cart cart = (Cart) HibernateUtil.getSession().createSQLQuery("SELECT * FROM cart where user_cart_id = " + userId).addEntity(Cart.class).uniqueResult();
        HibernateUtil.getSession().clear();
        CartGoods cartGoods =  (CartGoods) HibernateUtil.getSession().createSQLQuery("SELECT * FROM cartgoods where cart_goods_id = " + cart.getId() + " and goods_id = " + goodsId).addEntity(CartGoods.class).uniqueResult();
        HibernateUtil.getSession().delete(cartGoods);
    }

    @Override
    synchronized public void deleteAllCartGoods(int userId) throws SQLException, NamingException {
        Cart cart = (Cart) HibernateUtil.getSession().createSQLQuery("SELECT * FROM cart where user_cart_id = " + userId).addEntity(Cart.class).uniqueResult();
        for(CartGoods cartGoods : cart.getCartGoods()){
            HibernateUtil.getSession().delete(cartGoods);
        }
        cart.getCartGoods().clear();
        HibernateUtil.getSession().flush();
    }





}
