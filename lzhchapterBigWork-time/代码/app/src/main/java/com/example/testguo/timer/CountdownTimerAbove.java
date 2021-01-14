package com.example.testguo.timer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



//对应timerabove的xml的布局文件，Timer计时器页面上方的计时器布局文件
public class CountdownTimerAbove extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.countdowntimerabove,container,false);
    }
}
