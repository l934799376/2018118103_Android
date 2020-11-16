package com.example.lzh_chapter6;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        Toast.makeText(arg0, "received in MyBroadcastReceiver",
                Toast.LENGTH_SHORT).show();
    }

}
