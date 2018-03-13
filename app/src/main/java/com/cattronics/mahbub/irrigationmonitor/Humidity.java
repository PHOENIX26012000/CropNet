package com.cattronics.mahbub.irrigationmonitor;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Humidity extends AppCompatActivity {
    ListView list;
    feedAdapter adapter;
    ArrayList<shortfeed> feedlist;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity);
        list=(ListView) findViewById(R.id.humit);
        feedlist = new ArrayList<shortfeed>();
        Bundle extra = getIntent().getExtras();
        final int Id =extra.getInt("id");

        if (Id==12){
            url ="https://kohinurjosna.pythonanywhere.com/irrigation/default/humidity.json";
        }else if (Id==22){
            url ="https://kohinurjosna.pythonanywhere.com/irrigation/default/whumidity.json";
        }

        final ProgressDialog loading = ProgressDialog.show(Humidity.this,"Loading Feeds","Please wait",false,false);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url,null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray feedjson=response.getJSONArray("feeds");
                                    for(int i=0;i<feedjson.length();i++){

                                        shortfeed f=new shortfeed();
                                        JSONObject feed= feedjson.getJSONObject(i);

                                        f.setDate(feed.getString("ptime"));
                                        f.setNode(feed.getString("node"));
                                        f.setNodevalue(feed.getString("humidity"));

                                        feedlist.add(f);


                                        feedAdapter adapter=new feedAdapter(getApplicationContext(),R.layout.feedlist,feedlist);
                                        list.setAdapter(adapter);
                                        loading.dismiss();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        loading.dismiss();
                        Toast.makeText(Humidity.this,"An unexpected error occurred",Toast.LENGTH_LONG).show();

                    }
                });

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(Humidity.this).addTorequestrue(jsObjRequest);





    }






}

