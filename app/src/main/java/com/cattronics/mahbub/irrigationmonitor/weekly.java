package com.cattronics.mahbub.irrigationmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class weekly extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);
    }

    public void temp(View view){
        Intent i=new Intent(this,temp.class);
        i.putExtra("id", 21);
        startActivity(i);
    }

    public void Hu(View view){
        Intent i=new Intent(this,Humidity.class);
        i.putExtra("id", 22);
        startActivity(i);
    }
    public void mo(View view){
        Intent i=new Intent(this,Moisture.class);
        i.putExtra("id", 23);
        startActivity(i);
    }

    public void ambient(View view){
        Intent i=new Intent(this,ambient.class);
        i.putExtra("id", 32);
        startActivity(i);
    }

}
