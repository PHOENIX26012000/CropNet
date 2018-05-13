package com.cattronics.mahbub.irrigationmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DailyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily);
    }

    public void temp(View view){
        Intent i=new Intent(this,temp.class);
        i.putExtra("id", 11);
        startActivity(i);
    }

    public void Hu(View view){
        Intent i=new Intent(this,Humidity.class);
        i.putExtra("id", 12);
        startActivity(i);
    }
    public void mo(View view){
        Intent i=new Intent(this,Moisture.class);
        i.putExtra("id", 22);
        startActivity(i);
    }
    public void ambient(View view){
        Intent i=new Intent(this,ambient.class);
        i.putExtra("id", 31);
        startActivity(i);
    }

}
