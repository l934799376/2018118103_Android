package com.example.lzh_chapter6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

   // private IntentFilter intentFilter;
   // private NetworkChgeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //intentFilter = new IntentFilter();
        //   intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        //  networkChangeReceiver =new NetworkChgeReceiver();
        //   registerReceiver(networkChangeReceiver, intentFilter);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送全局广播
                Intent intent = new Intent("com.example.lzh_chapter6.MY_BROADCAST");
                sendBroadcast(intent);
            }
        });
    }


   // @Override
  //  protected void onDestroy() {
    //    super.onDestroy();
      //  unregisterReceiver(networkChangeReceiver);


  //  }
  //  class NetworkChgeReceiver extends BroadcastReceiver {
   //     @Override
    //    public void onReceive(Context context, Intent intent) {
   //         ConnectivityManager connectionManager = (ConnectivityManager)
    //                getSystemService(Context.CONNECTIVITY_SERVICE);
   //        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
   //         if (networkInfo != null && networkInfo.isAvailable()) {
    //            Toast.makeText(context, "network is available",
   //                     Toast.LENGTH_SHORT).show();
   //        } else {
    //            Toast.makeText(context, "network is unavailable",
   //                     Toast.LENGTH_SHORT).show();
    //        }

   //     }

  //  }



}

