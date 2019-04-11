package dao;

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
 * @Date: 2019/1/1
 * @Todo:
 */
public interface CartDAO {
    public List<CartGoods> findAllCartGoodsByUserId(int userId) throws SQLException, NamingException;
    public void insertCartGoods(int goodsId, int userId) throws SQLException, NamingException;
    public void updateCartGoodsQuantity(int goodsId, int userId, int quantity) throws SQLException, NamingException;
    public void deleteCartGoodsByGoodsId(int goodsId, int userId) throws SQLException, NamingException;
    public void deleteAllCartGoods(int userId) throws SQLException, NamingException;
}
