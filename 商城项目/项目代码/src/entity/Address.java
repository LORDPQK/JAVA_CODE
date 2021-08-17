package entity;

/*
对应数据库的地址表
 */

import java.io.Serializable;

public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    private int aid;
    private int uid;
    private String aname;//收件人
    private String aphone;//收件人手机
    private String adetail;//收件人详细地址
    private String astate;//默认地址0 非默认地址1

    public int getAid() {
        return aid;
    }

    public int getUid() {
        return uid;
    }

    public String getAname() {
        return aname;
    }

    public String getAphone() {
        return aphone;
    }

    public String getAdetail() {
        return adetail;
    }

    public String getAstate() {
        return astate;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public void setAphone(String aphone) {
        this.aphone = aphone;
    }

    public void setAdetail(String adetail) {
        this.adetail = adetail;
    }

    public void setAstate(String astate) {
        this.astate = astate;
    }

    @Override
    public String toString() {
        return "Address{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", aname='" + aname + '\'' +
                ", aphone='" + aphone + '\'' +
                ", adetail='" + adetail + '\'' +
                ", astate='" + astate + '\'' +
                '}';
    }
}
