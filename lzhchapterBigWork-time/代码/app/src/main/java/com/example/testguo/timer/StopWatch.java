package com.example.testguo.timer;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class StopWatch extends Activity implements OnClickListener {
    TextView textViewAlarm; //闹钟选项
    TextView textViewTime;  //世界时钟选项
    TextView textViewStopWatch; //秒表选项
    TextView textViewTimer; //计时器选项
    private TextView minuteTv, secondTv, longmillTv;
    private Button resetBtn, startBtn;
    private ListView listView;
    private TimeAdapter adapter;
    private ArrayList<String> list = new ArrayList<String>();
    private boolean isPaused = false;
    private String timeUsed;
    private long timeUsedInsec;
    private boolean leftBtnFlag = false;// 判断是复位还是计次,ture为计次
    private boolean rightBtnFlag = true;// 判断是开始还是暂停,true为开始

    private static final int TICK_WHAT = 2;
    private static final int TIME_TO_SEND = 100;

    private Handler uiHandle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case TICK_WHAT:
                    if (!isPaused) {
                        addTimeUsed();
                        updateClockUI();
                    }
                    uiHandle.sendEmptyMessageDelayed(TICK_WHAT, TIME_TO_SEND);
                    break;
                default:
                    break;
            }
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.stopwatch);
        textViewAlarm = findViewById(R.id.textViewAlarm);
        textViewTime = findViewById(R.id.textViewTime);
        textViewStopWatch = findViewById(R.id.textViewStopWatch);
        textViewTimer = findViewById(R.id.textViewTimer);

        //切换到闹钟界面
        textViewAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StopWatch.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //切换到世界时钟页面
        textViewTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StopWatch.this, Time.class);
                startActivity(intent);
            }
        });
        //切换到计时器页面
        textViewTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StopWatch.this, CountDownTime.class);
                startActivity(intent);
            }
        });

        initView();
        setListener();
    }

    private void initView() {
        minuteTv = findViewById(R.id.minute);
        secondTv = findViewById(R.id.second);
        longmillTv = findViewById(R.id.longmill);
        listView = findViewById(R.id.listview);
        resetBtn = findViewById(R.id.reset);
        startBtn = findViewById(R.id.start_and_stop);
        if (leftBtnFlag) {
            resetBtn.setText("计次");
        } else {
            resetBtn.setText("复位");
        }
        if (rightBtnFlag) {
            startBtn.setText("启动");
        } else {
            startBtn.setText("暂停");
        }
        adapter = new TimeAdapter(this, list);
        listView.setAdapter(adapter);
    }

    private void setListener() {
        resetBtn.setOnClickListener(this);
        startBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset:
                if (rightBtnFlag) {
                    uiHandle.removeMessages(TICK_WHAT);
                    timeUsedInsec = 0;
                    minuteTv.setText("00");
                    secondTv.setText("00");
                    longmillTv.setText("0");
                    list.removeAll(list);
                    adapter.notifyDataSetChanged();
                } else {
                    list.add(timeUsed);
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.start_and_stop:
                rightBtnFlag = !rightBtnFlag;
                if (rightBtnFlag) {
                    startBtn.setText("启动");
                    resetBtn.setText("复位");
                    leftBtnFlag = false;
                    isPaused = true;
                    uiHandle.removeMessages(TICK_WHAT);
                } else {
                    startBtn.setText("暂停");
                    resetBtn.setClickable(true);
                    startTime();
                    resetBtn.setText("计次");
                    leftBtnFlag = true;
                    isPaused = false;
                }
                break;

            default:
                break;
        }
    }

    private void startTime() {
        uiHandle.sendEmptyMessageDelayed(TICK_WHAT, TIME_TO_SEND);
    }

   //更新时间的显示
    private void updateClockUI() {
        minuteTv.setText(getMin());
        secondTv.setText(getSec());
        longmillTv.setText(getLongMill());
    }

    public void addTimeUsed() {
        timeUsedInsec += 100 ;
        timeUsed = this.getMin() + ":" + this.getSec()+":"+this.getLongMill();
    }

    public String getMin() {
        long min = (timeUsedInsec) / 60000;
        return min < 10 ? "0" + min : min+"";
    }

    public String getSec() {
        long sec = (timeUsedInsec / 1000) % 60;
        return sec < 10 ? "0" + sec : sec+"";
    }

    public String getLongMill() {
        long longmill = (timeUsedInsec/100) % 10;
        return longmill + "";
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPaused = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPaused = false;
    }

}
