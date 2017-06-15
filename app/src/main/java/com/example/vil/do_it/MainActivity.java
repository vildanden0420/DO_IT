package com.example.vil.do_it;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static final int _PLAN_REQUEST = 1;
    ListView listView;


    //ArrayList<String> restName = new ArrayList<>();
    ArrayList<Plan> planList = new ArrayList<>();
    PlanAdapter adapter;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xdcdcdc));
        setTitle(getDate());
        init();

    }

    public void onClick(View v){
        if(v.getId() == R.id.b1){


        }else if(v.getId() == R.id.b2){
            Intent intent = new Intent(this, Main2Activity.class);
            this.startActivityForResult(intent, _PLAN_REQUEST);

        }else if(v.getId() == R.id.b3){
            Intent intent = new Intent(this, Stopwatch.class);
            this.startActivityForResult(intent, _PLAN_REQUEST);
        }

    }

    public void init(){
        listView = (ListView)findViewById(R.id.listview);


        adapter = new PlanAdapter(planList,this);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putExtra("restaurant", planList.get(position));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == _PLAN_REQUEST) {
            if (resultCode == RESULT_OK) {
                Plan plan = data.getParcelableExtra("plan");
                this.planList.add(plan);
                adapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getDate(){
        SimpleDateFormat curDate = new SimpleDateFormat("yyyy-MM-dd");
        String date = curDate.format(new Date());
        return date;
    }

}