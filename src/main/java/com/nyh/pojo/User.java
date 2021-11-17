package com.nyh.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private String username;

    private String userphone;

    private String idcard;

    private String userpwd;

    public Boolean getUser() {
        return isUser;
    }

    public void setUser(Boolean user) {
        isUser = user;
    }

    private Boolean isUser;// 新添加的属性判断是否为user

    public User() {
    }

    public User(String username, String userphone, String idcard, String userpwd) {
        this.username = username;
        this.userphone = userphone;
        this.idcard = idcard;
        this.userpwd = userpwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userphone='" + userphone + '\'' +
                ", idcard='" + idcard + '\'' +
                ", userpwd='" + userpwd + '\'' +
                ", regtime=" + regtime +
                ", prelogtime=" + prelogtime +
                '}';
    }

    private Date regtime;

    private Date prelogtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone == null ? null : userphone.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd == null ? null : userpwd.trim();
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public Date getPrelogtime() {
        return prelogtime;
    }

    public void setPrelogtime(Date prelogtime) {
        this.prelogtime = prelogtime;
    }
}