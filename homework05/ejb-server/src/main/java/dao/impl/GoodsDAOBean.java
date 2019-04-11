package dao.impl;

import dao.GoodsDAO;
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
 * @Date: 2018/12/16
 * @Todo:
 */
@Stateless
public class GoodsDAOBean implements GoodsDAO{
    @PersistenceUnit(name = "jpa")
    private EntityManagerFactory factory;

    @PersistenceContext
    private EntityManager entityManager;

    public GoodsDAOBean() {
        factory = Persistence.createEntityManagerFactory("jpa");
        entityManager = factory.createEntityManager();
    }

    @Override
    synchronized public List<Goods> findAllGoods() throws SQLException, NamingException {
        List<Goods> goods = entityManager.createNativeQuery("SELECT * FROM goods", Goods.class).getResultList();
        return goods;
    }

    @Override
    synchronized public List<Goods> findGoodsByCategories(String cat) throws SQLException, NamingException {
        List<Goods> goods = entityManager.createNativeQuery("SELECT * FROM goods WHERE categories = ?", Goods.class).setParameter(1, cat).getResultList();
        return goods;
    }
}
