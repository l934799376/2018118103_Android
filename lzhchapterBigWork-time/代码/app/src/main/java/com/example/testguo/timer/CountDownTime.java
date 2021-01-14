package com.example.testguo.timer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;



public class CountDownTime extends Activity {
    TextView textViewAlarm; //闹钟选项
    TextView textViewTime;  //世界时钟选项
    TextView textViewStopWatch; //秒表选项
    TextView textViewTimer; //计时器选项
    private static TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
        textViewAlarm = findViewById(R.id.textViewAlarm);
        textViewTime = findViewById(R.id.textViewTime);
        textViewStopWatch = findViewById(R.id.textViewStopWatch);
        textViewTimer = findViewById(R.id.textViewTimer);

        tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tabTimer").
                setIndicator("计时器").setContent(R.id.tabTimer));

        //切换到闹钟界面
        textViewAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CountDownTime.this, MainActivity.class);
                startActivity(intent);

            }
        });
        //切换到世界时钟页面
        textViewTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CountDownTime.this, Time.class);
                startActivity(intent);
            }
        });
        //切换到秒表页面
        textViewStopWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CountDownTime.this, StopWatch.class);
                startActivity(intent);
            }
        });
    }

}
