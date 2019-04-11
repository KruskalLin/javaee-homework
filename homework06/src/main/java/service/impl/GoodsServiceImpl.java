package service.impl;

import entity.Goods;
import factory.DAOFactory;
import service.GoodsService;

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
public class GoodsServiceImpl implements GoodsService{
    private final DAOFactory daoFactory = new DAOFactory();

    @Override
    synchronized public List<Goods> getFilGoods(String cat) throws SQLException, NamingException, InstantiationException, IllegalAccessException {
        if("ALL".equals(cat)){
            return daoFactory.getGoodsDAO().findAllGoods();
        }else{
            return daoFactory.getGoodsDAO().findGoodsByCategories(cat);
        }
    }
}
