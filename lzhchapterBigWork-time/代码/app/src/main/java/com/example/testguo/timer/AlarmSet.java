package com.example.testguo.timer;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;



//闹钟设置页面，对应alarmset的xml布局文件
public class AlarmSet extends Activity {

    Button button1; //取消按钮
    Button button2; //确认按钮
    TextView textView2; //重复次数显示
    TextView textView4; //铃声显示
    ImageView imageButton1;   //选择重复次数按钮
    ImageView imageButton2;   //选择铃声按钮
    TimePicker timePicker;  //下方时间选择器
    String[] alarmrepeat = {"只响一次","每天","法定工作日","法定节假日","周一至周五","自定义"};
    String[] alarmvoice = {"1","2","3","4","5"};
    int hour;   //选择闹钟的小时数值；
    int minute; //选择闹钟的分钟数值；
    String hourfix; //根据展示的效果填充空格后传输的选择闹钟的小时数值
    String minutefix;   //根据展示的效果填充空格后传输的选择闹钟的分钟数值
    String hourget; //闹钟主页面获取的闹钟小时数值
    String minuteget;   //闹钟主页面获取的闹钟分钟数值
    String idget;   //闹钟主页面获取的闹钟在数据库中的ID值
    String time;  //从闹钟选择器中获取的时间
    int hourset;    //将从主页面获取的闹钟小时数值修改为int类型
    int minuteset;  //将从主页面获取的闹钟分钟数值修改为int类型
    int idset;  //将从主页面获取的闹钟在数据库中的ID值修改为int类型
    int id; //数据库获取没有被删除的最小ID值
    AlarmManager alarmManager;  //新建闹钟管理类
    private TimerDatabase timerDatabase;    //Timer数据库

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmset);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        textView2 = findViewById(R.id.textView2);
        textView4 = findViewById(R.id.textView4);
        imageButton1 = findViewById(R.id.imageButton1);
        imageButton2 = findViewById(R.id.imageButton2);
        alarmManager = (AlarmManager)getSystemService(Service.ALARM_SERVICE);
        timePicker = findViewById(R.id.timePicker);
        //new一个数据库类
        timerDatabase = new TimerDatabase(this, "Timer.db", null, 1);

        //主页面点击闹钟列表后跳转到闹钟设置页面，获取其对应值，并判断是否为空，不为空设置下方时钟数值
        final Intent intent = getIntent();
        hourget = intent.getStringExtra("Hour");
        minuteget = intent.getStringExtra("Minute");
        idget = intent.getStringExtra("ID");
        if (hourget != null) {
            hourset = Integer.parseInt(hourget);
            minuteset = Integer.parseInt(minuteget);
            idset = Integer.parseInt(idget);
            timePicker.setHour(hourset);
            timePicker.setMinute(minuteset);
        }

        //时钟选择监听器
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, final int minuteOfDay) {

                //获取时钟小时和分钟数值，根据需要显示效果填充空格修改格式
                hour = hourOfDay;
                minute = minuteOfDay;
                if (hour < 10) {
                    hourfix = " 0" + hour;
                }
                else {
                    hourfix = " " + hour;
                }
                if (minute < 10) {
                    minutefix = "0" + minute;
                }
                else {
                    minutefix = "" + minute;
                }
                time = " " + hourfix + ":" + minutefix;

                //确认按钮传值返回主页面，如果获取ID值则更新数据库，没有获取则插入数据库
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (idget != null) {
                            updatedata(idset);
                            addalarm(idset);
                            Intent intent = new Intent(AlarmSet.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            SQLiteDatabase database = timerDatabase.getWritableDatabase();
                            Cursor cursor = database.rawQuery("select id from alarmset where stat=0 LIMIT 1", null);
                            if (cursor.moveToNext()) {
                                do {
                                    id = cursor.getInt(cursor.getColumnIndex("id"));
                                }while (cursor.moveToNext());
                            }
                            cursor.close();
                            if (id == 1 || id == 2 || id == 3 || id == 4 || id == 5) {
                                updatedata(id);
                                addalarm(id);
                            }
                            else {
                                adddata();
                                addalarm(id);
                            }
                            Intent intent = new Intent(AlarmSet.this,MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        //取消按钮直接返回主页面
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlarmSet.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //调用选择重复次数对话框
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmrepeat_onclick();
            }
        });

        //调用选择铃声对话框
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmvoice_onclick();
            }
        });
    }

    //对话框选择闹钟重复次数的函数
    private void alarmrepeat_onclick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择重复次数：");
        builder.setIcon(R.mipmap.number);
        builder.setSingleChoiceItems(alarmrepeat, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String str = alarmrepeat[i];
                textView2.setText(str);
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //对话框选择闹钟铃声的函数
    private void alarmvoice_onclick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择铃声：");
        builder.setIcon(R.mipmap.music);
        builder.setSingleChoiceItems(alarmvoice, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = alarmvoice[which];
                textView4.setText(str);
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //向数据库添加闹钟信息的函数
    private void adddata() {
        timePicker = findViewById(R.id.timePicker);
        timerDatabase = new TimerDatabase(this, "Timer.db", null, 1);
        SQLiteDatabase database = timerDatabase.getWritableDatabase();
        database.execSQL("insert into alarmset(time,either,stat) values (\"" + time + "\",\"开启\",1)");
    }

    //在数据库中更新闹钟信息的函数
    private void updatedata(int id) {
        timePicker = findViewById(R.id.timePicker);
        timerDatabase = new TimerDatabase(this, "Timer.db", null, 1);
        SQLiteDatabase database = timerDatabase.getWritableDatabase();
        database.execSQL("update alarmset set time=\"" + time + "\",either=\"开启\",stat=1 where id=" + id);
    }

    //设置后台开启闹钟的函数
    private void addalarm(int id) {
        Calendar calendar = Calendar.getInstance();
        Intent intentAlarm = new Intent(AlarmSet.this, AlarmActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(AlarmSet.this, id, intentAlarm, 0);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Toast.makeText(AlarmSet.this, "闹钟已开启", Toast.LENGTH_SHORT).show();
    }
}
