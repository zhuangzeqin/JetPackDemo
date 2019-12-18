package com.eeepay.zzq.jetpackdemo.viewmodel;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.eeepay.zzq.jetpackdemo.R;
import com.eeepay.zzq.jetpackdemo.livedata.LiveDataTimerViewModel;

/**
 * 描述：a Fragment
 * 作者：zhuangzeqin
 * 时间: 2019/11/22-15:30
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class AFragment extends Fragment {

    private SeekBar mSeekBar;
    private SeekBarViewModel mSeekBarViewModel;
    private LiveDataTimerViewModel mLiveDataTimerViewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSeekBar = (SeekBar) view.findViewById(R.id.seekBar);
        mSeekBarViewModel = ViewModelProviders.of(this).get(SeekBarViewModel.class);
        mLiveDataTimerViewModel = ViewModelProviders.of(this).get(LiveDataTimerViewModel.class);
        subscribeSeekBar();
        subscribe();
    }
    private void subscribeSeekBar() {
        // Update the ViewModel when the SeekBar is changed.
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                //如果是用户改变了seekbar的进度就更新ViewModel
                if (fromUser) {
                    Log.d("Step5", "Progress changed!");
                    mSeekBarViewModel.seekbarValue.setValue(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        // 当ViewModel改变了时候，更新seekBar的进度
        mSeekBarViewModel.seekbarValue.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer value) {
                if (value != null) {
                    Toast.makeText(getContext(), "value:"+value, Toast.LENGTH_SHORT).show();
                    mSeekBar.setProgress(value);
                }
            }
        });
    }

    /**
     * 新建一个Observer,然后订阅 mLiveDataTimerViewModel.getElapsedTime()
     */
    private void subscribe() {
        final Observer<Long> elapsedTimeObserver = new Observer<Long>() {
            @Override
            public void onChanged(@Nullable final Long aLong) {
                Log.d("ChronoActivity3", "Updating timer"+aLong);
            }
        };

        mLiveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.a_fragment, container, false);
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
