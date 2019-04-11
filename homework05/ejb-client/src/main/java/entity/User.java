package entity;


import java.io.Serializable;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/12/16
 * @Todo:
 */
public class User implements Serializable{

    private int id;

    private String username;

    private String password;

    private CreditCard creditCard;

    public User() {
    }

    public User(int id, String username, String password, CreditCard creditCard) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.creditCard = creditCard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
