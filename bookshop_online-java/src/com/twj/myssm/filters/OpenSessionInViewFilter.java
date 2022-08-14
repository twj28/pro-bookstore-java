package com.twj.myssm.filters;


import com.twj.myssm.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @ClassNmae OpenSessionInViewFilter
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/5 15:06
 * @Version 1.0
 **/
@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            //开启事务
            TransactionManager.beginTrans();
            System.out.println("开启事务。。。。");
            //放行
            filterChain.doFilter(servletRequest,servletResponse);
            //提交事务
            TransactionManager.commit();
            System.out.println("提交事务。。。。");
        }catch(Exception e){
            e.printStackTrace();
            //回滚
            try {
                TransactionManager.rollBack();
                System.out.println("回滚事务。。。。");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void destroy() {

    }
}
