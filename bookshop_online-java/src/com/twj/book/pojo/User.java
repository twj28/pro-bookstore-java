package com.twj.book.pojo;

import java.util.List;

/**
 * @ClassNmae User
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/15 23:54
 * @Version 1.0
 **/
//用户类
public class User {
    private Integer id;
    private String uname;//用户名
    private String pwd;//密码
    private String email;//邮箱
    private Integer role;//角色
    private Cart cart;
    private OrderBean orderBean;
    private List<OrderBean> orderBeanList;

    public OrderBean getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }

    public List<OrderBean> getOrderBeanList() {
        return orderBeanList;
    }

    public void setOrderBeanList(List<OrderBean> orderBeanList) {
        this.orderBeanList = orderBeanList;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public User() {
    }

    public User(String uname, String pwd, String email, Integer role) {
        this.uname = uname;
        this.pwd = pwd;
        this.email = email;
        this.role = role;
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String uname) {
        this.uname = uname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
