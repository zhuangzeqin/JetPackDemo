package com.eeepay.zzq.jetpackdemo.viewmodel;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2019/11/25-16:44
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class SeekBarViewModel extends ViewModel {
    @Nullable
    public MutableLiveData<Integer> seekbarValue = new MutableLiveData<>();//可观察的
}
