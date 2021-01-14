package com.example.testguo.timer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class Time extends Activity {

    TextView textViewAlarm; //闹钟选项
    TextView textViewTime;  //世界时钟选项
    TextView textViewStopWatch; //秒表选项
    TextView textViewTimer; //计时器选项
    ImageView imageButton;    //添加世界时钟选项
    //各地时间显示
    TextView textView1;
    TextView textView1_1;
    ImageView imageView1;
    TextView textView2;
    TextView textView2_1;
    ImageView imageView2;
    TextView textView3;
    TextView textView3_1;
    ImageView imageView3;
    TextView textView4;
    TextView textView4_1;
    ImageView imageView4;
    TextView textView5;
    TextView textView5_1;
    ImageView imageView5;
    TextView textView6;
    TextView textView6_1;
    ImageView imageView6;
    TextView textView7;
    TextView textView7_1;
    ImageView imageView7;
    TextView textView8;
    TextView textView8_1;
    ImageView imageView8;
    TextView textView9;
    TextView textView9_1;
    ImageView imageView9;
    TextView textView10;
    TextView textView10_1;
    ImageView imageView10;
    TextView textView;  //配合实时时间更新TextView
    TimerDatabase timerDatabase;    //Timer数据库
    String[] areachoice = {"温哥华","华盛顿","布宜诺斯艾利斯","伦敦","开罗","莫斯科","孟买","北京","东京","墨尔本"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time);
        textViewAlarm = findViewById(R.id.textViewAlarm);
        textViewTime = findViewById(R.id.textViewTime);
        textViewStopWatch = findViewById(R.id.textViewStopWatch);
        textViewTimer = findViewById(R.id.textViewTimer);
        imageButton = findViewById(R.id.imageButton);
        textView1 = findViewById(R.id.textView1);
        textView1_1 = findViewById(R.id.textView1_1);
        imageView1 = findViewById(R.id.Vancouver);
        textView2 = findViewById(R.id.textView2);
        textView2_1 = findViewById(R.id.textView2_1);
        imageView2 = findViewById(R.id.Washington);
        textView3 = findViewById(R.id.textView3);
        textView3_1 = findViewById(R.id.textView3_1);
        imageView3 = findViewById(R.id.BuenosAires);
        textView4 = findViewById(R.id.textView4);
        textView4_1 = findViewById(R.id.textView4_1);
        imageView4 = findViewById(R.id.London);
        textView5 = findViewById(R.id.textView5);
        textView5_1 = findViewById(R.id.textView5_1);
        imageView5 = findViewById(R.id.Cario);
        textView6 = findViewById(R.id.textView6);
        textView6_1 = findViewById(R.id.textView6_1);
        imageView6 = findViewById(R.id.Moscow);
        textView7 = findViewById(R.id.textView7);
        textView7_1 = findViewById(R.id.textView7_1);
        imageView7 = findViewById(R.id.Mumbai);
        textView8 = findViewById(R.id.textView8);
        textView8_1 = findViewById(R.id.textView8_1);
        imageView8 = findViewById(R.id.Beijing);
        textView9 = findViewById(R.id.textView9);
        textView9_1 = findViewById(R.id.textView9_1);
        imageView9 = findViewById(R.id.Tokyo);
        textView10 = findViewById(R.id.textView10);
        textView10_1 = findViewById(R.id.textView10_1);
        imageView10 = findViewById(R.id.Melbourne);
        timerDatabase = new TimerDatabase(this, "Timer.db", null, 1);
        final SQLiteDatabase database = timerDatabase.getWritableDatabase();

        //获取世界时钟列表
        Cursor cursorone = database.rawQuery("select id from timeset where stat=1 LIMIT 1", null);
        if (cursorone.moveToNext()) {
            do {
                int id = cursorone.getInt(cursorone.getColumnIndex("id"));
                if (id != 0) {
                    selectAreaOne(id);
                }
            }while (cursorone.moveToNext());
        }
        cursorone.close();
        Cursor cursortwo = database.rawQuery("select id from timeset where stat=1 LIMIT 1,1", null);
        if (cursortwo.moveToNext()) {
            do {
                int id = cursortwo.getInt(cursortwo.getColumnIndex("id"));
                if (id != 0) {
                    selectAreaTwo(id);
                }
            }while (cursortwo.moveToNext());
        }
        cursortwo.close();
        Cursor cursorthree = database.rawQuery("select id from timeset where stat=1 LIMIT 2,1", null);
        if (cursorthree.moveToNext()) {
            do {
                int id = cursorthree.getInt(cursorthree.getColumnIndex("id"));
                if (id != 0) {
                    selectAreaThree(id);
                }
            }while (cursorthree.moveToNext());
        }
        cursorthree.close();
        Cursor cursorfour = database.rawQuery("select id from timeset where stat=1 LIMIT 3,1", null);
        if (cursorfour.moveToNext()) {
            do {
                int id = cursorfour.getInt(cursorfour.getColumnIndex("id"));
                if (id != 0) {
                    selectAreaFour(id);
                }
            }while (cursorfour.moveToNext());
        }
        cursorfour.close();

        //切换到闹钟界面
        textViewAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Time.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //切换到秒表页面
        textViewStopWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Time.this, StopWatch.class);
                startActivity(intent);
            }
        });
        //切换到计时器页面
        textViewTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Time.this, CountDownTime.class);
                startActivity(intent);
            }
        });

        //点击弹出对话框添加时钟
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                areaChoice();
            }
        });

        //长按删除添加的世界时钟
        textView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String str = textView1.getText().toString();
                deleteArea(str);
                return true;
            }
        });
        textView2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String str = textView2.getText().toString();
                deleteArea(str);
                return true;
            }
        });
        textView3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String str = textView3.getText().toString();
                deleteArea(str);
                return true;
            }
        });
        textView4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String str = textView4.getText().toString();
                deleteArea(str);
                return true;
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pointClick("    温哥华");
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pointClick("    华盛顿");
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pointClick("    布宜诺斯艾利斯");
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pointClick("    伦敦");
            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pointClick("    开罗");
            }
        });

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pointClick("    莫斯科");
            }
        });

        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pointClick("    孟买");
            }
        });

        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pointClick("    北京");
            }
        });

        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pointClick("    东京");
            }
        });

        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pointClick("    墨尔本");
            }
        });
    }

    //列表1根据数据库获取的名称选择对应的国家地区
    private void selectAreaOne(int id) {
        switch (id) {
            case 1:
                textView1.setText("    温哥华");
                textView1.setVisibility(View.VISIBLE);
                imageView1.setVisibility(View.VISIBLE);
                TimeThread timeThread11 = new TimeThread();
                timeThread11.setId(1);
                timeThread11.setText(1);
                timeThread11.start();
                break;
            case 2:
                textView1.setText("    华盛顿");
                textView1.setVisibility(View.VISIBLE);
                imageView2.setVisibility(View.VISIBLE);
                TimeThread timeThread12 = new TimeThread();
                timeThread12.setId(2);
                timeThread12.setText(1);
                timeThread12.start();
                break;
            case 3:
                textView1.setText("    布宜诺斯艾利斯");
                textView1.setVisibility(View.VISIBLE);
                imageView3.setVisibility(View.VISIBLE);
                TimeThread timeThread13 = new TimeThread();
                timeThread13.setId(3);
                timeThread13.setText(1);
                timeThread13.start();
                break;
            case 4:
                textView1.setText("    伦敦");
                textView1.setVisibility(View.VISIBLE);
                imageView4.setVisibility(View.VISIBLE);
                TimeThread timeThread14 = new TimeThread();
                timeThread14.setId(4);
                timeThread14.setText(1);
                timeThread14.start();
                break;
            case 5:
                textView1.setText("    开罗");
                textView1.setVisibility(View.VISIBLE);
                imageView5.setVisibility(View.VISIBLE);
                TimeThread timeThread15 = new TimeThread();
                timeThread15.setId(5);
                timeThread15.setText(1);
                timeThread15.start();
                break;
            case 6:
                textView1.setText("    莫斯科");
                textView1.setVisibility(View.VISIBLE);
                imageView6.setVisibility(View.VISIBLE);
                TimeThread timeThread16 = new TimeThread();
                timeThread16.setId(6);
                timeThread16.setText(1);
                timeThread16.start();
                break;
            case 7:
                textView1.setText("    孟买");
                textView1.setVisibility(View.VISIBLE);
                imageView7.setVisibility(View.VISIBLE);
                TimeThread timeThread17 = new TimeThread();
                timeThread17.setId(7);
                timeThread17.setText(1);
                timeThread17.start();
                break;
            case 8:
                textView1.setText("    北京");
                textView1.setVisibility(View.VISIBLE);
                imageView8.setVisibility(View.VISIBLE);
                TimeThread timeThread18 = new TimeThread();
                timeThread18.setId(8);
                timeThread18.setText(1);
                timeThread18.start();
                break;
            case 9:
                textView1.setText("    东京");
                textView1.setVisibility(View.VISIBLE);
                imageView9.setVisibility(View.VISIBLE);
                TimeThread timeThread19 = new TimeThread();
                timeThread19.setId(9);
                timeThread19.setText(1);
                timeThread19.start();
                break;
            case 10:
                textView1.setText("    墨尔本");
                textView1.setVisibility(View.VISIBLE);
                imageView10.setVisibility(View.VISIBLE);
                TimeThread timeThread110 = new TimeThread();
                timeThread110.setId(10);
                timeThread110.setText(1);
                timeThread110.start();
                break;
        }
    }

    //列表2根据数据库获取的名称选择对应的国家地区
    private void selectAreaTwo(int id) {
        switch (id) {
            case 1:
                textView2.setText("    温哥华");
                textView2.setVisibility(View.VISIBLE);
                imageView1.setVisibility(View.VISIBLE);
                TimeThread timeThread21 = new TimeThread();
                timeThread21.setId(1);
                timeThread21.setText(2);
                timeThread21.start();
                break;
            case 2:
                textView2.setText("    华盛顿");
                textView2.setVisibility(View.VISIBLE);
                imageView2.setVisibility(View.VISIBLE);
                TimeThread timeThread22 = new TimeThread();
                timeThread22.setId(2);
                timeThread22.setText(2);
                timeThread22.start();
                break;
            case 3:
                textView2.setText("    布宜诺斯艾利斯");
                textView2.setVisibility(View.VISIBLE);
                imageView3.setVisibility(View.VISIBLE);
                TimeThread timeThread23 = new TimeThread();
                timeThread23.setId(3);
                timeThread23.setText(2);
                timeThread23.start();
                break;
            case 4:
                textView2.setText("    伦敦");
                textView2.setVisibility(View.VISIBLE);
                imageView4.setVisibility(View.VISIBLE);
                TimeThread timeThread24 = new TimeThread();
                timeThread24.setId(4);
                timeThread24.setText(2);
                timeThread24.start();
                break;
            case 5:
                textView2.setText("    开罗");
                textView2.setVisibility(View.VISIBLE);
                imageView5.setVisibility(View.VISIBLE);
                TimeThread timeThread25 = new TimeThread();
                timeThread25.setId(5);
                timeThread25.setText(2);
                timeThread25.start();
                break;
            case 6:
                textView2.setText("    莫斯科");
                textView2.setVisibility(View.VISIBLE);
                imageView6.setVisibility(View.VISIBLE);
                TimeThread timeThread26 = new TimeThread();
                timeThread26.setId(6);
                timeThread26.setText(2);
                timeThread26.start();
                break;
            case 7:
                textView2.setText("    孟买");
                textView2.setVisibility(View.VISIBLE);
                imageView7.setVisibility(View.VISIBLE);
                TimeThread timeThread27 = new TimeThread();
                timeThread27.setId(7);
                timeThread27.setText(2);
                timeThread27.start();
                break;
            case 8:
                textView2.setText("    北京");
                textView2.setVisibility(View.VISIBLE);
                imageView8.setVisibility(View.VISIBLE);
                TimeThread timeThread28 = new TimeThread();
                timeThread28.setId(8);
                timeThread28.setText(2);
                timeThread28.start();
                break;
            case 9:
                textView2.setText("    东京");
                textView2.setVisibility(View.VISIBLE);
                imageView9.setVisibility(View.VISIBLE);
                TimeThread timeThread29 = new TimeThread();
                timeThread29.setId(9);
                timeThread29.setText(2);
                timeThread29.start();
                break;
            case 10:
                textView2.setText("    墨尔本");
                textView2.setVisibility(View.VISIBLE);
                imageView10.setVisibility(View.VISIBLE);
                TimeThread timeThread210 = new TimeThread();
                timeThread210.setId(10);
                timeThread210.setText(2);
                timeThread210.start();
                break;
        }
    }

    //列表3根据数据库获取的名称选择对应的国家地区
    private void selectAreaThree(int id) {
        switch (id) {
            case 1:
                textView3.setText("    温哥华");
                textView3.setVisibility(View.VISIBLE);
                imageView1.setVisibility(View.VISIBLE);
                TimeThread timeThread31 = new TimeThread();
                timeThread31.setId(1);
                timeThread31.setText(3);
                timeThread31.start();
                break;
            case 2:
                textView3.setText("    华盛顿");
                textView3.setVisibility(View.VISIBLE);
                imageView2.setVisibility(View.VISIBLE);
                TimeThread timeThread32 = new TimeThread();
                timeThread32.setId(2);
                timeThread32.setText(3);
                timeThread32.start();
                break;
            case 3:
                textView3.setText("    布宜诺斯艾利斯");
                textView3.setVisibility(View.VISIBLE);
                imageView3.setVisibility(View.VISIBLE);
                TimeThread timeThread33 = new TimeThread();
                timeThread33.setId(3);
                timeThread33.setText(3);
                timeThread33.start();
                break;
            case 4:
                textView3.setText("    伦敦");
                textView3.setVisibility(View.VISIBLE);
                imageView4.setVisibility(View.VISIBLE);
                TimeThread timeThread34 = new TimeThread();
                timeThread34.setId(4);
                timeThread34.setText(3);
                timeThread34.start();
                break;
            case 5:
                textView3.setText("    开罗");
                textView3.setVisibility(View.VISIBLE);
                imageView5.setVisibility(View.VISIBLE);
                TimeThread timeThread35 = new TimeThread();
                timeThread35.setId(5);
                timeThread35.setText(3);
                timeThread35.start();
                break;
            case 6:
                textView3.setText("    莫斯科");
                textView3.setVisibility(View.VISIBLE);
                imageView6.setVisibility(View.VISIBLE);
                TimeThread timeThread36 = new TimeThread();
                timeThread36.setId(6);
                timeThread36.setText(3);
                timeThread36.start();
                break;
            case 7:
                textView3.setText("    孟买");
                textView3.setVisibility(View.VISIBLE);
                imageView7.setVisibility(View.VISIBLE);
                TimeThread timeThread37 = new TimeThread();
                timeThread37.setId(7);
                timeThread37.setText(3);
                timeThread37.start();
                break;
            case 8:
                textView3.setText("    北京");
                textView3.setVisibility(View.VISIBLE);
                imageView8.setVisibility(View.VISIBLE);
                TimeThread timeThread38 = new TimeThread();
                timeThread38.setId(8);
                timeThread38.setText(3);
                timeThread38.start();
                break;
            case 9:
                textView3.setText("    东京");
                textView3.setVisibility(View.VISIBLE);
                imageView9.setVisibility(View.VISIBLE);
                TimeThread timeThread39 = new TimeThread();
                timeThread39.setId(9);
                timeThread39.setText(3);
                timeThread39.start();
                break;
            case 10:
                textView3.setText("    墨尔本");
                textView3.setVisibility(View.VISIBLE);
                imageView10.setVisibility(View.VISIBLE);
                TimeThread timeThread310 = new TimeThread();
                timeThread310.setId(10);
                timeThread310.setText(3);
                timeThread310.start();
                break;
        }
    }

    //列表4根据数据库获取的名称选择对应的国家地区
    private void selectAreaFour(int id) {
        switch (id) {
            case 1:
                textView4.setText("    温哥华");
                textView4.setVisibility(View.VISIBLE);
                imageView1.setVisibility(View.VISIBLE);
                TimeThread timeThread41 = new TimeThread();
                timeThread41.setId(1);
                timeThread41.setText(4);
                timeThread41.start();
                break;
            case 2:
                textView4.setText("    华盛顿");
                textView4.setVisibility(View.VISIBLE);
                imageView2.setVisibility(View.VISIBLE);
                TimeThread timeThread42 = new TimeThread();
                timeThread42.setId(2);
                timeThread42.setText(4);
                timeThread42.start();
                break;
            case 3:
                textView4.setText("    布宜诺斯艾利斯");
                textView4.setVisibility(View.VISIBLE);
                imageView3.setVisibility(View.VISIBLE);
                TimeThread timeThread43 = new TimeThread();
                timeThread43.setId(3);
                timeThread43.setText(4);
                timeThread43.start();
                break;
            case 4:
                textView4.setText("    伦敦");
                textView4.setVisibility(View.VISIBLE);
                imageView4.setVisibility(View.VISIBLE);
                TimeThread timeThread44 = new TimeThread();
                timeThread44.setId(4);
                timeThread44.setText(4);
                timeThread44.start();
                break;
            case 5:
                textView4.setText("    开罗");
                textView4.setVisibility(View.VISIBLE);
                imageView5.setVisibility(View.VISIBLE);
                TimeThread timeThread45 = new TimeThread();
                timeThread45.setId(5);
                timeThread45.setText(4);
                timeThread45.start();
                break;
            case 6:
                textView4.setText("    莫斯科");
                textView4.setVisibility(View.VISIBLE);
                imageView6.setVisibility(View.VISIBLE);
                TimeThread timeThread46 = new TimeThread();
                timeThread46.setId(6);
                timeThread46.setText(4);
                timeThread46.start();
                break;
            case 7:
                textView4.setText("    孟买");
                textView4.setVisibility(View.VISIBLE);
                imageView7.setVisibility(View.VISIBLE);
                TimeThread timeThread47 = new TimeThread();
                timeThread47.setId(7);
                timeThread47.setText(4);
                timeThread47.start();
                break;
            case 8:
                textView4.setText("    北京");
                textView4.setVisibility(View.VISIBLE);
                imageView8.setVisibility(View.VISIBLE);
                TimeThread timeThread48 = new TimeThread();
                timeThread48.setId(8);
                timeThread48.setText(4);
                timeThread48.start();
                break;
            case 9:
                textView4.setText("    东京");
                textView4.setVisibility(View.VISIBLE);
                imageView9.setVisibility(View.VISIBLE);
                TimeThread timeThread49 = new TimeThread();
                timeThread49.setId(9);
                timeThread49.setText(4);
                timeThread49.start();
                break;
            case 10:
                textView4.setText("    墨尔本");
                textView4.setVisibility(View.VISIBLE);
                imageView10.setVisibility(View.VISIBLE);
                TimeThread timeThread410 = new TimeThread();
                timeThread410.setId(10);
                timeThread410.setText(4);
                timeThread410.start();
                break;
        }
    }

    //添加国家地区时钟
    private void areaChoice() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择国家或者地区：");
        builder.setIcon(R.mipmap.earth);
        builder.setSingleChoiceItems(areachoice, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = areachoice[which];
                str = "    " + str;
                if (str.equals("    温哥华")) {
                    updateArea(1);
                }
                else if (str.equals("    华盛顿")) {
                    updateArea(2);
                }
                else if (str.equals("    布宜诺斯艾利斯")) {
                    updateArea(3);
                }
                else if (str.equals("    伦敦")) {
                    updateArea(4);
                }
                else if (str.equals("    开罗")) {
                    updateArea(5);
                }
                else if (str.equals("    莫斯科")) {
                    updateArea(6);
                }
                else if (str.equals("    孟买")) {
                    updateArea(7);
                }
                else if (str.equals("    北京")) {
                    updateArea(8);
                }
                else if (str.equals("    东京")) {
                    updateArea(9);
                }
                else {
                    updateArea(10);
                }
                dialog.dismiss();
                Intent intent = new Intent(Time.this, Time.class);
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //更新世界时钟地区列表
    private void updateArea(int id) {
        timerDatabase = new TimerDatabase(this, "Timer.db", null, 1);
        SQLiteDatabase database = timerDatabase.getWritableDatabase();
        database.execSQL("update timeset set stat=1 where id=" + id);
    }

    //删除添加的世界时钟
    private void deleteArea(String str) {
        final String strname = str;
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(Time.this);
        normalDialog.setIcon(R.drawable.delete);
        normalDialog.setTitle("删除世界时钟");
        normalDialog.setMessage("您是否确定要删除这个时钟");
        normalDialog.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int id;
                if (strname.equals("    温哥华")) {
                    id = 1;
                }
                else if (strname.equals("    华盛顿")) {
                    id = 2;
                }
                else if (strname.equals("    布宜诺斯艾利斯")) {
                    id = 3;
                }
                else if (strname.equals("    伦敦")) {
                    id = 4;
                }
                else if (strname.equals("    开罗")) {
                    id = 5;
                }
                else if (strname.equals("    莫斯科")) {
                    id = 6;
                }
                else if (strname.equals("    孟买")) {
                    id = 7;
                }
                else if (strname.equals("    北京")) {
                    id = 8;
                }
                else if (strname.equals("    东京")) {
                    id = 9;
                }
                else {
                    id = 10;
                }
                timerDatabase = new TimerDatabase(Time.this, "Timer.db", null, 1);
                SQLiteDatabase database = timerDatabase.getWritableDatabase();
                database.execSQL("update timeset set stat=0 where id=" + id);
                Intent intent = new Intent(Time.this, Time.class);
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

    //点击红点增强对应列表展示效果
    private void pointClick(String name) {
        String text1 = textView1.getText().toString();
        String text2 = textView2.getText().toString();
        String text3 = textView3.getText().toString();
        if (text1.equals(name)) {
            textView1.setBackgroundColor(Color.parseColor("#C0C0C0"));
            textView2.setBackgroundColor(Color.parseColor("#FFFFFF"));
            textView3.setBackgroundColor(Color.parseColor("#FFFFFF"));
            textView4.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else if (text2.equals(name)) {
            textView1.setBackgroundColor(Color.parseColor("#FFFFFF"));
            textView2.setBackgroundColor(Color.parseColor("#C0C0C0"));
            textView3.setBackgroundColor(Color.parseColor("#FFFFFF"));
            textView4.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else if (text3.equals(name)) {
            textView1.setBackgroundColor(Color.parseColor("#FFFFFF"));
            textView2.setBackgroundColor(Color.parseColor("#FFFFFF"));
            textView3.setBackgroundColor(Color.parseColor("#C0C0C0"));
            textView4.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else {
            textView1.setBackgroundColor(Color.parseColor("#FFFFFF"));
            textView2.setBackgroundColor(Color.parseColor("#FFFFFF"));
            textView3.setBackgroundColor(Color.parseColor("#FFFFFF"));
            textView4.setBackgroundColor(Color.parseColor("#C0C0C0"));
        }
    }

    //列表通过线程1完成定时刷新
    public class TimeThread extends Thread {

        int id;
        int text;

        //Thread传参函数
        public void setId(int id) {
            this.id = id;
        }
        public void setText(int text) {
            this.text = text;
        }

        @Override
        public void run() {
            super.run();
            do {
                try {
                    //每隔1000毫秒自动刷新时间
                    Thread.sleep(1000);
                    getSystemTime(id, text);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    //展示不同时区系统时间
    public void getSystemTime(int id, int text) {
        try {
            DateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            Date date = new Date(System.currentTimeMillis());
            switch (id) {
                case 1:
                    format.setTimeZone(TimeZone.getTimeZone("GMT-8"));
                    break;
                case 2:
                    format.setTimeZone(TimeZone.getTimeZone("GMT-5"));
                    break;
                case 3:
                    format.setTimeZone(TimeZone.getTimeZone("GMT-3"));
                    break;
                case 4:
                    format.setTimeZone(TimeZone.getTimeZone("GMT 0"));
                    break;
                case 5:
                    format.setTimeZone(TimeZone.getTimeZone("GMT+2"));
                    break;
                case 6:
                    format.setTimeZone(TimeZone.getTimeZone("GMT+3"));
                    break;
                case 7:
                    format.setTimeZone(TimeZone.getTimeZone("GMT+5"));
                    break;
                case 8:
                    format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                    break;
                case 9:
                    format.setTimeZone(TimeZone.getTimeZone("GMT+9"));
                    break;
                case 10:
                    format.setTimeZone(TimeZone.getTimeZone("GMT+10"));
                    break;
            }
            switch (text) {
                case 1:
                    textView = findViewById(R.id.textView1_1);
                    break;
                case 2:
                    textView = findViewById(R.id.textView2_1);
                    break;
                case 3:
                    textView = findViewById(R.id.textView3_1);
                    break;
                case 4:
                    textView = findViewById(R.id.textView4_1);
                    break;
            }
            final String time = format.format(date);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(time);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
