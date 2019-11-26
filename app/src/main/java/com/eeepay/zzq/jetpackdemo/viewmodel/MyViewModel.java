package com.eeepay.zzq.jetpackdemo.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

/**
 * 描述：由于 ViewModel 生命周期可能长与 activity 生命周期，所以为了避免内存泄漏Google禁止在ViewModel中持有Context或activity或view的引用。如果非得使用Context，
 * 可以继承AndroidViewModel 类中获取ApplicationContext
 * 之前我们在activity销毁重建时，可以用activity的onSaveInstanceState（）机制保存和恢复数据，但缺点明显，
 * 只适合保存少量的可以被序列化、反序列化的数据。假如我们需要保存一个比较大的数据，这个时候ViewModel就可以实现。
 *
 * 作者：zhuangzeqin
 * 时间: 2019/11/25-13:58
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class MyViewModel extends ViewModel {
    /**
     * ViewModel只会在Activity存活，且只会创建一次。当销毁时，会主动调用onClered
     * 因为在Activity存活时，只创建一次，那么在此Activity下的所有Fragment都可以共享一个ViewModel
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i("MyViewModel的相关","Activity被杀死后也会被销毁！");
    }
}
