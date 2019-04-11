package entity;

import java.util.Set;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/16
 * @Todo:
 */

public class UserOrder {

    private int id;

    private Set<CartGoods> cartGoods;


    private User user;

    public UserOrder() {
    }

    public UserOrder(Set<CartGoods> cartGoods, User user) {
        this.cartGoods = cartGoods;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<CartGoods> getCartGoods() {
        return cartGoods;
    }

    public void setCartGoods(Set<CartGoods> cartGoods) {
        this.cartGoods = cartGoods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
