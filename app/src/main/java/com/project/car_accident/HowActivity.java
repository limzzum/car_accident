package com.project.car_accident;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HowActivity extends AppCompatActivity implements View.OnClickListener {

    Context mContext;

    Button btn_maps;
    Button btn_chart;
    Button btn_danger;
    Button btn_how;
    Button btn_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how);



        mContext = this;
        initData();
    }

    public void initData(){
        btn_maps =findViewById(R.id.maps);
        btn_chart = findViewById(R.id.chart);
        btn_danger = findViewById(R.id.danger);
        btn_how = findViewById(R.id.how);
        btn_info = findViewById(R.id.info);

        btn_maps.setOnClickListener(this);
        btn_chart.setOnClickListener(this);
        btn_danger.setOnClickListener(this);
        btn_how.setOnClickListener(this);
        btn_info.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == btn_maps){
            Intent intent = new Intent(mContext,MapsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }else if(v == btn_chart){
            Intent intent = new Intent(mContext,ChartActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }else if(v == btn_danger){
            Intent intent = new Intent(mContext,DangerActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }else if(v == btn_how){
            Intent intent = new Intent(mContext,weatherActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }else if(v == btn_info){
            Intent intent = new Intent(mContext,InfoActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }

    }
}