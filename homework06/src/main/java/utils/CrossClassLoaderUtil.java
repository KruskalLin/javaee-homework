package utils;

import java.io.*;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2019/1/19
 * @Todo:
 */
public class CrossClassLoaderUtil {
    public static Object crossClassLoader(Object o){
        Object cross = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.flush();
            cross = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())).readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cross;
    }
}
