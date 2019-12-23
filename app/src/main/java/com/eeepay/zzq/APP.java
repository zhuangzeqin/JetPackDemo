package com.eeepay.zzq;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import cn.com.superLei.aoparms.AopArms;
import cn.com.superLei.aoparms.callback.Interceptor;
import cn.com.superLei.aoparms.common.utils.ArmsPreference;

/**
 * 描述：Application 基类
 * 作者：zhuangzeqin
 * 时间: 2019/11/28-11:14
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class APP extends Application {
    private final static String TAG = APP.class.getSimpleName();
    private static APP mInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        AopArms.init(this);
        //（建议,统一处理）在Application中进行进行监听拦截回调
        AopArms.setInterceptor(new Interceptor() {
            @Override
            public boolean intercept(String key, String methodName) throws Throwable {
                Log.e(TAG, "intercept methodName:>>>>>" + methodName);
                if ("login_intercept".equals(key)) {
                    String userId = ArmsPreference.get(mInstance, "userId", "");
                    if (TextUtils.isEmpty(userId)) {
                        Toast.makeText(mInstance, "您还没有登录", Toast.LENGTH_SHORT).show();
                        return true;//代表拦截
                    }
                }
                return false;//放行
            }
        });
    }


//   @Override
//   public void onConfigurationChanged(Configuration newConfig) {
//       if (newConfig.fontScale != 1)//非默认值  设置app字体不随系统字体大小而变化
//           getResources();
//       super.onConfigurationChanged(newConfig);
//   }
//
//   @Override
//   public Resources getResources() {
//       Resources res = super.getResources();
//       if (res.getConfiguration().fontScale != 1) {//非默认值
//           Configuration newConfig = new Configuration();
//           newConfig.setToDefaults();//设置默认
//           res.updateConfiguration(newConfig, res.getDisplayMetrics());
//       }
//       return res;
//   }

    /**
     * 获取APP的实例
     *
     * @return
     */
    public static APP getApplicationInstance() {
        return mInstance;
    }
}
