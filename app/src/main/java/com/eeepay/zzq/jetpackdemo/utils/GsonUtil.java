package com.eeepay.zzq.jetpackdemo.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：gson 工具类
 * 作者：zhuangzeqin
 * 时间: 2019/12/18-15:30
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public final class GsonUtil {
    //使用Gson进行解析Bean
    public static <T> T jsonToBean(String jsonString, Class<T> cls) {
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return t;
    }

//    // 使用Gson进行解析 List<Beans>
//    public static <T> List<T> jsonToList(String jsonString, Class<T> cls) {
////        List<T> list = new ArrayList<T>();
////        try {
////            Gson gson = new Gson();
////            list = gson.fromJson(jsonString, new TypeToken<List<T>>() {
////            }.getType());
////        } catch (Exception e) {
////        }
////        return list;
//
//
//
//    }

    /**
     * 转成list
     * 解决泛型问题
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }
}
