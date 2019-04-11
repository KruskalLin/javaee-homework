import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.JarResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import utils.DatabaseUtils;
import utils.TomcatUtils;

import java.io.File;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/10
 * @Todo:
 */
public class Starter {
    public static void main(String args[]){
        try {
            TomcatUtils.startTomcat(9000);
            DatabaseUtils.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
