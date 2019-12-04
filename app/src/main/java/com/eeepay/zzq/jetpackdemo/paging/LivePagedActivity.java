package com.eeepay.zzq.jetpackdemo.paging;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.eeepay.zzq.jetpackdemo.R;
import com.eeepay.zzq.jetpackdemo.bean.DataBean;

public class LivePagedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_paged);

        LiveData<DataBean> data = new
                LivePagedListBuilder(new CustomPageDataSourceFactory(new DataRepository()), new PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .build()).build();


//        data.observe(this, Observer {
//            adapter.submitList(it);
//        })
        data.observe(this, new Observer<DataBean>() {
            @Override
            public void onChanged(DataBean dataBean) {
//                adapter.submitList(dataBean);
            }
        });
    }
}
