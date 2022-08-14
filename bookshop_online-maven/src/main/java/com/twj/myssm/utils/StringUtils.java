package com.twj.myssm.utils;

/**
 * @ClassNmae StringUtils
 * @Description TODO
 * @Author twj280
 * @Date 2022/4/16 19:31
 * @Version 1.0
 **/
public class StringUtils {
    public static boolean isEmpty(String str){
        return str == null || "".equals(str);

    }
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
