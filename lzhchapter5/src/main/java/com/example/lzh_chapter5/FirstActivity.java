package com.example.lzh_chapter5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FirstActivity", "Task id is " + getTaskId());
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
              //  startActivity(intent);

               // Intent intent = new Intent("com.example.lzh_chapter5.ACTION_START");
              //  intent.addCategory("com.example.lzh_chapter5.MY_CATEGORY");
              //  startActivity(intent);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.hao123.com"));
                startActivity(intent);


            }
        });
    }



}