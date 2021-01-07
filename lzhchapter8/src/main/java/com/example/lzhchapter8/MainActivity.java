package com.example.lzhchapter8;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button download;
    private TextView textContent;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWindow();
    }

    private void initWindow(){
        this.download = (Button) findViewById(R.id.download);
        this.textContent = (TextView) findViewById(R.id.text_info);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.download.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DownloadTask task = new DownloadTask();
        task.execute(100);
    }

    class DownloadTask extends AsyncTask<Integer, Integer, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(Integer... params) {
            for(int i = 0; i <= 100; i++){
                progressBar.setProgress(i);
                publishProgress(i);
                try {
                    Thread.sleep(params[0]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } ;
            }
            
            return "下载成功";
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            textContent.setText(values[0]+"%");
            super.onProgressUpdate(values);
        }

        //doInBackground最后开始运行，result为doInBackground的return值
        @Override
        protected void onPostExecute(String result) {
            setTitle(result);
            super.onPostExecute(result);
        }
    }
}