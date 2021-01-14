package com.example.testguo.timer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;



//闹钟设置
public class AlarmActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //存放音乐
        //mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
        //mediaPlayer.setLooping(true);
        //mediaPlayer.start();
        new AlertDialog.Builder(AlarmActivity.this).setTitle("闹钟").setMessage("时间到了").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //mediaPlayer.stop();
                AlarmActivity.this.finish();
            }
        }).show();
    }
}
