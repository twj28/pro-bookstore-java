package com.twj.myssm.filters;


import com.twj.myssm.utils.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassNmae CharacterEncodingFilter
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/4 20:41
 * @Version 1.0
 **/
@WebFilter(urlPatterns = {"*.do"},initParams = {@WebInitParam(name = "encoding",value = "utf-8")})
public class CharacterEncodingFilter implements Filter {
    private String encoding = "utf-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingStr = filterConfig.getInitParameter("encoding");
        if (StringUtils.isNotEmpty(encodingStr)){
            encoding = encodingStr;
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ((HttpServletRequest)servletRequest).setCharacterEncoding(encoding);

        //dispatcherServlet中已加如下
        // response.setCharacterEncoding("UTF-8");
        // response.setContentType("application/json;charSet=UTF-8");

//        ((HttpServletResponse)servletResponse).setCharacterEncoding(encoding);//dispatcherServlet中加
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
