package com.cattronics.mahbub.irrigationmonitor;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class agnoAdapter extends ArrayAdapter<desfeed> {

    ArrayList<desfeed> magno;
    int Resource;
    Context context;
    LayoutInflater inflater;

    public agnoAdapter(Context context, int resource, ArrayList<desfeed> objects) {
        super(context, resource, objects);
        magno=objects;
        Resource=resource;
        this.context=context;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        agnoAdapter.ViewHolder viewHolder;
        if(convertView==null){

            convertView=inflater.inflate(Resource,null);
            viewHolder=new agnoAdapter.ViewHolder();
            viewHolder.title=(TextView)convertView.findViewById(R.id.ti);
            viewHolder.description=(TextView)convertView.findViewById(R.id.des);



            convertView.setTag(viewHolder);
        }else{
            viewHolder=(agnoAdapter.ViewHolder) convertView.getTag();

        }



        viewHolder.title.setText(magno.get(position).getName());
        viewHolder.description.setText(magno.get(position).getDescription());
        return convertView;
    }




    static class ViewHolder{
        public TextView title;
        public TextView description;

    }
}

