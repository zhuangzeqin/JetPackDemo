package com.eeepay.zzq.jetpackdemo.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.eeepay.zzq.jetpackdemo.R;

/**
 * 描述：欢迎页面 登录按钮 和 注册按钮
 * 作者：zhuangzeqin
 * 时间: 2019/11/22-15:24
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class WelcomeFragment extends Fragment implements View.OnClickListener {
    private Button btnLogin, btnRegister;//登录注册按钮

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //在 Navigation 里，页面的跳转是交给 NavController 来处理的，获取 NavController 的方法有这么三种：
//        NavHostFragment.findNavController(this);
//        Navigation.findNavController(getActivity(), @IdRes int viewId);
//        NavController navController = Navigation.findNavController(view);


        btnLogin = view.findViewById(R.id.btn_login);
        btnRegister = view.findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.welcome_fragment, container, false);
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Bundle bundle = new Bundle();
                bundle.putString("userName", "zzq");
//                findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment, bundle,navOption)
                NavHostFragment
                        .findNavController(WelcomeFragment.this)
                        .navigate(R.id.action_welcomeFragment_to_loginFragment, bundle);
                break;
            case R.id.btn_register:
                /**
                 * 在使用safeargs插件生成代码时,分为发送方和接收方两个类，发送方命名为<类名>+"Directions",
                 * 接受方命名为<类名>+"Args",action会成为一个方法(如此处是从FirstFragment跳转到ForthFragment,发送方命名为<FirstFragmentDirections>,接收方命名为<ForthFragmentArgs>,action:action_firstFragment_to_forthFragment会成为:FirstFragmentDirections.action_firstFragment_to_forthFragment())
                 * 如果不用Safe Args，action可以由Navigation.createNavigateOnClickListener(R.id.next_action, null)方式生成.
                 */
                WelcomeFragmentDirections.ActionWelcomeFragmentToRegisterFragment action =
                        WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment().setEMAIL("1546374673@qq.com");
                NavHostFragment
                        .findNavController(WelcomeFragment.this)
                        .navigate(action);
                break;
            default:

                break;
        }
    }
}
