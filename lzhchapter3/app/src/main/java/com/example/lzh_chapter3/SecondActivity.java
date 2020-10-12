package com.example.lzh_chapter3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
public class SecondActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SecondActivity","Task id is "+getTaskId());
        setContentView(R.layout.second_layout);
        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
               // Intent intent = new Intent(FirstActivity.this, FirstActivity.class);
                //Intent intent = new Intent(FirstActivity.this, SecondAActivity.class);
                startActivity(intent);
            }
        });
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.d("SecondActivity","onDestroy");
    }
}