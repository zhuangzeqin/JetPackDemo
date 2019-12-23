package com.eeepay.zzq.jetpackdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eeepay.zzq.jetpackdemo.aop.AopActivity;
import com.eeepay.zzq.jetpackdemo.bean.DataBean;
import com.eeepay.zzq.jetpackdemo.databing.ListViewActivity;
import com.eeepay.zzq.jetpackdemo.databing.TestDataActivity;
import com.eeepay.zzq.jetpackdemo.lifecycle.BroadCastObserver;
import com.eeepay.zzq.jetpackdemo.ui.act.NavHostActivity;
import com.eeepay.zzq.jetpackdemo.utils.GsonUtil;
import com.eeepay.zzq.jetpackdemo.utils.cache.LruCacheDataHelper;
import com.eeepay.zzq.jetpackdemo.viewmodel.ChronoActivity;
import com.eeepay.zzq.jetpackdemo.viewmodel.SeekBarActivity;
import com.eeepay.zzq.jetpackdemo.viewmodel.ViewModelActivity;
import com.eeepay.zzq.jetpackdemo.workmanager.WorknanagerActivity;

import java.util.ArrayList;
import java.util.List;

import cn.com.superLei.aoparms.AopArms;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_msg;
    private Button btn, btn_list, btn_navigation, btn_viewmodel, btn_viewmodel2,
            btn_viewmodel3, btn_workmanager, btn_cache, btn_aop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AopArms.init(this);
        tv_msg = (TextView) findViewById(R.id.tv_msg);
        btn = (Button) findViewById(R.id.btn);
        btn_list = (Button) findViewById(R.id.btn_list);
        btn_navigation = (Button) findViewById(R.id.btn_navigation);
        btn_viewmodel = (Button) findViewById(R.id.btn_viewmodel);
        btn_viewmodel2 = (Button) findViewById(R.id.btn_viewmodel2);
        btn_viewmodel3 = (Button) findViewById(R.id.btn_viewmodel3);
        btn_workmanager = (Button) findViewById(R.id.btn_workmanager);
        btn_cache = (Button) findViewById(R.id.btn_cache);
        btn_aop = (Button) findViewById(R.id.btn_aop);
        btn_viewmodel.setOnClickListener(this);
        btn_viewmodel2.setOnClickListener(this);
        btn_viewmodel3.setOnClickListener(this);
        btn_workmanager.setOnClickListener(this);
        btn_cache.setOnClickListener(this);
        btn_aop.setOnClickListener(this);
//        //通过调用生命周期类的addObserver()方法并传递观察者的实例来添加观察者。
//        getLifecycle().addObserver(new MyObserver());
        BroadCastObserver mBroadCastObserver = new BroadCastObserver(this);
        getLifecycle().addObserver(mBroadCastObserver);
        tv_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setAction("xxx");
                intent.putExtra("content", "广播1号");
                sendBroadcast(intent);
            }
        });
        /** ------注释说明--跳转到databingding------ **/
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestDataActivity.class);
                startActivity(intent);
            }
        });   /** ------注释说明--跳转到databingding  listview------ **/
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });
        /** ------注释说明---navigation 导航组件视图----- **/
        btn_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NavHostActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_viewmodel://viemodel
                startActivity(new Intent(MainActivity.this, ViewModelActivity.class));
                break;
            case R.id.btn_viewmodel2://viemodel2
                startActivity(new Intent(MainActivity.this, ChronoActivity.class));
                break;
            case R.id.btn_viewmodel3://viemodel3
                startActivity(new Intent(MainActivity.this, SeekBarActivity.class));
                break;
            case R.id.btn_workmanager://Worknanager
                startActivity(new Intent(MainActivity.this, WorknanagerActivity.class));
                break;
            case R.id.btn_aop://aop
                startActivity(new Intent(MainActivity.this, AopActivity.class));
                break;
            case R.id.btn_cache://三级缓存
                ArrayList<DataBean> dataBeans = initData();
                LruCacheDataHelper.getInstance().addObjectToCache("userdata", dataBeans);
                //1 内存中获取
                String jsonString  = LruCacheDataHelper.getInstance().getObjectFromCache("userdata");
                List<DataBean> dataBeans1 = GsonUtil.jsonToList(jsonString, DataBean.class);
                Toast.makeText(this, dataBeans1.get(1).toString(), Toast.LENGTH_SHORT).show();
                LruCacheDataHelper.getInstance().removeLruCacheData("userdata");
                //2 内存中没有了---->磁盘中获取
                String jsonString2  = LruCacheDataHelper.getInstance().getObjectFromCache("userdata");
                List<DataBean> dataBeans2 = GsonUtil.jsonToList(jsonString2, DataBean.class);
                Toast.makeText(this, dataBeans2.get(2).toString(), Toast.LENGTH_SHORT).show();
                //3 内存中,磁盘中都没有--从网络上获取
                LruCacheDataHelper.getInstance().cleanAllData();
                String jsonString3  = LruCacheDataHelper.getInstance().getObjectFromCache("userdata");
                List<DataBean> dataBeans3 = GsonUtil.jsonToList(jsonString3, DataBean.class);
                Toast.makeText(this, dataBeans3.get(3).toString(), Toast.LENGTH_SHORT).show();

                break;
            default:

                break;
        }
    }

    private ArrayList<DataBean> initData() {
        ArrayList<DataBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DataBean user = new DataBean();
            user.setName("zzqyhb不小心:" + i);
            user.setId(i);
            list.add(user);
        }
        return list;
    }
}
