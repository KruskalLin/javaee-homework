package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2019/1/1
 * @Todo:
 */
public class Singleton {
    private static final Singleton instance = new Singleton();

    private Map<Class,Object> mapHolder = new HashMap<Class, Object>();

    private Singleton() {}

    public static <T> T getInstance(Class<T> classOf) throws InstantiationException, IllegalAccessException {
        synchronized(instance){
            if(!instance.mapHolder.containsKey(classOf)){
                T obj = classOf.newInstance();
                instance.mapHolder.put(classOf, obj);
            }
            return (T)instance.mapHolder.get(classOf);
        }
    }
}
