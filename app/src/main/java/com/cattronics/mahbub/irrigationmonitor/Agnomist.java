package com.cattronics.mahbub.irrigationmonitor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class Agnomist extends AppCompatActivity {

    ListView list;
    agnoAdapter adapter;
    ArrayList<desfeed> feedlist;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agnomist);
        list=(ListView) findViewById(R.id.agnosu);
        feedlist = new ArrayList<desfeed>();

        url="https://kohinurjosna.pythonanywhere.com/irrigation/default/get_post.json";

        final ProgressDialog loading = ProgressDialog.show(Agnomist.this,"Loading Feeds","Please wait",false,false);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url,null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray feedjson=response.getJSONArray("feeds");
                                    for(int i=0;i<feedjson.length();i++){

                                        desfeed f=new desfeed();
                                        JSONObject feed= feedjson.getJSONObject(i);

                                        f.setName(feed.getString("name"));
                                        f.setDescription(feed.getString("suggestion"));

                                        feedlist.add(f);


                                        agnoAdapter adapter=new agnoAdapter(getApplicationContext(),R.layout.agnomist,feedlist);
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
                        Toast.makeText(Agnomist.this,"An unexpected error occurred",Toast.LENGTH_LONG).show();

                    }
                });

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(Agnomist.this).addTorequestrue(jsObjRequest);





    }


}
