package com.eeepay.zzq.jetpackdemo.workmanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.eeepay.zzq.jetpackdemo.R;

public class WorknanagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worknanager);
        //1.约束条件
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)//网络连接
//                .setRequiresBatteryNotLow(true)//不在电量不足时执行
//                .setRequiresCharging(true)//在充电时执行
//                .setRequiresStorageNotLow(true)//不在存储容量不足时执行
//                .setRequiresDeviceIdle(true)//在待机状态执行
                .build();
        //2.传入参数
        Data data = new Data.Builder().putString("demo", "helloworld").build();
        //3.构造work  WorkRequest 则定义工作的运行方式和时间。任务可以是一次性的，也可以是周期性的。对于一次性 WorkRequest，请使用 OneTimeWorkRequest，对于周期性工作，请使用 PeriodicWorkRequest。
        OneTimeWorkRequest httpwork = new OneTimeWorkRequest.Builder(HttpWork.class).setConstraints(constraints).setInputData(data).build();
        //OneTimeWorkRequest，任务只会执行一次，如果需要执行周期性任务可以使用PeriodicWorkRequest
//        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(HttpWork.class, 15, TimeUnit.SECONDS).setConstraints(constraints).setInputData(data).build();
        //4.放入执行队列
        WorkManager.getInstance().enqueue(httpwork);
        //任务链
        //1 WorkManager.getInstance().beginWith(A).then(B).then(C).enqueue();//任务ABC顺序执行
        //2 WorkManager.getInstance().beginWith(A,B).then(C).enqueue();//任务A和B同时执行，它们都执行完了之后再执行C

        //任务链1和任务链2同时执行，它们都执行完了之后再执行E
//        WorkContinuation workContinuation1 = WorkManager.getInstance().beginWith(A).then(B);
//        WorkContinuation workContinuation2 = WorkManager.getInstance().beginWith(C).then(D);
//        WorkContinuation workContinuation3 = WorkContinuation.combine(workContinuation1,workContinuation2).then(E);
//        workContinuation3.enqueue();


    }
}
