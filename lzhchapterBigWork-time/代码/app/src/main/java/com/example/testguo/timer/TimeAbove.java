package com.example.testguo.timer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



//对应timeabove的xml的布局文件，Time世界始终页面上方的地图和标签布局文件
public class TimeAbove extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.timeabove,container,false);
    }
}
