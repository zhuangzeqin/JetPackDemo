package com.eeepay.zzq.jetpackdemo.utils.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * 描述： * SoftRefenceCache
 * 软引用是用来表示某个引用会被GC（垃圾处理器）收集的类。
 * 当有引用指向某个obj的时候，通常发生GC的时候不会把这个对象处理掉，但是被软引用包装的对象，当应用内存将要被耗尽的时候-->即将发生OOM，
 * 垃圾处理器就会把它带走。这么看来，软应用的生命周期还是很长的，可以用来做缓存处理。
 * * @param <K> key的类型.
 * * @param <V> value的类型.
 * 作者：zhuangzeqin
 * 时间: 2019/12/30-14:36
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public final class SoftReferenceCache<K, V> {
    private final HashMap<K, SoftReference<V>> mCache;

    public SoftReferenceCache() {
        mCache = new HashMap<K, SoftReference<V>>();
    }

    /**
     * 将对象放进缓存中，这个对象可以在GC发生时被回收
     *
     * @param key   key的值.
     * @param value value的值型.
     */

    public void put(K key, V value) {
        mCache.put(key, new SoftReference<V>(value));
    }

    /**
     * 从缓存中获取value
     *
     * @param key
     * @return 如果找到的话返回value，如果被回收或者压根儿没有就返* 回null
     */

    public V get(K key) {
        V value = null;

        SoftReference<V> reference = mCache.get(key);

        if (reference != null) {
            value = reference.get();
        }

        return value;
    }
}
