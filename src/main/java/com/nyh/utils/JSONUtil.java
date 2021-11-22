package com.nyh.utils;

import com.google.gson.Gson;

/**
 * 将对象转化为JSON格式字符串的工具类
 */
public class JSONUtil {

    private static Gson g = new Gson();
    public static  String toJSON(Object obj){
        return g.toJson(obj);
    }
}
