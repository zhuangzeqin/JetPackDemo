package com.eeepay.zzq.jetpackdemo.bean;

import java.io.Serializable;

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2019/11/21-11:51
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class UserBean implements Serializable {
    private int userId;
    private String userName;
    private int userAge;
    private int userSex;

    public UserBean(int userId, String userName, int userAge, int userSex) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
        this.userSex = userSex;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }
}
