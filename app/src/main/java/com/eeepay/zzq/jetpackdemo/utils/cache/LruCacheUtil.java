package com.eeepay.zzq.jetpackdemo.utils.cache;

import android.util.Log;

import androidx.collection.LruCache;

/**
  * 描述：内存缓存工具类
  * 它的主要算法原理是把最近使用的对象用强引用存储在 LinkedHashMap 中，
  * 并且把最近最少使用的对象在缓存值达到预设定值之前从内存中移除。
  * 保存一个强引用来限制内容数量，每当Item被访问的时候，此Item就会移动到队列的头部。
  * 当cache已满的时候加入新的item时，在队列尾部的item会被回收。
  * 不允许key或者value为null
  * 当get（），put（），remove（）返回值为null时，key相应的项不在cache中
  * 作者：zhuangzeqin
  * 时间: 2017/7/5-16:39
  * 邮箱：zzq@eeepay.cn
  */
public class LruCacheUtil {
	private static final String TAG = "LruCacheUtil";

	/** 内部类容器法 **/
	private static class SingletonHolder {
		private static final LruCacheUtil ourInstance = new LruCacheUtil();
	}
	
	public static LruCacheUtil getInstance() {
		return SingletonHolder.ourInstance;
	}

	private LruCache<String, Object> mMemoryCache;

	private LruCacheUtil() {
		// 获取到可用内存的最大值，使用内存超出这个值会引起OutOfMemory异常。
		// LruCache通过构造函数传入缓存值，以KB为单位。
		int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		// 使用最大可用内存值的1/8作为缓存的大小。
		int cacheSize = maxMemory / 8;
		Log.i(TAG, "LruCacheUtil缓存的大小: "+cacheSize);
		mMemoryCache = new LruCache<String, Object>(cacheSize);// 也可以写死 1 * 1024 * 1024
	}

	/**
	 * 如果内存中没有就放进内存中
	 * @param key
	 * @param
	 */
	public void addObjectToMemoryCache(String key , Object value)
	{
		mMemoryCache.put(key, value);
	}

	/**
	 * 从缓存中获取
	 * @param key
	 * @return
	 */
	public Object getObjectFromMemCache(String key) {
		return mMemoryCache.get(key);
	}
	/**
	 *从缓存中移除
	 */
	public void removeObjectByKey(String key)
	{
		mMemoryCache.remove(key);
	}
	/**
	 * 清空cacke
	 */
	public void clearCache()
	{
		mMemoryCache.evictAll();
	}

	/**
	 * 已经存储的大小
	 * @return
	 */
	public int getAlreadyCachedSize()
	{
		return mMemoryCache.size();
	}

	/**
	 * 规定的最大存储空间
	 * @return
	 */
	public int getCachedSize()
	{
		return mMemoryCache.maxSize();
	}
}
