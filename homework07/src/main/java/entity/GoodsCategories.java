package entity;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/20
 * @Todo:
 */
public enum GoodsCategories {
    DailyUse("生活用品"),
    Books("书籍课本"),
    Electronics("电子商品"),
    Sports("运动健身");

    private String type;

    GoodsCategories(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


}
