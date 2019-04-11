package service.impl;

import dao.GoodsDAO;
import entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
public class GoodsServiceImpl implements GoodsService{
    @Autowired
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
