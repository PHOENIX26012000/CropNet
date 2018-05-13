package com.cattronics.mahbub.irrigationmonitor;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class ManualSensorData extends AppCompatActivity {

    EditText s1, s2 , s3, s4;


    String getS1, getS2, getS3, gets4;
    Button submitdata;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_sensor_data);

        s1 = (EditText) findViewById(R.id.s1);
        s2 = (EditText) findViewById(R.id.s2);
        s3 = (EditText) findViewById(R.id.s3);
        s4 = (EditText) findViewById(R.id.s4);
        submitdata = (Button) findViewById(R.id.submit);


        final AlertDialog.Builder builder=null;

        //hooking onclick listener of button
        submitdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getS1 = s1.getText().toString();
                getS2=s2.getText().toString();
                getS3=s3.getText().toString();
                gets4=s4.getText().toString();





                RequestQueue queue = Volley.newRequestQueue(ManualSensorData.this);
                String URL = "https://kohinurjosna.pythonanywhere.com/irrigation/default/phdata";

                final ProgressDialog loading = ProgressDialog.show(ManualSensorData.this,"Sending...","Please wait...",false,false);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Do something with the response
                                Toast.makeText(ManualSensorData.this,"Success",Toast.LENGTH_LONG).show();
                                loading.dismiss();
                                Log.v(TAG,"Response: "+response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                                loading.dismiss();

                                Toast.makeText(ManualSensorData.this,"An unexpected error occurred",Toast.LENGTH_LONG).show();

                            }
                        }){
                    //adding parameters to the request
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("s1", getS1);
                        params.put("s2", getS2);
                        params.put("s3", getS3);
                        params.put("s4",gets4);
                        return params;
                    }
                };

                MySingleton.getInstance(ManualSensorData.this).addTorequestrue(stringRequest);








            }
        });
    }
}
