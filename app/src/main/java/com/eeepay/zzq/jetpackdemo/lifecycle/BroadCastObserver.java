package com.eeepay.zzq.jetpackdemo.lifecycle;

import android.app.Activity;
import android.content.IntentFilter;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * 描述：定义lifecycle观察者
 * 作者：zhuangzeqin
 * 时间: 2019/11/20-14:49
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class BroadCastObserver implements LifecycleObserver {
    private final static String TAG = BroadCastObserver.class.getSimpleName();
    private Activity mActivity;
    private MyBroadcastReceiver mBroadcastReceiver;

    public BroadCastObserver(Activity activity) {
        this.mActivity = activity;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void register() {
        Log.e(TAG, "register: ");
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("xxx");
        mActivity.registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void unRegister() {
        Log.e(TAG, "unRegister: ");
        mActivity.unregisterReceiver(mBroadcastReceiver);
    }
}
