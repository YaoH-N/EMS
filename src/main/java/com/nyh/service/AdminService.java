package com.nyh.service;


import java.util.Date;

public interface AdminService {

    /**
     * 更新登录时间与ip
     * @param username
     * @param date
     * @param ip
     */
    void updateLoginTimeAndIP(String username, Date date, String ip);

    /**
     * 管理员根据账号密码登录
     * @param username  账号
     * @param password  密码
     * @return  登录的结果，true表示成功
     */
    boolean login(String username,String password);
}
