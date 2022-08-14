package com.twj.myssm.myspringmvc;


import com.twj.myssm.ioc.BeanFactory;
import com.twj.myssm.utils.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;

/**
 * @ClassNmae DispatcherServlet
 * @Description TODO
 * @Author twj280
 * @Date 2022/4/20 16:53
 * @Version 1.0
 **/
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {
    private BeanFactory beanFactory;

    public DispatcherServlet() {

    }

    public void init() throws ServletException {
        super.init();

//         beanFactory = new ClassPathXmlApplicationContext();
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");

        if (beanFactoryObj != null) {
            this.beanFactory = (BeanFactory) beanFactoryObj;
        } else {
            throw new DispatcherServletException("获取IOC容器失败。。");
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");

        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);//hello.do
        int lastDotIndex = servletPath.lastIndexOf(".do");
//        System.out.println("lastDotIndex = " + lastDotIndex);
        servletPath = servletPath.substring(0, lastDotIndex);

        Object controllerBeanObj = beanFactory.getBean(servletPath);

        String operate = request.getParameter("operate");
        if (StringUtils.isEmpty(operate)) {
            operate = "index";
        }
        if (controllerBeanObj != null) {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                try {
                    if (operate.equals(method.getName())) {
                        //获取参数数组
                        Parameter[] parameters = method.getParameters();
                        Object[] parameterValues = new Object[parameters.length];
                        for (int i = 0; i < parameters.length; i++) {
                            //获取参数名
                            String parameterName = parameters[i].getName();

                            if ("request".equals(parameterName)) {
                                parameterValues[i] = request;
                            } else if ("response".equals(parameterName)) {
                                parameterValues[i] = response;
                            } else if ("session".equals(parameterName)) {
                                parameterValues[i] = request.getSession();
                            } else {
                                //通过参数名获取 值
                                String parameterValue = request.getParameter(parameterName);
                                //获取参数的类型
                                String typeName = parameters[i].getType().getName();

                                if ("java.lang.Integer".equals(typeName)) {
                                    if (parameterValue != null) {
                                        parameterValues[i] = Integer.parseInt(parameterValue); //"2"  2
                                    }
                                } else if ("java.math.BigDecimal".equals(typeName)) {
                                    if (parameterValue != null) {
                                        parameterValues[i] = new BigDecimal(parameterValue); //"2"  2
                                    }

                                } else {
                                    parameterValues[i] = parameterValue;
                                }

                            }
                        }
                        //获取方法的返回值
                        method.setAccessible(true);
                        Object returnObj = method.invoke(controllerBeanObj, parameterValues);

                        //视图处理：
                        String methodReturnStr = (String) returnObj;

                        if (methodReturnStr != null && methodReturnStr != "") {
                            if (methodReturnStr.startsWith("redirect:")) {
                                String redirectStr = methodReturnStr.substring("redirect:".length());
                                response.sendRedirect(redirectStr);
                            }  else if(methodReturnStr.startsWith("json:")) {
                                response.setCharacterEncoding("UTF-8");
                                response.setContentType("application/json;charSet=UTF-8");

                                //json:{'uname':'0'}
                                String jsonStr = methodReturnStr.substring("json:".length());//{'uname':'0'}
                                //通过response.getWriter获取打印流
                                PrintWriter writer = response.getWriter();
                                //写出到客户端
                                writer.print(jsonStr);
                                writer.flush();
                            }else{
                                super.processTemplate(methodReturnStr, request, response);
                            }
                        }
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new DispatcherServletException("DispatcherServlet  出现问题了。。。");
                }
            }
        }

        throw new RuntimeException("错误网页!!");
    }
}
