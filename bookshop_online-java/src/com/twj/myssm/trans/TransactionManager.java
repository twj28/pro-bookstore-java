package com.twj.myssm.trans;


import com.twj.myssm.utils.JDBCUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassNmae TransationManager
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/5 14:51
 * @Version 1.0
 **/
public class TransactionManager {
//    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    //开启事务
    public static void beginTrans() throws SQLException, IOException, ClassNotFoundException {
//        Connection conn = threadLocal.get();
//        if (conn == null) {
//            conn = JDBCUtils.getConnection();
//            threadLocal.set(conn);
//        }
        Connection conn = JDBCUtils.getThreadLocalConn();
        conn.setAutoCommit(false);

    }

    //提交事务
    public static void commit() throws SQLException, IOException, ClassNotFoundException {
//        Connection conn = threadLocal.get();
//        if (conn == null) {
//            conn = JDBCUtils.getConnection();
//        }
        Connection conn = JDBCUtils.getThreadLocalConn();
        conn.commit();
        JDBCUtils.closeThreadLocalConn();
    }
    //回滚事务
    public static void rollBack() throws SQLException, IOException, ClassNotFoundException {
//        Connection conn = threadLocal.get();
//        if(conn == null){
//            conn = JDBCUtils.getConnection();
//        }

        Connection conn = JDBCUtils.getThreadLocalConn();
        conn.rollback();
    }
}
