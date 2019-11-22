package com.eeepay.zzq.jetpackdemo.bean;

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2019/11/20-16:38
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class UserInfo {
    private String name;
    private int age;

    public UserInfo(String name, int age, int id, String address) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.address = address;
    }

    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;
}
