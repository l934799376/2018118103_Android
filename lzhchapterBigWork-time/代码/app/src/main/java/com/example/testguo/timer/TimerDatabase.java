package com.example.testguo.timer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;



//Timer数据库
public class TimerDatabase extends SQLiteOpenHelper {

    //闹钟数据库
    private static final String CREATE_ALARM = "create table alarmset ("
             + "id integer primary key autoincrement,"
             + "time char(20),"
             + "either char(20),"
             + "stat int)";

    //世界时钟数据库
    private static final String CREATE_TIME = "create table timeset ("
             + "id integer primary key autoincrement,"
             + "stat int)";

    private Context mContext;

    public TimerDatabase(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version) {
        super(context, name, cursorFactory, version);
        mContext = context;
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_ALARM);
        database.execSQL(CREATE_TIME);;
        Toast.makeText(mContext, "数据库创建成功", Toast.LENGTH_SHORT).show();  //显示数据库创建成功
    }

    //更新数据库
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

    }
}
