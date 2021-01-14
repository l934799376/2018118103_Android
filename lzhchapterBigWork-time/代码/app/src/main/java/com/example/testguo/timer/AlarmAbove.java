package com.example.testguo.timer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



//对应alarmabove的xml的布局文件，Alarm闹钟页面上方的时钟和标签布局文件
public class AlarmAbove extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.alarmabove,container,false);
    }
}
