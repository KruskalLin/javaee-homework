package dao.impl;

import dao.CartDAO;
import entity.CartGoods;
import entity.Goods;
import utils.DatabaseUtils;

import javax.naming.NamingException;
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
public class CartDAOImpl implements CartDAO{
    @Override
    synchronized public List<CartGoods> findAllCartGoodsByUserId(int userId) throws SQLException, NamingException {
        Connection conn = DatabaseUtils.getConnection();
        List<CartGoods> cartGoods = new ArrayList<>();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM cart where user_cart_id = ?");
        pst.setInt(1, userId);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            int cartId = rs.getInt("id");
            PreparedStatement pst1 = conn.prepareStatement("SELECT * FROM cartgoods where cart_goods_id = ?");
            pst1.setInt(1, cartId);
            ResultSet rs1 = pst1.executeQuery();
            while(rs1.next()){
                int goodsId = rs1.getInt("goods_id");
                PreparedStatement pst2 = conn.prepareStatement("SELECT * FROM goods where id = ?");
                pst2.setInt(1, goodsId);
                ResultSet rs2 = pst2.executeQuery();
                if(rs2.next()){
                    cartGoods.add(new CartGoods(
                            cartId,
                            new Goods(
                                    goodsId,
                                    rs2.getString("name"),
                                    rs2.getString("property"),
                                    rs2.getString("categories"),
                                    rs2.getInt("price")
                            ),
                            rs1.getInt("quantity")
                    ));
                }
                pst2.close();
                rs2.close();
            }
            pst1.close();
            rs1.close();
            pst.close();
            rs.close();
        }
        return cartGoods;
    }

    @Override
    synchronized public void insertCartGoods(int goodsId, int userId) throws SQLException, NamingException{
        Connection conn = DatabaseUtils.getConnection();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM cart where user_cart_id = ?");
        pst.setInt(1, userId);
        ResultSet rs = pst.executeQuery();
        if(rs.next()) {
            int cartId = rs.getInt("id");
            PreparedStatement pst1 = conn.prepareStatement("INSERT INTO cartgoods(quantity, goods_id, cart_goods_id) VALUES (?, ?, ?)");
            pst1.setInt(1, 1);
            pst1.setInt(2, goodsId);
            pst1.setInt(3, cartId);
            boolean rs1 = pst1.execute();
            pst1.close();
        }
        pst.close();
        rs.close();
    }

    @Override
    synchronized public void updateCartGoodsQuantity(int goodsId, int userId, int quantity) throws SQLException, NamingException{
        Connection conn = DatabaseUtils.getConnection();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM cart where user_cart_id = ?");
        pst.setInt(1, userId);
        ResultSet rs = pst.executeQuery();
        if(rs.next()) {
            int cartId = rs.getInt("id");
            PreparedStatement pst1 = conn.prepareStatement("UPDATE cartgoods SET quantity = ? WHERE goods_id = ? AND cart_goods_id = ?");
            pst1.setInt(1, quantity);
            pst1.setInt(2, goodsId);
            pst1.setInt(3, cartId);
            boolean rs1 = pst1.execute();
            pst1.close();
        }
        pst.close();
        rs.close();
    }


    @Override
    synchronized public void deleteCartGoodsByGoodsId(int goodsId, int userId) throws SQLException, NamingException {
        Connection conn = DatabaseUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM cart where user_cart_id = ?");
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int cartId = rs.getInt("id");
                PreparedStatement pst1 = conn.prepareStatement("DELETE from cartgoods WHERE goods_id = ? and cart_goods_id = ?");
                pst1.setInt(1, goodsId);
                pst1.setInt(2, cartId);
                boolean rs1 = pst1.execute();
                pst1.close();
            }
            pst.close();
            rs.close();
    }

    @Override
    synchronized public void deleteAllCartGoods(int userId) throws SQLException, NamingException {
        Connection conn = DatabaseUtils.getConnection();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM cart where user_cart_id = ?");
        pst.setInt(1, userId);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int cartId = rs.getInt("id");
            PreparedStatement pst1 = conn.prepareStatement("DELETE from cartgoods WHERE cart_goods_id = ?");
            pst1.setInt(1, cartId);
            boolean rs1 = pst1.execute();
            pst1.close();
        }
        pst.close();
        rs.close();
    }





}
