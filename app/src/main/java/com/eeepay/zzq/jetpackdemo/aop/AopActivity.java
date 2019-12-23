package com.eeepay.zzq.jetpackdemo.aop;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.eeepay.zzq.jetpackdemo.AppContext;
import com.eeepay.zzq.jetpackdemo.R;
import com.eeepay.zzq.jetpackdemo.bean.DataBean;

import java.util.ArrayList;
import java.util.List;

import cn.com.superLei.aoparms.annotation.Async;
import cn.com.superLei.aoparms.annotation.Cache;
import cn.com.superLei.aoparms.annotation.CacheEvict;
import cn.com.superLei.aoparms.annotation.Callback;
import cn.com.superLei.aoparms.annotation.Delay;
import cn.com.superLei.aoparms.annotation.DelayAway;
import cn.com.superLei.aoparms.annotation.Intercept;
import cn.com.superLei.aoparms.annotation.Permission;
import cn.com.superLei.aoparms.annotation.PermissionDenied;
import cn.com.superLei.aoparms.annotation.PermissionNoAskDenied;
import cn.com.superLei.aoparms.annotation.Prefs;
import cn.com.superLei.aoparms.annotation.PrefsEvict;
import cn.com.superLei.aoparms.annotation.Retry;
import cn.com.superLei.aoparms.annotation.Safe;
import cn.com.superLei.aoparms.annotation.Scheduled;
import cn.com.superLei.aoparms.annotation.SingleClick;
import cn.com.superLei.aoparms.annotation.TimeLog;
import cn.com.superLei.aoparms.common.permission.AopPermissionUtils;
import cn.com.superLei.aoparms.common.utils.ArmsCache;
import cn.com.superLei.aoparms.common.utils.ArmsPreference;

/**
 * 描述：Android开发中常用的一套注解，如日志、异步处理、缓存、SP、延迟操作、定时任务、重试机制、try-catch安全机制、过滤频繁点击等
 * 作者：zhuangzeqin
 * 时间: 2019/12/5-14:10
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class AopActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = AopActivity.class.getSimpleName();
    private Button btn_cache, btn_sharedpreferences, btn_aysnc, btn_permission, btn_catch, btn_retry, btn_scheduled, btn_delay, btn_intercept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aop);
        btn_cache = (Button) findViewById(R.id.btn_cache);
        btn_sharedpreferences = (Button) findViewById(R.id.btn_sharedpreferences);
        btn_aysnc = (Button) findViewById(R.id.btn_aysnc);
        btn_permission = (Button) findViewById(R.id.btn_permission);
        btn_catch = (Button) findViewById(R.id.btn_catch);
        btn_retry = (Button) findViewById(R.id.btn_retry);
        btn_scheduled = (Button) findViewById(R.id.btn_scheduled);
        btn_delay = (Button) findViewById(R.id.btn_delay);
        btn_intercept = (Button) findViewById(R.id.btn_intercept);
        btn_cache.setOnClickListener(this);
        btn_sharedpreferences.setOnClickListener(this);
        btn_aysnc.setOnClickListener(this);
        btn_permission.setOnClickListener(this);
        btn_catch.setOnClickListener(this);
        btn_retry.setOnClickListener(this);
        btn_scheduled.setOnClickListener(this);
        btn_delay.setOnClickListener(this);
        btn_intercept.setOnClickListener(this);
        initData();
    }

    @Override
    @SingleClick(ids = {R.id.btn_cache, R.id.btn_sharedpreferences, R.id.btn_aysnc, R.id.btn_permission, R.id.btn_catch, R.id.btn_retry})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cache:
                ArrayList<DataBean> user = getUser();
                for (int i = 0; i < user.size(); i++) {
                    DataBean dataBean = user.get(i);
                    Log.d(TAG, dataBean.toString());
                }
//                removeUser();
                break;
            case R.id.btn_sharedpreferences:
                initArticle();
                getArticle();
                break;
            case R.id.btn_aysnc:
                asyn();
                break;
            case R.id.btn_permission:
                permission(view);
                break;
            case R.id.btn_catch:
                safe();//try-catch安全机制篇
                break;
            case R.id.btn_retry:
                retry();//重试机制
                break;
            case R.id.btn_scheduled:
                scheduled();//定时机制
                break;
            case R.id.btn_delay:
                delay();//延迟
                break;
            case R.id.btn_intercept:
                loginIntercept();//拦截
                break;
            default:

                break;
        }
    }

//     1、插入缓存

    /**
     * key：缓存的键
     * expiry：缓存过期时间,单位s
     *
     * @return 缓存的值
     */
    @Cache(key = "userList", expiry = 60 * 60 * 24)
    private ArrayList<DataBean> initData() {
        ArrayList<DataBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DataBean user = new DataBean();
            user.setName("艾神一不小心:" + i);
            user.setId(i);
            list.add(user);
        }
        return list;
    }

    //2、获取缓存
    //AopActivity.getUser:[Ljava.lang.Object;@fb89786 --->:[18ms] 测试方法耗时篇
    @TimeLog
    private ArrayList<DataBean> getUser() {
        return ArmsCache.get(this).getAsList("userList", DataBean.class);
    }

//3、移除缓存

    /**
     * key:缓存的键
     * beforeInvocation:缓存的清除是否在方法之前执行, 如果出现异常缓存就不会清除   默认false
     * allEntries：是否清空所有缓存(与key互斥)  默认false
     */
    @CacheEvict(key = "userList", beforeInvocation = true, allEntries = false)
    public void removeUser() {
        Log.e(TAG, "removeUser: >>>>");
    }


    //1、开启请求权限

    /**
     * //     * @param value 权限值
     * //     * @param rationale 拒绝后的下一次提示(开启后，拒绝后，下一次会先提示该权限申请提示语)
     * //     * @param requestCode 权限请求码标识
     */
    @Permission(value = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, rationale = "为了更好的体验，请打开相关权限")
    public void permission(View view) {
        Log.e(TAG, "permission: 权限已打开");
    }

    //2、请求拒绝注解回调
    @PermissionDenied
    public void permissionDenied(int requestCode, List<String> denyList) {
        Log.e(TAG, "permissionDenied: " + requestCode);
        Log.e(TAG, "permissionDenied>>>: " + denyList.toString());
    }

    //3、请求拒绝且不在提示注解回调
    @PermissionNoAskDenied
    public void permissionNoAskDenied(int requestCode, List<String> denyNoAskList) {
        Log.e(TAG, "permissionNoAskDenied: " + requestCode);
        Log.e(TAG, "permissionNoAskDenied>>>: " + denyNoAskList.toString());
        //前往设置页面打开权限
        AopPermissionUtils.showGoSetting(this, "为了更好的体验，建议前往设置页面打开权限");
    }

    //保存key到sp
    @Prefs(key = "article")
    private DataBean initArticle() {
        DataBean user = new DataBean();
        user.setName("zhuangzeqin:" + 9);
        user.setId(9);
        return user;
    }

    //通过key从sp中获取value
    public void getArticle() {
        DataBean article = ArmsPreference.get(AppContext.getContext(), "article", null);
        Log.d(TAG, article.toString());
    }

    //从sp中移除key

    /**
     * key:sp的键
     * allEntries：是否清空所有存储(与key互斥)  默认false
     */
    @PrefsEvict(key = "article", allEntries = false)
    public void removeArticle() {
        Log.e(TAG, "removeArticle: >>>>");
    }

    //异步
    @Async
    public void asyn() {
        Log.e(TAG, "useAync: " + Thread.currentThread().getName());
    }

    //自动帮你try-catch   允许你定义回调方法
    @Safe(callBack = "throwMethod")
    public void safe() {
        String str = null;
        str.toString();
    }

    //自定义回调方法（注意要和callBack的值保持一致）,必须要有Callback注解
    @Callback
    public void throwMethod(Throwable throwable) {
        Log.e(TAG, "throwMethod: >>>>>" + throwable.toString());
    }

    /**
     * //@param count 重试次数
     * // @param delay 每次重试的间隔
     * //@param asyn 是否异步执行
     * //@param retryCallback 自定义重试结果回调
     * //@return 当前方法是否执行成功
     */
    @Retry(count = 3, delay = 1000, asyn = true, retryCallback = "retryCallback")
    public boolean retry() {
        Log.e(TAG, "retryDo: >>>>>>" + Thread.currentThread().getName());
        return false;
    }

    @Callback
    public void retryCallback(boolean result) {
        Log.e(TAG, "retryCallback: >>>>" + result);
    }

    /**
     * * @param interval 初始化延迟
     * * @param interval 时间间隔
     * * @param timeUnit 时间单位
     * * @param count 执行次数
     * * @param taskExpiredCallback 定时任务到期回调
     */
    @Scheduled(interval = 1000L, count = 10, taskExpiredCallback = "taskExpiredCallback")
    public void scheduled() {
        Log.e(TAG, "scheduled: >>>>");
    }

    @Callback
    public void taskExpiredCallback() {
        Log.e(TAG, "taskExpiredCallback: >>>>");
    }

    //开启延迟任务（10s后执行该方法）
    @Delay(key = "test", delay = 10000L)
    public void delay() {
        Log.e(TAG, "delay: >>>>>");
    }

    //移除延迟任务
    @DelayAway(key = "test")
    public void cancelDelay() {
        Log.e(TAG, "cancelDelay: >>>>");
    }

    //1、在需要进行拦截的方法添加注解
    @Intercept("login_intercept")
    public void loginIntercept() {
        Log.e(TAG, "intercept: 已登陆>>>>");
    }
}
