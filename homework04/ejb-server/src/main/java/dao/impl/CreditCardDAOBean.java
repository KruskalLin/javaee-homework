package dao.impl;

import dao.CreditCardDAO;
import utils.DatabaseUtils;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/21
 * @Todo:
 */
@Stateless
public class CreditCardDAOBean implements CreditCardDAO {
    @Override
    synchronized public void updateCreditCardMoneyByUserId(int userId, int money) throws SQLException, NamingException {
        int currentMoney = findCreditCardMoneyByUserId(userId);
        money = currentMoney - money;
        Connection conn = DatabaseUtils.getConnection();
        PreparedStatement pst1 = conn.prepareStatement("SELECT * FROM user WHERE id = ?");
        pst1.setInt(1, userId);
        ResultSet rs1 = pst1.executeQuery();
        if(rs1.next()) {
            int cardId = rs1.getInt("credit_card_id");
            PreparedStatement pst2 = conn.prepareStatement("UPDATE creditcard SET money = ? WHERE id = ?");
            pst2.setInt(1, money);
            pst2.setInt(2, cardId);
            boolean rs2 = pst2.execute();
            pst2.close();
        }
        pst1.close();
        rs1.close();
    }

    @Override
    synchronized public int findCreditCardMoneyByUserId(int userId) throws SQLException, NamingException {
        Connection conn = DatabaseUtils.getConnection();
        int money = 0;
        PreparedStatement pst1 = conn.prepareStatement("SELECT * FROM user WHERE id = ?");
        pst1.setInt(1, userId);
        ResultSet rs1 = pst1.executeQuery();
        if(rs1.next()){
            int cardId = rs1.getInt("credit_card_id");
            PreparedStatement pst2 = conn.prepareStatement("SELECT * FROM creditcard WHERE id = ?");
            pst2.setInt(1, cardId);
            ResultSet rs2 = pst2.executeQuery();
            if(rs2.next()) {
                money = rs2.getInt("money");
            }
            pst2.close();
            rs2.close();
        }
        pst1.close();
        rs1.close();
        return money;
    }

}
