package com.nyh.utils;


import java.util.Random;

/**
 * 生成验证码和取货码的工具类
 */
public class RandomUtil {
    public static Random r = new Random();
    public static int getCode(){
        return r.nextInt(900000)+100000;
    };
}
