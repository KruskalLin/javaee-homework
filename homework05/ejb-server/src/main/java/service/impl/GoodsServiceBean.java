package service.impl;

import dao.GoodsDAO;
import entity.Goods;
import service.GoodsService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
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
@Stateless(name = "GoodsServiceBean")
public class GoodsServiceBean implements GoodsService{

    @EJB
    private GoodsDAO goodsDAO;
    @Override
    synchronized public List<Goods> getFilGoods(String cat) throws SQLException, NamingException, InstantiationException, IllegalAccessException {
        if("ALL".equals(cat)){
            return goodsDAO.findAllGoods();
        }else{
            return goodsDAO.findGoodsByCategories(cat);
        }
    }
}
