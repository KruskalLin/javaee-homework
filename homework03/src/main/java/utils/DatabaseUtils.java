package utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
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
    synchronized public static Connection getConnection() throws NamingException, SQLException {
        if(connection!=null){
            return connection;
        }
        Context context = new InitialContext();
        connection = ((DataSource) context.lookup("java:comp/env/jdbc/datasource")).getConnection();
        return connection;
    }


}
