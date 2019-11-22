package com.eeepay.zzq.jetpackdemo.ui.act;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.eeepay.zzq.jetpackdemo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
   * 描述：当前Fragment的容器，包含了当前的导航视图xml
  *     Navigation中最关键的三要素
  *     1 Navigation Graph(New XML resource) 可视化界面可以看出他能够到达的Destination(用户能够到达的屏幕界面)，以及流程关系。
  *     2 NavHostFragment(Layout XML view) 当前Fragment的容器
  *     3 NavController(Kotlin/Java object) 导航的控制者
   * 作者：zhuangzeqin
   * 时间: 2019/11/22-16:25
   * 邮箱：zzq@eeepay.cn
   * 备注:
   */
public class NavHostActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_host);
        NavHostFragment navHostFragment = (NavHostFragment )getSupportFragmentManager().findFragmentById(R.id.my_nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

            }
        });

        mBottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_view);
        NavigationUI.setupWithNavController(mBottomNavigationView, navController);
    }
}
