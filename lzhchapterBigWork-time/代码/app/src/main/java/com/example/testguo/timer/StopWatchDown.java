package com.example.testguo.timer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


//对应stopwatchdown的xml的布局文件，StopWatch秒表页面下方的秒表计次布局文件
public class StopWatchDown extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stopwatchdown,container,false);
    }
}
