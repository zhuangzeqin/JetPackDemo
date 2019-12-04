package com.eeepay.zzq.jetpackdemo.utils.cache;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.eeepay.zzq.jetpackdemo.bean.DataBean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * 描述：二级缓存的工具类
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

    /**
     * ------注释说明 单例模式--------
     **/
    private LruCacheDataHelper(Context context) {
        try {
            mDiskLruCacheUtil = new DiskLruCacheUtil(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ------注释说明 单例模式--------
     **/
    public static LruCacheDataHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (LruCacheDataHelper.class) {
                if (mInstance == null) {
                    mInstance = new LruCacheDataHelper(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 每次添加到缓存里，先移除；再添加进来；确定是最新的数据
     *
     * @param key
     * @param value
     */
    public void addObjectToCache(@NonNull final String key, @NonNull Serializable value) {
        /** 从缓存中获取是否存在； 如果存在则先移除；然后在添加 **/
        removeLruCacheData(key);
        /** ------注释说明---磁盘缓存----- **/
        removeDiskCacheData(key);
        //放进内存中
        LruCacheUtil.getInstance().addObjectToMemoryCache(key, value);
        //放进磁盘中
        if (mDiskLruCacheUtil != null)
            mDiskLruCacheUtil.put(key, value);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 典型的二级缓存机制
     * 1 检查是否在内存中，如果是则直接返回。
     * 2 否则后则检查是否在磁盘上,如果在磁盘上，则存放一份在内存中，接着放回给调用方
     * 3 可在扩展三级缓存 《《《如果本地获取也为空；就从网络上获取(再次存放disk本地；存放一份在内存中)》》》
     * 依次从LruCache、DiskLruCache获取，最后才网络
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getObjectFromCache(@NonNull final String key) {
        /** 从缓存中获取 **/
        Object object = LruCacheUtil.getInstance().getObjectFromMemCache(key);
        /** 缓存不为空 直接return 出去**/
        if (object != null) {
            Log.i(TAG, "从缓存获取 getObjectFromMemCache: " + ((T) object).toString());
            return (T) object;
        }
        /** 从本地磁盘上 **/
        T localObject = mDiskLruCacheUtil.getAsSerializable(key);
        if (localObject != null) {
            Log.i(TAG, "从本地获取 getDateFromLocal: " + localObject.toString());
            //放进内存中
            LruCacheUtil.getInstance().addObjectToMemoryCache(key, localObject);
            return localObject;
        }
        //***********************如果本地获取也为空；就从网络上获取(再次存放本地；存放缓存后续可以在进行扩展)**************************
        //getDataFromNetWork 比如我主动请求后台获取数据，得到数据后
        //放进内存中
//        LruCacheUtil.getInstance().addObjectToMemoryCache(key, localObject);
        //放进磁盘中
//        if (mDiskLruCacheUtil != null)
//            mDiskLruCacheUtil.put(key, value);
        /** ------注释说明---- 模拟网络上获取数据---- **/
        DataBean dataFromNetWork = getDataFromNetWork();
        if (dataFromNetWork != null) {
            Log.i(TAG, "从网络上获取数据 getDataFromNetWork: " + dataFromNetWork.toString());
            //放进内存中
            LruCacheUtil.getInstance().addObjectToMemoryCache(key, dataFromNetWork);
            //放进磁盘中
            if (mDiskLruCacheUtil != null)
                mDiskLruCacheUtil.put(key, dataFromNetWork);
            return (T) dataFromNetWork;
        }
        return null;
    }

    /**
     * 模拟网络上获取数据
     *
     * @return
     */
    private DataBean getDataFromNetWork() {
        try {
            Thread.sleep(1000);
            DataBean dataBean = new DataBean(33, "我是从网络获取的数据.....");
            return dataBean;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
