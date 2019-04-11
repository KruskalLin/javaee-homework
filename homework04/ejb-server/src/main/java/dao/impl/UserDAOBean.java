package dao.impl;

import dao.UserDAO;
import entity.CreditCard;
import entity.User;
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
 * @Date: 2018/12/20
 * @Todo:
 */
@Stateless
public class UserDAOBean implements UserDAO{
    @Override
    synchronized public User findUserByUsername(String username) throws SQLException, NamingException {
        Connection conn = DatabaseUtils.getConnection();
        PreparedStatement pst1 = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
        pst1.setString(1, username);
        ResultSet rs1 = pst1.executeQuery();
        User result = null;
        if (rs1.next()) {
            int cardId = rs1.getInt("credit_card_id");
            PreparedStatement pst2 = conn.prepareStatement("SELECT * FROM creditcard WHERE id = ?");
            pst2.setString(1, "" + cardId);
            ResultSet rs2 = pst2.executeQuery();
            if(rs2.next()) {
                result = new User(
                        rs1.getInt("id"),
                        rs1.getString("username"),
                        rs1.getString("password"),
                        new CreditCard(rs2.getInt("id"), rs2.getInt("money"))
                );
            }
            rs2.close();
            pst2.close();
        }
        rs1.close();
        pst1.close();
        return result;
    }
}
