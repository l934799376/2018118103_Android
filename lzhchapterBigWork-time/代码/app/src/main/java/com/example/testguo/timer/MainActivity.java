package com.example.testguo.timer;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity{

    TextView textViewAlarm; //闹钟选项
    TextView textViewTime;  //世界时钟选项
    TextView textViewStopWatch; //秒表选项
    TextView textViewTimer; //计时器选项
    TextView textView1; //闹钟时间1
    TextView textView2; //闹钟时间2
    TextView textView3; //闹钟时间3
    TextView textView4; //闹钟时间4
    TextView textView5; //闹钟时间5
    TextView textView6; //闹钟开启状态1
    TextView textView7; //闹钟开启状态2
    TextView textView8; //闹钟开启状态3
    TextView textView9; //闹钟开启状态4
    TextView textView10;    //闹钟开启状态5
    Switch aSwitch1;    //闹钟选择开启与否1
    Switch aSwitch2;    //闹钟选择开启与否2
    Switch aSwitch3;    //闹钟选择开启与否3
    Switch aSwitch4;    //闹钟选择开启与否4
    Switch aSwitch5;    //闹钟选择开启与否5
    ImageView imageButton;    //添加闹钟按钮
    AnalogClock analogClock;    //上方时钟
    TimerDatabase timerDatabase;    //Timer数据库
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewAlarm = findViewById(R.id.textViewAlarm);
        textViewTime = findViewById(R.id.textViewTime);
        textViewStopWatch = findViewById(R.id.textViewStopWatch);
        textViewTimer = findViewById(R.id.textViewTimer);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        textView10 = findViewById(R.id.textView10);
        aSwitch1 = findViewById(R.id.switch1);
        aSwitch2 = findViewById(R.id.switch2);
        aSwitch3 = findViewById(R.id.switch3);
        aSwitch4 = findViewById(R.id.switch4);
        aSwitch5 = findViewById(R.id.switch5);
        imageButton = findViewById(R.id.imageButton);
        analogClock = findViewById(R.id.analogClock);
        //new数据库之后创建数据库并建表
        timerDatabase = new TimerDatabase(this, "Timer.db", null, 1);
        timerDatabase.getWritableDatabase();

        //向世界时钟数据表中插入数据
        SQLiteDatabase timedatabase = timerDatabase.getWritableDatabase();
        timedatabase.execSQL("insert into timeset(stat) values (0),(0),(0),(0),(0),(0),(0),(0),(0),(0)");

        //从数据库中查询闹钟1、闹钟2、闹钟3是否存在，存在则显示
        final SQLiteDatabase database = timerDatabase.getWritableDatabase();
        Cursor cursorone = database.rawQuery("select time,either from alarmset where stat=1 LIMIT 1", null);
        if (cursorone.moveToNext()) {
            do {
                String timeone = cursorone.getString(cursorone.getColumnIndex("time"));
                String eitherone = cursorone.getString(cursorone.getColumnIndex("either"));
                if (timeone != null) {
                    textView1.setVisibility(View.VISIBLE);
                    textView1.setText(timeone);
                    textView6.setVisibility(View.VISIBLE);
                    textView6.setText(eitherone);
                    aSwitch1.setVisibility(View.VISIBLE);
                    if (eitherone.equals("开启")) {
                        aSwitch1.setChecked(true);
                    }
                    else {
                        aSwitch1.setChecked(false);
                    }
                }
            }while (cursorone.moveToNext());
        }
        cursorone.close();
        Cursor cursortwo = database.rawQuery("select time,either from alarmset where stat=1 LIMIT 1,1", null);
        if (cursortwo.moveToNext()) {
            do {
                String timetwo = cursortwo.getString(cursortwo.getColumnIndex("time"));
                String  eithertwo = cursortwo.getString(cursortwo.getColumnIndex("either"));
                if (timetwo != null) {
                    textView2.setVisibility(View.VISIBLE);
                    textView2.setText(timetwo);
                    textView7.setVisibility(View.VISIBLE);
                    textView7.setText(eithertwo);
                    aSwitch2.setVisibility(View.VISIBLE);
                    if (eithertwo.equals("开启")) {
                        aSwitch2.setChecked(true);
                    }
                    else {
                        aSwitch2.setChecked(false);
                    }
                }
            }while (cursortwo.moveToNext());
        }
        cursortwo.close();
        Cursor cursorthree = database.rawQuery("select time,either from alarmset where stat=1 LIMIT 2,1", null);
        if (cursorthree.moveToNext()) {
            do {
                String timethree = cursorthree.getString(cursorthree.getColumnIndex("time"));
                String eitherthree = cursorthree.getString(cursorthree.getColumnIndex("either"));
                if (timethree != null) {
                    textView3.setVisibility(View.VISIBLE);
                    textView3.setText(timethree);
                    textView8.setVisibility(View.VISIBLE);
                    textView8.setText(eitherthree);
                    aSwitch3.setVisibility(View.VISIBLE);
                    if (eitherthree.equals("开启")) {
                        aSwitch3.setChecked(true);
                    }
                    else {
                        aSwitch3.setChecked(false);
                    }
                }
            }while (cursorthree.moveToNext());
        }
        cursorthree.close();
        Cursor cursorfour = database.rawQuery("select time,either from alarmset where stat=1 LIMIT 3,1", null);
        if (cursorfour.moveToNext()) {
            do {
                String timefour = cursorfour.getString(cursorfour.getColumnIndex("time"));
                String eitherfour = cursorfour.getString(cursorfour.getColumnIndex("either"));
                if (timefour != null) {
                    textView4.setVisibility(View.VISIBLE);
                    textView4.setText(timefour);
                    textView9.setVisibility(View.VISIBLE);
                    textView9.setText(eitherfour);
                    aSwitch4.setVisibility(View.VISIBLE);
                    if (eitherfour.equals("开启")) {
                        aSwitch4.setChecked(true);
                    }
                    else {
                        aSwitch4.setChecked(false);
                    }
                }
            }while (cursorfour.moveToNext());
        }
        cursorfour.close();
        Cursor cursorfive = database.rawQuery("select time,either from alarmset where stat=1 LIMIT 4,1", null);
        if (cursorfive.moveToNext()) {
            do {
                String timefive = cursorfive.getString(cursorfive.getColumnIndex("time"));
                String eitherfive = cursorfive.getString(cursorfive.getColumnIndex("either"));
                if (timefive != null) {
                    textView5.setVisibility(View.VISIBLE);
                    textView5.setText(timefive);
                    textView10.setVisibility(View.VISIBLE);
                    textView10.setText(eitherfive);
                    aSwitch5.setVisibility(View.VISIBLE);
                    if (eitherfive.equals("开启")) {
                        aSwitch5.setChecked(true);
                    }
                    else {
                        aSwitch5.setChecked(false);
                    }
                }
            }while (cursorfive.moveToNext());
        }
        cursorfive.close();

        //闹钟列表通过开关选择开启状态，并且更新数据库是否开启状态
        aSwitch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aSwitch1.isChecked()) {
                    textView6.setText("开启");
                    ContentValues values = new ContentValues();
                    values.put("either","开启");
                    database.update("alarmset",values,"id=1",new String[] {});
                    String time = textView1.getText().toString();
                    openAlarm(time, 1);
                }
                else {
                    textView6.setText("未开启");
                    ContentValues values = new ContentValues();
                    values.put("either","未开启");
                    database.update("alarmset",values,"id=1",new String[] {});
                    deleteAlarm(1);
                }
            }
        });
        aSwitch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aSwitch2.isChecked()) {
                    textView7.setText("开启");
                    ContentValues values = new ContentValues();
                    values.put("either","开启");
                    database.update("alarmset",values,"id=2",new String[] {});
                    String time = textView2.getText().toString();
                    openAlarm(time, 2);
                }
                else {
                    textView7.setText("未开启");
                    ContentValues values = new ContentValues();
                    values.put("either","未开启");
                    database.update("alarmset",values,"id=2",new String[] {});
                    deleteAlarm(2);
                }
            }
        });
        aSwitch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aSwitch3.isChecked()) {
                    textView8.setText("开启");
                    ContentValues values = new ContentValues();
                    values.put("either","开启");
                    database.update("alarmset",values,"id=3",new String[] {});
                    String time = textView3.getText().toString();
                    openAlarm(time, 3);
                }
                else {
                    textView8.setText("未开启");
                    ContentValues values = new ContentValues();
                    values.put("either","未开启");
                    database.update("alarmset",values,"id=3",new String[] {});
                    deleteAlarm(3);
                }
            }
        });
        aSwitch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aSwitch4.isChecked()) {
                    textView9.setText("开启");
                    ContentValues values = new ContentValues();
                    values.put("either","开启");
                    database.update("alarmset",values,"id=4",new String[] {});
                    String time = textView4.getText().toString();
                    openAlarm(time, 4);
                }
                else {
                    textView9.setText("未开启");
                    ContentValues values = new ContentValues();
                    values.put("either","未开启");
                    database.update("alarmset",values,"id=4",new String[] {});
                    deleteAlarm(4);
                }
            }
        });
        aSwitch5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aSwitch5.isChecked()) {
                    textView10.setText("开启");
                    ContentValues values = new ContentValues();
                    values.put("either","开启");
                    database.update("alarmset",values,"id=5",new String[] {});
                    String time = textView5.getText().toString();
                    openAlarm(time, 5);
                }
                else {
                    textView10.setText("未开启");
                    ContentValues values = new ContentValues();
                    values.put("either","未开启");
                    database.update("alarmset",values,"id=5",new String[] {});
                    deleteAlarm(5);
                }
            }
        });

        //点击添加闹钟按钮后跳转到闹钟设置页面
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AlarmSet.class);
                startActivity(intent);
            }
        });

        //点击显示的闹钟列表，跳转到对应闹钟的闹钟设置页面
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView1 = findViewById(R.id.textView1);
                String time = textView1.getText().toString();
                turnAlarmSet(time, "1");
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2 = findViewById(R.id.textView2);
                String time = textView2.getText().toString();
                turnAlarmSet(time, "2");
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView3 = findViewById(R.id.textView3);
                String time = textView3.getText().toString();
                turnAlarmSet(time, "3");
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView4 = findViewById(R.id.textView4);
                String time = textView4.getText().toString();
                turnAlarmSet(time, "4");
            }
        });
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView5 = findViewById(R.id.textView5);
                String time = textView5.getText().toString();
                turnAlarmSet(time, "5");
            }
        });

        //长按闹钟列表选择是否删除闹钟
        textView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDeleteDiaglog(1);
                return true;
            }
        });
        textView2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDeleteDiaglog(2);
                return true;
            }
        });
        textView3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDeleteDiaglog(3);
                return true;
            }
        });
        textView4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDeleteDiaglog(4);
                return true;
            }
        });
        textView5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDeleteDiaglog(5);
                return true;
            }
        });

        //切换到世界时钟页面
        textViewTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Time.class);
                startActivity(intent);
            }
        });
        //切换到秒表页面
        textViewStopWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StopWatch.class);
                startActivity(intent);
            }
        });
        //切换到计时器页面
        textViewTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CountDownTime.class);
                startActivity(intent);
            }
        });

    }

    //删除闹钟对话框
    private void showDeleteDiaglog(int i) {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(MainActivity.this);
        final int updateid = i;
        normalDialog.setIcon(R.drawable.delete);
        normalDialog.setTitle("删除闹钟");
        normalDialog.setMessage("您是否确定要删除这个闹钟");
        normalDialog.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                timerDatabase = new TimerDatabase(MainActivity.this, "Timer.db", null, 1);
                SQLiteDatabase database = timerDatabase.getWritableDatabase();
                database.execSQL("update alarmset set stat=0 where id=" + updateid);
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                deleteAlarm(updateid);
                startActivity(intent);
            }
        });
        normalDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        normalDialog.show();
    }

    //点击闹钟列表跳转到修改闹钟页面
    private void turnAlarmSet(String time, String idturn) {
        Intent intent = new Intent(MainActivity.this, AlarmSet.class);
        String hour = time.substring(2,4);
        String minute = time.substring(5,7);
        intent.putExtra("ID",idturn);
        intent.putExtra("Hour",hour);
        intent.putExtra("Minute",minute);
        startActivity(intent);
    }

    //取消闹钟设定
    private void deleteAlarm(int id) {
        alarmManager = (AlarmManager)getSystemService(Service.ALARM_SERVICE);
        Intent intentAlarm = new Intent(MainActivity.this, AlarmActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, id, intentAlarm, 0);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(MainActivity.this, "闹钟已关闭", Toast.LENGTH_SHORT).show();
    }

    //开启闹钟设定
    public void openAlarm(String time, int id) {
        alarmManager = (AlarmManager)getSystemService(Service.ALARM_SERVICE);
        Intent intentAlarm = new Intent(MainActivity.this, AlarmActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, id, intentAlarm, 0);
        String hour = time.substring(2,4);
        String minute = time.substring(5,7);
        int hourint = Integer.parseInt(hour);
        int minuteint = Integer.parseInt(minute);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR, hourint);
        calendar.set(Calendar.MINUTE,minuteint);
        calendar.set(Calendar.SECOND, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Toast.makeText(MainActivity.this, "闹钟已开启", Toast.LENGTH_SHORT).show();
    }
}
