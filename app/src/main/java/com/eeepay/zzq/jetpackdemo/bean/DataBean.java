package com.eeepay.zzq.jetpackdemo.bean;

import java.io.Serializable;

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2019/12/2-16:31
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class DataBean implements Serializable {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataBean(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return "DataBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
