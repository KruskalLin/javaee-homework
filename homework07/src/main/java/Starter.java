import utils.TomcatUtils;

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
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
