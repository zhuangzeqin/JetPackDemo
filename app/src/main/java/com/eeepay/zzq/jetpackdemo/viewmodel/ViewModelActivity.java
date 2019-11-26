package com.eeepay.zzq.jetpackdemo.viewmodel;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.eeepay.zzq.jetpackdemo.R;
 /**
   * 描述：ViewModel的生命周期
  * 1、Activity created(走了3个生命周期)，对应于ViewModel的scope。
  * 2、Activity rorared(类似切换了横竖屏幕)，还是对应scope
  * 3、finish()（Activity销毁），依赖是scope
  * 4、Finished （已经销毁了）。调用ViewModel的onCleared。
  * ViewModel是存储UI相关数据并不会因为旋转而销毁的类
   * 作者：zhuangzeqin
   * 时间: 2019/11/25-14:05
   * 邮箱：zzq@eeepay.cn
   * 备注:
   */
public class ViewModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);
        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        Log.i("MyViewModel的相关", "onCreate ==> " + myViewModel.hashCode());
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        Log.i("MyViewModel的相关", "onStart ==> " + myViewModel.hashCode());
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        Log.i("MyViewModel的相关", "onResume ==> " + myViewModel.hashCode());
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        Log.i("MyViewModel的相关", "onPause ==> " + myViewModel.hashCode());
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        Log.i("MyViewModel的相关", "onStop ==> " + myViewModel.hashCode());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        Log.i("MyViewModel的相关", "onDestroy ==> " + myViewModel.hashCode());
    }



}
