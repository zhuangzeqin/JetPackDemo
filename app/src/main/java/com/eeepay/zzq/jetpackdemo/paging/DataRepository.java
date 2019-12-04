package com.eeepay.zzq.jetpackdemo.paging;

import com.eeepay.zzq.jetpackdemo.bean.DataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2019/12/2-16:33
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class DataRepository {
    private static List<DataBean> data = new ArrayList<>();

    static {
        /** ------注释说明---DataRepository初始化的时候我们对data初始化101条数据进去----- **/
        for (int i = 0; i < 100; i++) {
            data.add(new DataBean(i, "name " + i));
        }
    }

    /**
     * 初始加载数据
     *
     * @param size
     * @return
     */
    public List<DataBean> loadData(int size) {
        return data.subList(0, size);
    }

    /**
     * 根据index加载数据
     *
     * @param index
     * @param size
     * @return
     */
    public List<DataBean> loadData(int index, int size) {
        if (index >= data.size() - 1 || index < 1) {
            return null;
        }

        if (index + size > data.size()) {
            return data.subList(index + 1, data.size());
        }
        return data.subList(index + 1, index + size);
    }

    /**
     * 分页加载数据
     *
     * @param page
     * @param size
     * @return
     */
    public List<DataBean> loadPageData(int page, int size) {
        int totalPage = 0;
        if (data.size() % size == 0) {
            totalPage = data.size() / size;
        } else {
            totalPage = data.size() / size + 1;
        }

        if (page > totalPage || page < 1) {
            return null;
        }

        if (page == totalPage) {
            return data.subList((page - 1) * size, data.size());
        }
        return data.subList((page - 1) * size, page * size);
    }
}
