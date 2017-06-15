package com.example.vil.do_it;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Date;

public class Main2Activity extends AppCompatActivity {


        EditText etname;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            setTitle("할 일");

            init();

        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void  onClick(View v){
            if(v.getId() ==R.id.btnAdd){

                Intent intent = new Intent();


                String name = etname.getText().toString();
                String time = "";
                int numTime = 0;


                Plan plan = new Plan(name, time, numTime);

                intent.putExtra("plan", plan);
                setResult(RESULT_OK, intent);
                finish();

            }else if(v.getId() == R.id.btnCancel){

                finish();
            }

        }

        public void init(){
            etname = (EditText)findViewById(R.id.planName);


        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public String getDate(){
            SimpleDateFormat curDate = new SimpleDateFormat("yyyyMMdd");
            String date = curDate.format(new Date());
            return date;
        }
}
