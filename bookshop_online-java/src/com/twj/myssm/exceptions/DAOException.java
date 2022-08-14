package com.twj.myssm.exceptions;

/**
 * @ClassNmae DAOException
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/5 15:50
 * @Version 1.0
 **/
public class DAOException extends RuntimeException{
    static final long serialVersionUID = -803489719010000000L;
    public DAOException(String msg){
        super(msg);
    }

}
