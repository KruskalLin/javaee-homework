package entity;

import java.util.Objects;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/20
 * @Todo:
 */
public class CartGoods {

    private int id;

    private Goods goods;

    private int quantity;

    public CartGoods() {
    }

    public CartGoods(int id, Goods goods, int quantity) {
        this.id = id;
        this.goods = goods;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartGoods cartGoods = (CartGoods) o;
        return id == cartGoods.id &&
                quantity == cartGoods.quantity &&
                Objects.equals(goods, cartGoods.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goods, quantity);
    }
}
