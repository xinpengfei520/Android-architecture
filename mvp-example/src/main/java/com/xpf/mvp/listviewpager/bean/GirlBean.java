package com.xpf.mvp.listviewpager.bean;

/**
 * Created by xpf on 2018/5/4 :)
 * GitHub:xinpengfei520
 * Function:
 */
public class GirlBean {

    private int id;
    private String describe;

    public GirlBean() {
    }

    public GirlBean(int id, String describe) {
        this.id = id;
        this.describe = describe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "GirlBean{" +
                "id=" + id +
                ", describe='" + describe + '\'' +
                '}';
    }
}
