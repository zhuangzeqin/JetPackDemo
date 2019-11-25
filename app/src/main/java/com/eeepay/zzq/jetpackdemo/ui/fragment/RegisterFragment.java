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
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.eeepay.zzq.jetpackdemo.R;

/**
  * 描述：注册的Fragment
  * 作者：zhuangzeqin
  * 时间: 2019/11/22-15:30
  * 邮箱：zzq@eeepay.cn
  * 备注:
  */
public class RegisterFragment extends Fragment {

   @Override
   public void onAttach(@NonNull Context context) {
       super.onAttach(context);
   }

   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       super.onViewCreated(view, savedInstanceState);
       String email = RegisterFragmentArgs.fromBundle(getArguments()).getEMAIL();
       TextView tv_register = (TextView) view.findViewById(R.id.tv_register);
       tv_register.setText("注册页面:" + email);

       tv_register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               try {
                   NavDirections navDirections = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment();
                   NavHostFragment
                           .findNavController(RegisterFragment.this)
                           .navigate(navDirections);
               }catch (Exception ex)
               {
                   ex.printStackTrace();
               }

           }
       });
   }

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View root = inflater.inflate(R.layout.register_fragment, container, false);
       return root;
   }

   @Override
   public void onDestroy() {
       super.onDestroy();
   }
}
