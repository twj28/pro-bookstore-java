package com.twj.myssm.myspringmvc;

/**
 * @ClassNmae DispatcherServletException
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/5 16:24
 * @Version 1.0
 **/
public class DispatcherServletException extends RuntimeException{
    static final long serialVersionUID = -7034891561507766939L;
    public DispatcherServletException(String msg){
        super(msg);
    }
}
