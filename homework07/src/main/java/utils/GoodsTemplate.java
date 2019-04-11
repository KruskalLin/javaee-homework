package utils;

import entity.CartGoods;
import entity.Goods;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2019/1/4
 * @Todo:
 */
public class GoodsTemplate {

    public static String getItemString(CartGoods cartGoods) {
        return " <div class=\"item\">\n" +
                "        <div class=\"buttons\">\n" +
                "          <span id=\"" + cartGoods.getGoods().getId() + "\" class=\"delete-btn\"></span>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"image\">\n" +
                "          <img src=\"/materials/item.png\" alt=\"\" />\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"description\">\n" +
                "          <span>" + cartGoods.getGoods().getName() + "</span>\n" +
                "          <span>" + cartGoods.getGoods().getProperty() + "</span>\n" +
                "          <span>" + cartGoods.getGoods().getCategories().getType() + "</span>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"quantity\">\n" +
                "          <button value=\"" + cartGoods.getGoods().getId() + "\" class=\"plus-btn\" type=\"button\" name=\"button\">\n" +
                "            <img src=\"/materials/plus.svg\" alt=\"\" />\n" +
                "          </button>\n" +
                "          <input type=\"text\" name=\"name\" value=\"" + cartGoods.getQuantity() + "\">\n" +
                "          <button value=\"" + cartGoods.getGoods().getId() + "\"  class=\"minus-btn\" type=\"button\" name=\"button\">\n" +
                "            <img src=\"/materials/minus.svg\" alt=\"\" />\n" +
                "          </button>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"total-price\">￥" + cartGoods.getGoods().getPrice() * cartGoods.getQuantity() + "</div>\n" +
                "      </div>\n";
    }
    public static String getItemString(Goods goods) {
        return "<div class=\"item\">\n" +
                "        <div class=\"buttons\">\n" +
                "            <button class=\"cart-btn cart\" value=\"" + goods.getId() + "\">放入购物车</button>\n" +
                "            <button class=\"buy-btn buy\" value=\"" + goods.getPrice() + "\">立即购买</button>\n" +
                "        </div>\n" +
                "        <div class=\"image\">\n" +
                "            <img src=\"/materials/item.png\" alt=\"\"/>\n" +
                "        </div>\n" +
                "        <div class=\"description\">\n" +
                "            <span>" + goods.getName() + "</span>\n" +
                "            <span>" + goods.getProperty() + "</span>\n" +
                "            <span>" + goods.getCategories().getType() + "</span>\n" +
                "        </div>\n" +
                "        <div class=\"description\">\n" +
                "        </div>\n" +
                "        <div class=\"total-price\">￥" + goods.getPrice() + "</div>\n" +
                "    </div>\n";
    }
}
