package com.nyh.pojo;

public class BootStrapTableCourier {

    private int id;
    private String exName;
    private String exPhone;
    private String idCard;
    private String exPassword;
    private String tranNumber;
    private String regTime;
    private String preLogTime;

    public BootStrapTableCourier(int id, String exName, String exPhone, String idCard, String exPassword, String tranNumber, String regTime, String preLogTime) {
        this.id = id;
        this.exName = exName;
        this.exPhone = exPhone;
        this.idCard = idCard;
        this.exPassword = exPassword;
        this.tranNumber = tranNumber;
        this.regTime = regTime;
        this.preLogTime = preLogTime;
    }

    public String getExName() {
        return exName;
    }

    public void setExName(String exName) {
        this.exName = exName;
    }

    public String getExPhone() {
        return exPhone;
    }

    public void setExPhone(String exPhone) {
        this.exPhone = exPhone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getExPassword() {
        return exPassword;
    }

    public void setExPassword(String exPassword) {
        this.exPassword = exPassword;
    }

    public String getTranNumber() {
        return tranNumber;
    }

    public void setTranNumber(String tranNumber) {
        this.tranNumber = tranNumber;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
