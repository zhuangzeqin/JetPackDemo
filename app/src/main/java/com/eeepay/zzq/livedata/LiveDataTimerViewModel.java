package com.eeepay.zzq.livedata;

import android.os.SystemClock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 描述：ViewModel通常通过LiveData或者Data Binding来暴露信息。
 * 也可以通过其他任何可观察的对象，例如RxJava中的ObserVable。LiveData 与普通的Observable不同，
 * LiveData是生命周期感知的，这意味着它尊重其他应用程序组件的生命周期，
 * 例如Activity，Fragment或Service。 LiveData生命周期感知能力确保 LiveData仅仅去更新那些处于生命周期活动状态的观察者。
 * 作者：zhuangzeqin
 * 时间: 2019/11/25-17:09
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class LiveDataTimerViewModel extends ViewModel {
    private static final int ONE_SECOND = 1000;//1s
    //新建一个LiveData实例
    private MutableLiveData<Long> mElapsedTime = new MediatorLiveData<>();
//    LiveData、MutableLiveData、MediatorLiveData三者关系?
//    继承关系：MediatorLiveData -> MutableLiveData -> LiveData。 所以MediatorLiveData功能最强大。
//    LiveData 是一个具有生命周期感知的可观察的数据保持类。
//    MutableLiveData 在LiveData基础上打开了修改Value的方法权限。
//    MediatorLiveData 可管理多个LiveData。

    private long mInitialTime;

    public LiveDataTimerViewModel() {
        mInitialTime = SystemClock.elapsedRealtime();
        Timer timer = new Timer();

        // 每隔一秒更新一次
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final long newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000;
                // setValue() 不能再后台线程调用，所以使用post到主线程
                mElapsedTime.postValue(newValue);
            }
        }, ONE_SECOND, ONE_SECOND);

    }

    public LiveData<Long> getElapsedTime() {
        return mElapsedTime;
    }

}
