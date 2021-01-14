package com.example.testguo.timer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


//对应stopwatchabove的xml的布局文件，StopWatch秒表页面上方的秒表按钮布局文件
public class StopWatchAbove extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stopwatchabove,container,false);
    }
}
