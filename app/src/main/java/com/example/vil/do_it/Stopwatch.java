package com.example.vil.do_it;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Stopwatch extends AppCompatActivity {
    TextView time;
    ImageView btnStart, btnPause;
    Boolean start = false;
    CustomTask task;
    int interval = 1000;
    int totalTime = 0;
    int setTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xdcdcdc));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        time = (TextView)findViewById(R.id.time);
        btnStart = (ImageView)findViewById(R.id.btnStart);
        btnPause = (ImageView)findViewById(R.id.btnPause);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!start){
                    start= true;
                    if(totalTime!=0){
                        int hour = totalTime/3600;
                        int min = totalTime/60;
                        int sec = totalTime%60;
                        String hourTime = timeSetting(hour);
                        String minTime = timeSetting(min);
                        String secTime = timeSetting(sec);


                        time.setText(hourTime+":"+minTime+":"+secTime);
                    }

                    task = new CustomTask();
                    task.execute(interval);
                }


            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime = totalTime;
                start = false;
                task.cancel(true);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.stopwatch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.done){
            //현재 공부시간 저장하고 종료

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("종료");
            dlg.setMessage("현재까지 공부한 시간이 저장됩니다. 종료하시겠습니까?");
            dlg.setNegativeButton("취소", null);
            dlg.setPositiveButton("확인",
                    new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            try {
//                                BufferedWriter bw = new BufferedWriter(
//                                        new FileWriter(getFilesDir()+ "stopwatch.txt", true));
//                                bw.write(getDate()+"/"+totalTime+"\n");
//                                bw.close();
//                                //Toast.makeText(getApplicationContext(), "저장완료", Toast.LENGTH_SHORT).show();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                                //Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
//                            }
                            finish();
                        }
                    });
            dlg.show();
            return true;
        }else if(item.getItemId()==R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class CustomTask extends AsyncTask<Integer,Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... params) {
            for(int i = 1;i<101;i++){
                if(isCancelled() ==true){
                    return null;
                }
                try {
                    Thread.sleep(1000);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            totalTime = values[0]+setTime;

            int hour = totalTime/3600;
            int min = totalTime/60;
            int sec = totalTime%60;
            String hourTime = timeSetting(hour);
            String minTime = timeSetting(min);
            String secTime = timeSetting(sec);


            time.setText(hourTime+":"+minTime+":"+secTime);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

    public String timeSetting(int time){
        String setTime = "";
        if(time<10){
            setTime = "0"+time;
        }else{
            setTime = String.valueOf(time);
        }

        return setTime;
    }

    public String getExternalPath() {
        String sdPath = "";
        String ext = Environment.getExternalStorageState();
        if (ext.equals(Environment.MEDIA_MOUNTED)) {
            sdPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        } else
            sdPath = getFilesDir() + "";
        return sdPath;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getDate(){
        SimpleDateFormat curDate = new SimpleDateFormat("yyyy-MM-dd");
        String date = curDate.format(new Date());
        return date;
    }
}
