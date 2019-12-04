package com.eeepay.zzq.jetpackdemo.paging;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.eeepay.zzq.jetpackdemo.bean.DataBean;

import java.util.List;

/**
 * 描述：自定义DataSource
 * 作者：zhuangzeqin
 * 时间: 2019/12/2-16:48
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class CustomPageDataSource extends PageKeyedDataSource<Integer, DataBean> {
    private DataRepository repository;
    public CustomPageDataSource(DataRepository repository) {
        this.repository = repository;
    }

    /**
     * 初始加载数据
     * @param params 包装了分页加载的参数
     * @param callback
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, DataBean> callback) {
        //LoadInitialParams 包含了requestedLoadSize和placeholdersEnabled两个属性，requestedLoadSize为加载的数据量，placeholdersEnabled是是否显示占位及当数据为null时显示占位的view
        List<DataBean> data = repository.loadData(params.requestedLoadSize);
        callback.onResult(data, null, 2);
    }

    /**
     * 向前分页加载数据
     * @param params 包装了分页加载的参数
     * @param callback
     */
    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataBean> callback) {
        List<DataBean> data = repository.loadPageData(params.key,params.requestedLoadSize);
        callback.onResult(data, params.key - 1);
    }

    /**
     * 向后分页加载数据
     * @param params 包装了分页加载的参数
     * @param callback
     */
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataBean> callback) {
        List<DataBean> data = repository.loadPageData(params.key, params.requestedLoadSize);
        callback.onResult(data, params.key + 1);
    }
}
