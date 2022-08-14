package com.twj.myssm.listeners;

import com.twj.myssm.ioc.BeanFactory;
import com.twj.myssm.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @ClassNmae ContextLoaderListener
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/5 23:36
 * @Version 1.0
 **/
@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {


        ServletContext application = servletContextEvent.getServletContext();
        String path = application.getInitParameter("contextConfigLocation");
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);

        application.setAttribute("beanFactory",beanFactory);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
