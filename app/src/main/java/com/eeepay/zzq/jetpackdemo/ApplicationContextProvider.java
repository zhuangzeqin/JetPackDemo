package com.eeepay.zzq.jetpackdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 描述：无侵入式获取全局Context
 * 对于固定的初始化配置，可以使用ContextProvider方案减少调用方的配置，减少出错。
 * 作者：zhuangzeqin
 * 时间: 2019/12/23-11:51
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public final class ApplicationContextProvider extends ContentProvider {

    @Override
    public boolean onCreate() {
        //初始化全局Context提供者
        AppContext.init(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        if (providerInfo == null) {
            throw new NullPointerException("APPInitProvider ProviderInfo cannot be null.");
        }
        // So if the authorities equal the library internal ones, the developer forgot to set his applicationId
        String name = ApplicationContextProvider.class.getName();
        Log.d("attachInfo", name);
        //com.eeepay.zzq.jetpackdemo.ApplicationContextProvider
        if (name.equals(providerInfo.authority)) {
            throw new IllegalStateException("Incorrect provider authority in manifest. Most likely due to a "
                    + "missing applicationId variable in application\'s build.gradle.");
        }
        super.attachInfo(context, providerInfo);
    }
}
