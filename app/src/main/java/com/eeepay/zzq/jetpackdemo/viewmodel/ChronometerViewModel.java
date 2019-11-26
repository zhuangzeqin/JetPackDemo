package com.eeepay.zzq.jetpackdemo.viewmodel;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

/**
 * 描述：ViewModel的唯一的作用是管理UI的数据。ViewModel不能访问UI或者持有Activity/Fragment的引用。
 * 作者：zhuangzeqin
 * 时间: 2019/11/25-16:23
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class ChronometerViewModel extends ViewModel {
    public Long getmStartTime() {
        return mStartTime;
    }

    public void setmStartTime(Long mStartTime) {
        this.mStartTime = mStartTime;
    }

    @Nullable
    private Long mStartTime;
}
