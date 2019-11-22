package com.eeepay.zzq.jetpackdemo.lifecycle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 描述：定义广播接收者
 * 作者：zhuangzeqin
 * 时间: 2019/11/20-14:47
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
     private final static String TAG = MyBroadcastReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG,intent.getAction());
        Log.e(TAG,intent.getStringExtra("content"));
    }
}
