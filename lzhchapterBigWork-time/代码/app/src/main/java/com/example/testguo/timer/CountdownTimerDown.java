package com.example.testguo.timer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



//对应timerdown的xml的布局文件，Timer计时器页面下方的空白布局文件
public class CountdownTimerDown extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.countdowntimerdown,container,false);
    }
}
