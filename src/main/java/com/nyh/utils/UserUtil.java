package com.nyh.utils;

import com.nyh.pojo.User;

import javax.servlet.http.HttpSession;

public class UserUtil {
    /**
     * 根据session获取用户的姓名
     * @param session
     * @return
     */
    public static String getUserName(HttpSession session){
        return (String) session.getAttribute("adminUserName");
    }

    /**
     * 根据session获取用户的电话
     * @param session
     * @return
     */
    public static String getUserPhone(HttpSession session){
        // TODO : 还没有编写柜子端，未存储任何的录入人信息，无法获取录入人电话
        // 还未存储录入人的信息，这里先暂时代替一下
        User wxUser = (User) session.getAttribute("wxUser");
        if (wxUser == null){
            return "18888888888";
        }
        return wxUser.getUserphone();
    }

    public static String getLoginSms(HttpSession session, String userPhone) {
        return (String) session.getAttribute(userPhone);
    }
    public static void setLoginSms(HttpSession session, String userPhone, String code) {
        session.setAttribute(userPhone, code);
    }

    public static User getWxUser (HttpSession session) {
        return (User) session.getAttribute("wxUser");
    }
    public static void setWxUser(HttpSession session, User user) {
        session.setAttribute("wxUser", user);
    }
}
