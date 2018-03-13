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

public class Moisture extends AppCompatActivity {

    ListView list;
    feedAdapter adapter;
    ArrayList<shortfeed> feedlist;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moisture);
        list=(ListView) findViewById(R.id.mosture);
        feedlist = new ArrayList<shortfeed>();

        Bundle extra = getIntent().getExtras();
        final int Id =extra.getInt("id");

        if (Id==13){
            url ="https://kohinurjosna.pythonanywhere.com/irrigation/default/mosture.json";
        }else if (Id==23){
            url ="https://kohinurjosna.pythonanywhere.com/irrigation/default/wmosture.json";
        }



        String url ="https://kohinurjosna.pythonanywhere.com/irrigation/default/mosture.json";
        final ProgressDialog loading = ProgressDialog.show(Moisture.this,"Loading Feeds","Please wait",false,false);

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
                                        f.setNodevalue(feed.getString("mosture"));

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
                        Toast.makeText(Moisture.this,"An unexpected error occurred",Toast.LENGTH_LONG).show();

                    }
                });

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(Moisture.this).addTorequestrue(jsObjRequest);





    }






}
