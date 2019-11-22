package com.eeepay.zzq.jetpackdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.eeepay.zzq.jetpackdemo.databing.ListViewActivity;
import com.eeepay.zzq.jetpackdemo.databing.TestDataActivity;
import com.eeepay.zzq.jetpackdemo.lifecycle.BroadCastObserver;
import com.eeepay.zzq.jetpackdemo.ui.act.NavHostActivity;

public class MainActivity extends AppCompatActivity {
    private TextView tv_msg;
    private Button btn,btn_list,btn_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_msg = (TextView)findViewById(R.id.tv_msg);
        btn = (Button)findViewById(R.id.btn);
        btn_list = (Button)findViewById(R.id.btn_list);
        btn_navigation = (Button)findViewById(R.id.btn_navigation);
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
}
