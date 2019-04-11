package dao.impl;

import dao.GoodsDAO;
import entity.Goods;
import org.springframework.stereotype.Repository;
import utils.HibernateUtil;

import javax.naming.NamingException;
import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/16
 * @Todo:
 */
@Repository
public class GoodsDAOImpl implements GoodsDAO {
    @Override
    synchronized public List<Goods> findAllGoods() throws SQLException, NamingException {
        List<Goods> goods = HibernateUtil.getSession().createSQLQuery("SELECT * FROM goods").addEntity(Goods.class).list();
        return goods;
    }

    @Override
    synchronized public List<Goods> findGoodsByCategories(String cat) throws SQLException, NamingException {
        List<Goods> goods = HibernateUtil.getSession().createSQLQuery("SELECT * FROM goods WHERE categories = " + "'" + cat + "'").addEntity(Goods.class).list();
        return goods;
    }
}
