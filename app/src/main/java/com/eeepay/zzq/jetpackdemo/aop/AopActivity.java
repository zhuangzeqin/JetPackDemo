package com.eeepay.zzq.jetpackdemo.aop;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.eeepay.zzq.jetpackdemo.R;
import com.eeepay.zzq.jetpackdemo.bean.DataBean;

import java.util.ArrayList;

import cn.com.superLei.aoparms.annotation.Cache;
import cn.com.superLei.aoparms.annotation.CacheEvict;
import cn.com.superLei.aoparms.annotation.SingleClick;
import cn.com.superLei.aoparms.annotation.TimeLog;
import cn.com.superLei.aoparms.common.utils.ArmsCache;

/**
 * 描述：Android开发中常用的一套注解，如日志、异步处理、缓存、SP、延迟操作、定时任务、重试机制、try-catch安全机制、过滤频繁点击等
 * 作者：zhuangzeqin
 * 时间: 2019/12/5-14:10
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class AopActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = AopActivity.class.getSimpleName();
    private Button btn_cache, btn_sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aop);
        btn_cache = (Button) findViewById(R.id.btn_cache);
        btn_sharedpreferences = (Button) findViewById(R.id.btn_sharedpreferences);
        btn_cache.setOnClickListener(this);
        btn_sharedpreferences.setOnClickListener(this);
        initData();
    }

    @Override
    @SingleClick(ids = {R.id.btn_cache, R.id.btn_sharedpreferences})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cache:
                ArrayList<DataBean> user = getUser();
                for (int i = 0; i < user.size(); i++) {
                    DataBean dataBean = user.get(i);
                    Log.d(TAG, dataBean.toString());
                }
                break;
            case R.id.btn_sharedpreferences:

                break;
            default:

                break;
        }
    }

//     1、插入缓存

    /**
     * key：缓存的键
     * expiry：缓存过期时间,单位s
     *
     * @return 缓存的值
     */
    @Cache(key = "userList", expiry = 60 * 60 * 24)
    private ArrayList<DataBean> initData() {
        ArrayList<DataBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DataBean user = new DataBean();
            user.setName("艾神一不小心:" + i);
            user.setId(i);
            list.add(user);
        }
        return list;
    }

    //2、获取缓存
    @TimeLog
    private ArrayList<DataBean> getUser() {
        return ArmsCache.get(this).getAsList("userList", DataBean.class);
    }

//3、移除缓存

    /**
     * key:缓存的键
     * beforeInvocation:缓存的清除是否在方法之前执行, 如果出现异常缓存就不会清除   默认false
     * allEntries：是否清空所有缓存(与key互斥)  默认false
     */
    @CacheEvict(key = "userList", beforeInvocation = true, allEntries = false)
    public void removeUser() {
        Log.e(TAG, "removeUser: >>>>");
    }
}
