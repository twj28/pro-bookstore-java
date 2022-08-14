package com.twj.z_book.filters;


import com.twj.myssm.utils.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassNmae SessionFilter
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/20 17:51
 * @Version 1.0
 **/
@WebFilter(urlPatterns = {"*.do","*.html"},initParams = {@WebInitParam(name = "whiteList",value = "/bookshop/page.do?operate=page&page=user/login,/bookshop/user.do,/bookshop/page.do?operate=page&page=user/regist,/bookshop/book.do")})
public class SessionFilter implements Filter {
    private List<String> whiteStrList = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String whiteList = filterConfig.getInitParameter("whiteList");
        String[] splitWhiteList = whiteList.split(",");
        whiteStrList = Arrays.asList(splitWhiteList);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("request.getRequestURI() = " + request.getRequestURI());///pro24/page.do
//        System.out.println("request.getRequestURL() = " + request.getRequestURL());//http://localhost:8080/pro24/page.do
        System.out.println("request.getQueryString() = " + request.getQueryString());//operate=page&page=user/login
        String queryString = request.getQueryString();

        if(StringUtils.isNotEmpty(queryString)){
            if (queryString.contains("operate=ckName&uname=")){
                queryString=null;
            }
        }

        String str = request.getRequestURI() + "?" + queryString;

        if (queryString == null){
            str = request.getRequestURI();
        }

        System.out.println("str = " + str);
        if (whiteStrList.contains(str)){
            filterChain.doFilter(request,response);
        }else{
            HttpSession session = request.getSession();
            Object currUserObj = session.getAttribute("currUser");
            if(currUserObj == null){

                response.sendRedirect("page.do?operate=page&page=user/login");
            }else{
                filterChain.doFilter(request,response);
            }
        }



    }

    @Override
    public void destroy() {

    }
}
