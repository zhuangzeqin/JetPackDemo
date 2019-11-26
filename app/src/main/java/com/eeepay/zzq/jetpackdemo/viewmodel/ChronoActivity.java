package com.eeepay.zzq.jetpackdemo.viewmodel;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.eeepay.zzq.jetpackdemo.R;

/**
 * 描述：计时器，记录我们在这个界面停留的时间,但是当我们旋转屏幕的时候，会导致Activity重新创建实例，
 * onCreate()方法会再次执行，导致计时器会重新从0开始计时。
 * 作者：zhuangzeqin
 * 时间: 2019/11/25-16:21
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class ChronoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono);
        //在onCreate()方法中开始倒计时
        Chronometer chronometer = findViewById(R.id.chronometer);//计时器
//        long startTime = SystemClock.elapsedRealtime();
//        //每次onCreate()方法都会重新设置base
//        chronometer.setBase(startTime);
//        chronometer.start();
        ChronometerViewModel chronometerViewModel = ViewModelProviders.of(this).get(ChronometerViewModel.class);
        if (chronometerViewModel.getmStartTime() == null) {
            //chronometerViewModel如果没设置过开始时间，那么说明这个新的ViewModel,
            //所以给它设置开始时间
            long startTime = SystemClock.elapsedRealtime();
            chronometerViewModel.setmStartTime(startTime);
            chronometer.setBase(startTime);//设置计时器的开始时间
        } else {
            //否则ViewModel已经在上个Activity的onCreate()方法中创建过了，屏幕旋转以后，
            //ViewModel会被保存,我们直接获取ViewModel里持有的时间
            chronometer.setBase(chronometerViewModel.getmStartTime());
        }
        chronometer.start();//启动计时器

    }
}
