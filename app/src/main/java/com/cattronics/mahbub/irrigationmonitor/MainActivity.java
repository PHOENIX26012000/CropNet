package com.cattronics.mahbub.irrigationmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void td(View view){
        Intent i=new Intent(this,DailyActivity.class);
        startActivity(i);
    }

    public void wk(View view){
        Intent i=new Intent(this,weekly.class);
        startActivity(i);
    }

    public void ca(View view){
        Intent i=new Intent(this,ConditionAnalysis.class);
        startActivity(i);
    }
    public void as(View view){
        Intent i=new Intent(this,Agnomist.class);
        startActivity(i);
    }

}