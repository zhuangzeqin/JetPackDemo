package com.eeepay.zzq.jetpackdemo.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.eeepay.zzq.jetpackdemo.R;

/**
 * 描述：登录的Fragment
 * 作者：zhuangzeqin
 * 时间: 2019/11/22-15:30
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class LoginFragment extends Fragment {
    private Bundle mBundle;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBundle = getArguments();//参数Bunlder
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv_loginmsg = (TextView) view.findViewById(R.id.tv_loginmsg);
        String userName = mBundle.getString("userName", "123456");
        tv_loginmsg.setText("登录页面:" + userName);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_fragment, container, false);
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
