package service;

import entity.Goods;

import javax.ejb.Remote;
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
@Remote
public interface GoodsService {
    public List<Goods> getFilGoods(String cat) throws SQLException, NamingException, InstantiationException, IllegalAccessException;
}
