package com.cattronics.mahbub.irrigationmonitor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class ConditionAnalysis extends AppCompatActivity {


    public static int tm;
    public static int hu;
    public static int mo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition_analysis);

        final TextView t = (TextView) findViewById(R.id.tempr);
        final TextView h = (TextView) findViewById(R.id.Humidi);
        final TextView m = (TextView) findViewById(R.id.mos);
        final TextView reques= (TextView) findViewById(R.id.controlmotor);
        final TextView cs= (TextView) findViewById(R.id.oc);



        String url = "https://kohinurjosna.pythonanywhere.com/irrigation/default/conditionanalysis.json";
        final ProgressDialog loading = ProgressDialog.show(ConditionAnalysis.this, "Loading Conditions", "Please wait", false, false);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {


                                    JSONObject feed = response.getJSONObject("feeds");



                                    tm = feed.getInt("ambient");
                                    hu = feed.getInt("humidity");
                                    mo = feed.getInt("moisture");
                                    String requ= feed.getString("rs");
                                    String css= feed.getString("cs");
                                    reques.setText(requ);
                                    cs.setText(css);


                                    String tmv = String.valueOf(tm);
                                    String huv = String.valueOf(hu);
                                    String mov = String.valueOf(mo);

                                    t.setText(tmv);
                                    h.setText(huv);
                                    m.setText(mov);


                                    loading.dismiss();




                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.v("Exception","Error");
                                    loading.dismiss();


                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        loading.dismiss();
                        Toast.makeText(ConditionAnalysis.this, "Json Loading error", Toast.LENGTH_LONG).show();

                    }
                });

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(ConditionAnalysis.this).addTorequestrue(jsObjRequest);













    }



    public void as(View view){
        Intent i=new Intent(this,MyQuestion.class);
        startActivity(i);
    }


    public void motorcontroller(View view){
        final TextView c= (TextView) findViewById(R.id.controlmotor);
        final TextView motorconditon = (TextView) findViewById(R.id.oc);
        final String state=c.getText().toString();
        final String cstate=motorconditon.getText().toString();


        RequestQueue queue = Volley.newRequestQueue(ConditionAnalysis.this);
        String u = "https://kohinurjosna.pythonanywhere.com/irrigation/default/post_state";

        final ProgressDialog loading = ProgressDialog.show(ConditionAnalysis.this,"Wating...","for motor response...",false,false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, u,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        loading.dismiss();
                        Log.v(TAG,"Response: "+response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        loading.dismiss();
                        Log.v(TAG,"Response: "+ error);


                        Toast.makeText(ConditionAnalysis.this,"Something Wrong. Please Check the Internet Connection",Toast.LENGTH_LONG).show();

                    }
                }){
            //adding parameters to the request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("state", state);
                params.put("cstate", cstate);
                return params;
            }
        };

        MySingleton.getInstance(ConditionAnalysis.this).addTorequestrue(stringRequest);













        String z = "https://kohinurjosna.pythonanywhere.com/irrigation/default/motorcontrol.json";
        final ProgressDialog load = ProgressDialog.show(ConditionAnalysis.this, "Loading Motor Response", "Please wait", false, false);
        JsonObjectRequest jsObRequest = new JsonObjectRequest
                (Request.Method.GET, z, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {


                                    JSONObject feed = response.getJSONObject("feeds");



                                    String state= feed.getString("requested_state");
                                    Log.v("Requested state: ",state);





                                    load.dismiss();

                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);




                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.v("Exception","Error");

                                    loading.dismiss();
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        load.dismiss();
                        Toast.makeText(ConditionAnalysis.this, "Json Loading error", Toast.LENGTH_LONG).show();

                    }
                });
        MySingleton.getInstance(ConditionAnalysis.this).addTorequestrue(jsObRequest);

    }








}
