package com.eeepay.zzq.jetpackdemo.databing;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.eeepay.zzq.jetpackdemo.R;
import com.eeepay.zzq.jetpackdemo.bean.UserBean;
import com.eeepay.zzq.jetpackdemo.bean.UserBean2;
import com.eeepay.zzq.jetpackdemo.databinding.ActivityTestDataBinding;

import java.util.ArrayList;

public class TestDataActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityTestDataBinding viewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_data);
        //它的名字取决于你的layout文件名，activity_main
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_test_data);

        //初始化数据
        UserBean userBean1 = new UserBean(1, "aaa", 1, 1);
        UserBean userBean2 = new UserBean(2, "bbb", 2, 4);
        UserBean userBean3 = new UserBean(3, "ccc", 3, 15);
        ArrayList<UserBean> data = new ArrayList<>();
        data.add(userBean1);
        data.add(userBean2);
        data.add(userBean3);

        UserBean2 userBean21 = new UserBean2();
        userBean21.userId.set(11);
        userBean21.userName.set("zhuangzeqin");
        userBean21.userAge.set(30);
        userBean21.userSex.set(1);
        viewDataBinding.setObservableuser(userBean21);

        //这里的方法和xml中定义的方法相对应
        //赋值过后，控件会自动填充数据
        viewDataBinding.setUser(userBean1);
        viewDataBinding.setList(data);
        //有id的控件也可以通过databinding得到
        viewDataBinding.btnTest.setOnClickListener(this);
        viewDataBinding.btnTest1.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        if (v.equals(viewDataBinding.btnTest)) {
            //修改databinding中的数据
            viewDataBinding.getList().get(0).setUserName("bbb");
            //每一个定义的变量都有相对应的get/set方法
            viewDataBinding.btnTest.setText(viewDataBinding.getList().get(0).getUserName());
        } else if (v.equals(viewDataBinding.btnTest1)) {
            try {
                //双向绑定数据不需要重新调用控件，数据改变，相对应绑定数据的view也会改变
                viewDataBinding.getObservableuser().userName.set("动态修改后的数据，view自动刷新数据");
                viewDataBinding.getObservableuser().userAge.set(100000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}
