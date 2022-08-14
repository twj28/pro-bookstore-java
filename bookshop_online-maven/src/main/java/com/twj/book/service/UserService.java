package com.twj.book.service;

import com.twj.book.pojo.User;

/**
 * @author twj28
 * @create 2022 2022/5/16 0:36
 */
public interface UserService {
    //登录方法
    User login(String uname, String pwd);
    //注册方法
    void regist(User user);
    //核对用户
    User ckGetUser(User user);
}
