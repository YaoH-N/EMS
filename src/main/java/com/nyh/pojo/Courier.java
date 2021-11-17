package com.nyh.pojo;

import java.util.Date;

public class Courier {
    private Integer id;

    private String exname;

    private String exphone;

    private String idcard;

    private String expassword;

    private String trannumber;

    private Date regtime;

    private Date prelogtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExname() {
        return exname;
    }

    public void setExname(String exname) {
        this.exname = exname == null ? null : exname.trim();
    }

    public String getExphone() {
        return exphone;
    }

    public void setExphone(String exphone) {
        this.exphone = exphone == null ? null : exphone.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getExpassword() {
        return expassword;
    }

    public void setExpassword(String expassword) {
        this.expassword = expassword == null ? null : expassword.trim();
    }

    public String getTrannumber() {
        return trannumber;
    }

    public void setTrannumber(String trannumber) {
        this.trannumber = trannumber == null ? null : trannumber.trim();
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

    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", exname='" + exname + '\'' +
                ", exphone='" + exphone + '\'' +
                ", idcard='" + idcard + '\'' +
                ", expassword='" + expassword + '\'' +
                ", trannumber='" + trannumber + '\'' +
                ", regtime=" + regtime +
                ", prelogtime=" + prelogtime +
                '}';
    }

    public Courier() {
    }

    public Courier(String exname, String exphone, String idcard, String expassword) {
        this.exname = exname;
        this.exphone = exphone;
        this.idcard = idcard;
        this.expassword = expassword;
    }
}