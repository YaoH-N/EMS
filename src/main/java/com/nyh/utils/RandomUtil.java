package com.nyh.utils;


import java.util.Random;

public class RandomUtil {
    public static Random r = new Random();
    public static int getCode(){
        return r.nextInt(900000)+100000;
    };
}
