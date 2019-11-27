package com.eeepay.zzq.jetpackdemo.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 *
 * 描述：任务是使用 Worker 类定义的。doWork() 方法在 WorkManager 提供的后台线程上同步运行。
 * 作者：zhuangzeqin
 * 时间: 2019/11/26-14:24
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class HttpWork extends Worker {
    public HttpWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    /**
     * 从 doWork() 返回的 Result 会通知 WorkManager 任务是否：
     *
     * 已成功完成：Result.success()
     * 已失败：Result.failure()
     * 需要稍后重试：Result.retry()
     * @return
     */
    @NonNull
    @Override
    public Result doWork() {
        //接收传进来的参数
        String str = this.getInputData().getString("demo");
        Log.i("HttpWork","执行了哦"+str);
       return Result.success();
    }
}
