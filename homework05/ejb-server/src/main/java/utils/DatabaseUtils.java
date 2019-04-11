package utils;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/16
 * @Todo:
 */
public class DatabaseUtils {
    private static Connection connection;
    public static Connection getConnection() throws SQLException {
        if(connection != null){
            return connection;
        }
        try{
            InitialContext jndiContext = new InitialContext();
            connection = ((DataSource) jndiContext.lookup("java:/mysqlds")).getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }


}
