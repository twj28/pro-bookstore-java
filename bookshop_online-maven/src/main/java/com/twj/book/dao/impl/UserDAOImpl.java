package com.twj.book.dao.impl;

import com.twj.book.dao.UserDAO;
import com.twj.book.pojo.User;
import com.twj.myssm.basedao.BaseDAO;

/**
 * @ClassNmae UserDAOImpl
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/16 0:40
 * @Version 1.0
 **/
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User getUser(String uname, String pwd) {
        return super.load("select * from t_user where uname =? and pwd = ?",uname,pwd);
    }

    @Override
    public void addUser(User user) {
        super.executeUpdate("insert into t_user values(0,?,?,?,?)",user.getUname(),user.getPwd(),user.getEmail(),user.getRole());
    }

    @Override
    public User ckGetUser(User user) {
        return super.load("select * from t_user where uname = ?",user.getUname());
    }
}
