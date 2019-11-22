package com.eeepay.zzq.jetpackdemo.bean;

import androidx.databinding.ObservableDouble;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableFloat;
import androidx.databinding.ObservableInt;

/**
 * 描述：数据的双向绑定 Observable
 * 作者：zhuangzeqin
 * 时间: 2019/11/21-16:13
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class UserBean2 {
    public ObservableInt userId = new ObservableInt();//可观察的int
    public ObservableField<String> userName = new ObservableField<>();//String 对象
    public ObservableDouble userAge = new ObservableDouble();//可观察的double
    public ObservableFloat userSex = new ObservableFloat();//可观察的Float

//    ObservableList<String>

}
