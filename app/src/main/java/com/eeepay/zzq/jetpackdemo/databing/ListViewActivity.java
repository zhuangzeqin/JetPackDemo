package com.eeepay.zzq.jetpackdemo.databing;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.eeepay.zzq.jetpackdemo.R;
import com.eeepay.zzq.jetpackdemo.adapter.ListViewAdapter;
import com.eeepay.zzq.jetpackdemo.bean.UserBean2;
import com.eeepay.zzq.jetpackdemo.databinding.ActivityListViewBinding;

import java.util.ArrayList;
import java.util.List;
 /**
   * 描述：DataBinding 应用于ListView
   * 作者：zhuangzeqin
   * 时间: 2019/11/21-17:34
   * 邮箱：zzq@eeepay.cn
   * 备注:
   */
public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_view);

        ActivityListViewBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_view);

        ListViewAdapter listViewAdapter =new ListViewAdapter(initData());
        viewDataBinding.setAdapterAA(listViewAdapter);
        //通知adapter刷新数据
        listViewAdapter.notifyDataSetChanged();
    }

    //初始化测试数据
    private List<UserBean2> initData() {
        List<UserBean2> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            UserBean2 userBean = new UserBean2();
            userBean.userId.set(i);
            userBean.userName.set(i + "aaa");
            userBean.userAge.set(18 + i);
            userBean.userSex.set(i % 2 == 0 ? 1 : 0);
            list.add(userBean);
        }
        return list;
    }

}
