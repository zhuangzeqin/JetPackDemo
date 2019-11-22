package com.eeepay.zzq.jetpackdemo.lifecycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * 描述：/ 定义一个类实现LifecycleObserver接口,通过向其方法添加注释来监视组件的生命周期状态
 * —— Lifecycle 是具有生命周期感知能力的组件。简单的理解当Activity/Fragment的生命周期产生变化时，lifeCycle组件也会有相应的生命周期变化。我们可以通过使用lifeCycle组件在自定义的类中管理Activity/fragment的生命周期。
 * 主要由三大部分构成：Lifecycle、LifecycleOwner、LifecycleObserver
 *
 * Lifecycle：是一个持有组件生命周期状态与事件（如Activity或Fragment）的信息的类
 * LifecycleOwner：Lifecycle的提供者，通过实现LifecycleOwner接口来访问Lifecycle（生命周期）对象。Fragment和FragmentActivity类实现了LifecycleOwner接口，它具有访问生命周期的getLifecycle方法。您还可以在自己的类中实现LifecycleOwner。
 * LifecycleObserver：Lifecycle观察者，实现该接口的类，通过注解的方式，可以通过被LifecycleOwner类的addObserver(LifecycleObserver o)方法注册,被注册后，LifecycleObserver便可以观察到LifecycleOwner的生命周期事件。
 * 作者：zhuangzeqin
 * 时间: 2019/11/20-14:35
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class MyObserver implements LifecycleObserver {
    private final static String TAG = MyObserver.class.getSimpleName();
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
        Log.e(TAG, "start: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop() {
        Log.e(TAG, "stop: ");
    }
}
