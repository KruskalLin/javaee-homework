package service;

import entity.CartGoods;
import org.springframework.stereotype.Service;

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

public interface CartService {
    public List<CartGoods> getAllCartGoods(int userId) throws SQLException, NamingException, InstantiationException, IllegalAccessException;
    public void saveCartGoods(int goodsId, int userId, int quantity) throws SQLException, NamingException, InstantiationException, IllegalAccessException;
    public void deleteCartGoods(int goodsId, int userId) throws SQLException, NamingException, InstantiationException, IllegalAccessException;
    public void deleteAllCartGoods(int userId) throws SQLException, NamingException, InstantiationException, IllegalAccessException;
}
