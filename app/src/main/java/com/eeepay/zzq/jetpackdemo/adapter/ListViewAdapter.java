package com.eeepay.zzq.jetpackdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.eeepay.zzq.jetpackdemo.R;
import com.eeepay.zzq.jetpackdemo.bean.UserBean2;
import com.eeepay.zzq.jetpackdemo.databinding.ListItemBinding;

import java.util.List;

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2019/11/21-17:02
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class ListViewAdapter extends BaseAdapter {
    //数据
    private List<UserBean2> list;

    public ListViewAdapter(List<UserBean2> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ////这个databinding也是根据布局文件item_mvvm而命名的
        ListItemBinding listItemBinding;
        if (convertView == null) {
            listItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item, parent, false);
            //获取convertView
            convertView = listItemBinding.getRoot();
        } else {
            //去除convertView中bangding的dataBinding
            listItemBinding = DataBindingUtil.getBinding(convertView);
        }
        UserBean2 userBean = list.get(position);
        //绑定数据，这里的BR.user根据item布局文件中的变量声明来决定
        listItemBinding.setVariable(com.eeepay.zzq.jetpackdemo.BR.user, userBean);
        listItemBinding.btnUpdate.setOnClickListener(new OnBtnClickListener(1, userBean));
        listItemBinding.btnDelete.setOnClickListener(new OnBtnClickListener(2, position));
        return convertView;

    }

    private class OnBtnClickListener implements View.OnClickListener {
        private int stats;//1,修改；2，删除
        private UserBean2 userBean;
        private int position;

        OnBtnClickListener(int stats, UserBean2 userBean) {
            this.stats = stats;
            this.userBean = userBean;
        }

        OnBtnClickListener(int stats, int position) {
            this.stats = stats;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            switch (stats) {
                case 1:
                    userBean.userName.set("修改后的名字");
                    break;
                case 2:
                    list.remove(position);
                    notifyDataSetChanged();
                    break;
            }
        }
    }
}
