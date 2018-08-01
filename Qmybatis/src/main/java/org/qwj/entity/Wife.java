package org.qwj.entity;

import java.io.Serializable;

public class Wife implements Serializable {
    private int wid;

    private String wname;

    private int hus_id;

//    public Wife(int wid, String wname, Husband husband) {
//        super();
//        this.wid = wid;
//        this.wname = wname;
//        this.husband = husband;
//    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public int getHus_id() {
        return hus_id;
    }

    public void setHus_id(int hus_id) {
        this.hus_id = hus_id;
    }

    @Override
    public String toString() {
        return "Wife [id=" + wid + ", name=" + wname + "]";
    }
}