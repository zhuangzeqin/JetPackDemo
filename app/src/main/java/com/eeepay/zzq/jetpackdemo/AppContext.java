package com.eeepay.zzq.jetpackdemo;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * 描述：无侵入式获取全局Context
 * 作者：zhuangzeqin
 * 时间: 2019/12/23-12:33
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public final class AppContext {
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        if (mContext == null) {
            throw new IllegalStateException("please init AppContext");
        }
        return mContext;
    }
}
