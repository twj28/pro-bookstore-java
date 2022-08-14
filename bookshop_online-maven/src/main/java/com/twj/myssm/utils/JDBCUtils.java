package com.twj.myssm.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.twj.myssm.exceptions.JDBCUtilsException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class JDBCUtils {
    /**
     * @Author: twj280
     * @Description: 获取连接
     * @date: 10:34 2022/4/8
     * @Param: []
     * @return java.sql.Connection
     **/
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private static DataSource datasource;
    //创建数据库连接池，并获取资源 datasource;
    static{
        try {
            Properties properties = new Properties();
            properties.load(JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.druid.properties"));
            datasource = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
            throw new JDBCUtilsException("初始化 获取datasource 出现问题了。。。");
        }
    }

    public static Connection getConnection() {
        //方式一：
        /*
        try {
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties pro = new Properties();
            pro.load(is);
            //加载驱动
            Class.forName(pro.getProperty("classDriver"));
            Connection conn = DriverManager.getConnection(pro.getProperty("url"), pro.getProperty("user"), pro.getProperty("password"));
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JDBCUtilsException("JDBCUtils getConnection 出问题了");
        }*/

        //方式二：
        /*
        try {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/qqzonedb");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            DruidPooledConnection conn = dataSource.getConnection();
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JDBCUtilsException("JDBCUtils getConnection 出问题了");
        }*/

        //方式三：
        /*
        try {
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.druid.properties");

            Properties properties = new Properties();
            properties.load(is);
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

            return dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new JDBCUtilsException("JDBCUtils getConnection 出问题了");
        }*/

        //方式四：通过Druid 数据库连接池 ，获取单个链接
        try {
            return datasource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JDBCUtilsException("JDBCUtils getConnection 出问题了");
        }

    }

    /**
     * @return void
     * @Author: twj280
     * @Description: 关闭资源
     * @date: 10:35 2022/4/8
     * @Param: [conn, ps, rs]
     **/
    public static void closeResource(Connection conn, Statement ps, ResultSet rs) {
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (ps != null)
                ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (rs != null)
                rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @Author: twj280
     * @Description: 关闭资源
     * @date: 10:35 2022/4/8
     * @Param: [conn, ps]
     **/
    public static void closeResource(Connection conn, Statement ps) {
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (ps != null)
                ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return void
     * @Author: twj280
     * @Description: 关闭资源
     * @date: 10:35 2022/4/8
     * @Param: [conn, ps]
     **/
    public static void closeResource1(Connection conn) {
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeResource(Connection conn) {

    }

    public static Connection getThreadLocalConn() {
        Connection conn = threadLocal.get();
        if (conn == null) {
            conn = getConnection();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }

    public static void closeThreadLocalConn() {
        Connection conn = threadLocal.get();
        if (conn == null) {
            return;
        }
        try {
            if (!conn.isClosed()) {
                conn.close();
                //        threadLocal.set(null);
                threadLocal.remove();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JDBCUtilsException("JDBCUtils closeThreadLocalConn 出问题了");
        }
    }
}
