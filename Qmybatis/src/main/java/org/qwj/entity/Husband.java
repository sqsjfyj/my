package org.qwj.entity;

import java.io.Serializable;

public class Husband implements Serializable {

    private int hid;

    private String hname;

    private Wife wife;

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }

    @Override
    public String toString() {
        return "Husband [id=" + hid + ", name=" + hname + ", wife=" + wife + "]";
    }
}