package utils;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.JarResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;
import java.net.URISyntaxException;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/17
 * @Todo:
 */
public class TomcatUtils {
    public static void startTomcat(int port) throws ServletException, LifecycleException, URISyntaxException {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(".");
        tomcat.getHost().setAppBase(".");
        tomcat.setPort(port);
        tomcat.enableNaming();

        Context ctx = tomcat.addWebapp("", "src/main/webapp");
        ctx.setConfigFile(TomcatUtils.class.getClassLoader().getResource("META-INF/context.xml"));
        WebResourceRoot res = new StandardRoot(ctx);

        File classPath = new File(TomcatUtils.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        res.addPreResources(new DirResourceSet(res, "/WEB-INF/classes", classPath.getAbsolutePath(), "/"));
        ctx.setResources(res);

        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }

}
