package com.eeepay.zzq.jetpackdemo.utils.cache;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.eeepay.zzq.APP;
import com.eeepay.zzq.jetpackdemo.bean.DataBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * 描述：二级缓存的工具类 可以扩展为三级缓存
 * 对于访问频率比较高，对那些大量占用应用程序宝贵内存的序列化对象
 * 作者：zhuangzeqin
 * 时间: 2019/12/4-12:12
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public final class LruCacheDataHelper {
    private final static String TAG = LruCacheDataHelper.class.getSimpleName();
    private static volatile LruCacheDataHelper mInstance = null;
    private static volatile DiskLruCacheUtil mDiskLruCacheUtil = null;
    private final static Gson MGSON = new Gson();//Gson 解析

    /**
     * ------注释说明 单例模式--------
     **/
    private LruCacheDataHelper() {
        initDiskCache();
    }

    /**
     * 初始化DiskLruCacheUtil
     */
    private void initDiskCache() {
        try {
            mDiskLruCacheUtil = new DiskLruCacheUtil(APP.getApplicationInstance().getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ------注释说明 单例模式--------
     **/
    public static LruCacheDataHelper getInstance() {
        if (mInstance == null) {
            synchronized (LruCacheDataHelper.class) {
                if (mInstance == null) {
                    mInstance = new LruCacheDataHelper();
                }
            }
        }
        return mInstance;
    }

    /**
     * 每次添加到缓存里，先移除；再添加进来；确定是最新的数据
     *
     * @param key
     */
    public void addObjectToCache(@NonNull final String key, @NonNull Object object) {
        /** 从缓存中获取是否存在； 如果存在则先移除；然后在添加 **/
        removeLruCacheData(key);
        /** ------注释说明---磁盘缓存----- **/
        removeDiskCacheData(key);
        //转换为Json字符串
        String jsonData = MGSON.toJson(object);
        //放进内存中
        LruCacheUtil.getInstance().addObjectToMemoryCache(key, jsonData);
        //放进磁盘中
        putDiskLruCacheData(key, jsonData);
    }

    /**
     * 放进磁盘中
     *
     * @param key
     * @param jsonData
     */
    private void putDiskLruCacheData(@NonNull String key, @NonNull String jsonData) {
        if (mDiskLruCacheUtil == null)
            initDiskCache();
        if (mDiskLruCacheUtil != null) {
            mDiskLruCacheUtil.put(key, jsonData);
        }
    }


    /**
     * 从缓存中移除
     *
     * @param key
     */
    public void removeLruCacheData(@NonNull final String key) {
        /** 从缓存中获取 **/
        Object object = LruCacheUtil.getInstance().getObjectFromMemCache(key);
        /** 缓存不为空 **/
        if (object != null) {
            //先移除
            LruCacheUtil.getInstance().removeObjectByKey(key);
        }
    }

    /**
     * 从磁盘中移除
     *
     * @param key
     */
    public void removeDiskCacheData(@NonNull final String key) {
        /** ------注释说明---磁盘缓存----- **/
        if (mDiskLruCacheUtil != null) {
            InputStream inputStream = mDiskLruCacheUtil.get(key);
            if (inputStream != null)
                mDiskLruCacheUtil.remove(key);  //先移除
        }
    }

    /**
     * 清空所有的缓存数据
     */
    public void cleanAllData() {
        try {
            LruCacheUtil.getInstance().clearCache();
            if (mDiskLruCacheUtil != null)
                mDiskLruCacheUtil.delete();
            if (mDiskLruCacheUtil.isClosed())
                initDiskCache();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 典型的三级缓存机制
     * 1 检查是否在内存中，如果是则直接返回。
     * 2 否则后则检查是否在磁盘上,如果在磁盘上，则存放一份在内存中，接着返回给调用方
     * 3 可在扩展三级缓存 《《《如果本地获取也为空；就从网络上获取(再次存放disk本地；存放一份在内存中)》》》
     * 依次从LruCache、DiskLruCache获取，最后才网络
     *
     * @param key
     * @return
     */
    public String getObjectFromCache(@NonNull final String key) {
        /** 从缓存中获取 **/
        String object = (String) LruCacheUtil.getInstance().getObjectFromMemCache(key);
        /** 缓存不为空 直接return 出去**/
        if (!TextUtils.isEmpty(object)) {
            Log.i(TAG, "从缓存获取 getObjectFromMemCache: " + object);
            return object;
        }
        /** 从本地磁盘上 **/
        if (mDiskLruCacheUtil == null) {
            /** ------注释说明--从网络上获取的数据------ **/
            String dataFromNetWork = getDataFromNetWork(key);
            return dataFromNetWork;
        }
        /** 从本地磁盘上 **/
        String localObject = mDiskLruCacheUtil.getAsString(key);
        if (!TextUtils.isEmpty(localObject)) {
            Log.i(TAG, "从本地获取 getDateFromLocal: " + localObject);
            //放进内存中
            LruCacheUtil.getInstance().addObjectToMemoryCache(key, localObject);
            return localObject;
        } else {
            /** ------注释说明--从网络上获取的数据------ **/
            String dataFromNetWork = getDataFromNetWork(key);
            return dataFromNetWork;
        }
        //3 ************扩展************
        // 可扩展三级缓存 如果本地获取也为空；就从网络上获取一般也都是Json字符串返回
        // 1 再次存放内存；
        // 2 再次存放磁盘缓存)
        // **************************************
//        return null;
    }

    /**
     * 如果本地获取也为空；就从网络上获取(再次存放本地；存放缓存)
     **/
//    protected abstract Object getDataFromNetWork();

    /**
     * 模拟网络上获取数据
     *
     * @return
     */
    private String getDataFromNetWork(@NonNull final String key) {
        try {
            //***********************如果本地获取也为空；就从网络上获取(再次存放本地；存放缓存后续可以在进行扩展)**************************
            // 比如我主动请求后台获取数据，得到数据后
            Thread.sleep(1000);
            ArrayList<DataBean> dataBeans = initData();
            //转换为Json字符串
            String jsonData = MGSON.toJson(dataBeans);
            if (TextUtils.isEmpty(jsonData))
                return null;
            //1 放进内存中
            LruCacheUtil.getInstance().addObjectToMemoryCache(key, jsonData);
            //2 放进磁盘中
            putDiskLruCacheData(key, jsonData);
            return jsonData;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<DataBean> initData() {
        ArrayList<DataBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DataBean user = new DataBean();
            user.setName("我是从网络获取的数据:" + i);
            user.setId(i);
            list.add(user);
        }
        return list;
    }
}
