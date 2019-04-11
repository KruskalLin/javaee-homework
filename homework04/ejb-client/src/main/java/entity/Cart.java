package entity;

import java.util.Set;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/20
 * @Todo:
 */
public class Cart {

    private int id;

    private User user;

    private Set<CartGoods> cartGoods;

    public Cart() {
    }

    public int getTotal(){
        int total = 0;
        for(CartGoods c: cartGoods){
            total += c.getQuantity();
        }
        return total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CartGoods> getCartGoods() {
        return cartGoods;
    }

    public void setCartGoods(Set<CartGoods> cartGoods) {
        this.cartGoods = cartGoods;
    }
}
