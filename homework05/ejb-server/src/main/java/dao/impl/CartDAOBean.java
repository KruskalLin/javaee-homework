package dao.impl;

import dao.CartDAO;
import entity.Cart;
import entity.CartGoods;
import entity.Goods;
import utils.DatabaseUtils;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@Stateless
public class CartDAOBean implements CartDAO {
    @PersistenceUnit(name = "jpa")
    private EntityManagerFactory factory;

    @PersistenceContext
    private EntityManager entityManager;

    public CartDAOBean() {
        factory = Persistence.createEntityManagerFactory("jpa");
        entityManager = factory.createEntityManager();
    }
    @Override
    synchronized public List<CartGoods> findAllCartGoodsByUserId(int userId) throws SQLException, NamingException {
        Cart cart  = (Cart) entityManager.createNativeQuery("SELECT * FROM cart where user_cart_id = ?", Cart.class).setParameter(1, userId).getSingleResult();
        entityManager.clear();
        return new ArrayList<>(cart.getCartGoods());
    }

    @Override
    synchronized public void insertCartGoods(int goodsId, int userId) throws SQLException, NamingException{
        Cart carts = (Cart) entityManager.createNativeQuery("SELECT * FROM cart where user_cart_id = ?", Cart.class).setParameter(1, userId).getSingleResult();
        Goods goods = entityManager.find(Goods.class, goodsId);
        CartGoods cartGoods = new CartGoods();
        cartGoods.setQuantity(1);
        cartGoods.setGoods(goods);
        carts.getCartGoods().add(cartGoods);
        entityManager.persist(cartGoods);
        entityManager.flush();
    }

    @Override
    synchronized public void updateCartGoodsQuantity(int goodsId, int userId, int quantity) throws SQLException, NamingException{
        Cart cart = (Cart) entityManager.createNativeQuery("SELECT * FROM cart where user_cart_id = ?", Cart.class).setParameter(1, userId).getSingleResult();
        entityManager.clear();
        CartGoods cartGoods =  (CartGoods) entityManager.createNativeQuery("SELECT * FROM cartgoods where cart_goods_id = ? and goods_id = ?", CartGoods.class).setParameter(1, cart.getId()).setParameter(2, goodsId).getSingleResult();
        cartGoods.setQuantity(quantity);
        entityManager.flush();
    }


    @Override
    synchronized public void deleteCartGoodsByGoodsId(int goodsId, int userId) throws SQLException, NamingException {
        Cart cart = (Cart) entityManager.createNativeQuery("SELECT * FROM cart where user_cart_id = ?", Cart.class).setParameter(1, userId).getSingleResult();
        entityManager.clear();
        CartGoods cartGoods =  (CartGoods) entityManager.createNativeQuery("SELECT * FROM cartgoods where cart_goods_id = ? and goods_id = ?", CartGoods.class).setParameter(1, cart.getId()).setParameter(2, goodsId).getSingleResult();
        entityManager.remove(cartGoods);
    }

    @Override
    synchronized public void deleteAllCartGoods(int userId) throws SQLException, NamingException {
        Cart cart = (Cart) entityManager.createNativeQuery("SELECT * FROM cart where user_cart_id = ?", Cart.class).setParameter(1, userId).getSingleResult();
        for(CartGoods cartGoods : cart.getCartGoods()){
            entityManager.remove(cartGoods);
        }
        cart.getCartGoods().clear();
        entityManager.flush();
    }





}
