package entity;


import java.io.Serializable;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/16
 * @Todo:
 */
public class Goods implements Serializable{

    private int id;

    private String name;

    private String property;

    private GoodsCategories categories;

    private int price;

    public Goods() {
    }

    public Goods(int id, String name, String property, String categories, int price) {
        this.id = id;
        this.name = name;
        this.property = property;
        this.categories = GoodsCategories.valueOf(categories);
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public GoodsCategories getCategories() {
        return categories;
    }

    public void setCategories(GoodsCategories categories) {
        this.categories = categories;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
