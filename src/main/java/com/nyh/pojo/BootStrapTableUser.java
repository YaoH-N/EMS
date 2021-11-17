package com.nyh.pojo;

public class BootStrapTableUser {

    private int id;
    private String userName;
    private String userPhone;
    private String idCard;
    private String userPwd;
    private String regTime;
    private String preLogTime;

    public BootStrapTableUser(int id, String userName, String userPhone, String idCard, String userPwd, String regTime, String preLogTime) {
        this.id = id;
        this.userName = userName;
        this.userPhone = userPhone;
        this.idCard = idCard;
        this.userPwd = userPwd;
        this.regTime = regTime;
        this.preLogTime = preLogTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getPreLogTime() {
        return preLogTime;
    }

    public void setPreLogTime(String preLogTime) {
        this.preLogTime = preLogTime;
    }
}
