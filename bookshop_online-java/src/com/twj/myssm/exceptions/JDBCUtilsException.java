package com.twj.myssm.exceptions;

/**
 * @ClassNmae JDBCUtilsException
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/14 17:06
 * @Version 1.0
 **/
public class JDBCUtilsException extends RuntimeException{
    static final long serialVersionUID = -703489719121561939L;
    public JDBCUtilsException(String msg){
        super(msg);
    }
}
