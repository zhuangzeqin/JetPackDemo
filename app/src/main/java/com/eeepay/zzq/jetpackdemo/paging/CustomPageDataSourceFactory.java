package com.eeepay.zzq.jetpackdemo.paging;

import androidx.paging.DataSource;

import com.eeepay.zzq.jetpackdemo.bean.DataBean;

/**
 * 描述：创建DataSourceFactory类，创建PageList的使用用得着
 * 作者：zhuangzeqin
 * 时间: 2019/12/2-17:08
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class CustomPageDataSourceFactory extends DataSource.Factory<Integer, DataBean> {
    private DataRepository repository;

    public CustomPageDataSourceFactory(DataRepository repository) {
        this.repository = repository;
    }

    @Override
    public DataSource<Integer, DataBean> create() {
        return new CustomPageDataSource(repository);
    }
}
