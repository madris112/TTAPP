package com.example.ttapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ArrayAdapter extends BaseAdapter {

    private Context context;
    private List<course> courselist;

    public ArrayAdapter(Context context, List<course> courselist) {
        this.context = context;
        this.courselist = courselist;
    }

    @Override
    public int getCount() {
        return courselist.size();
    }

    @Override
    public Object getItem(int i) {
        return courselist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null){
            view = View.inflate(context,R.layout.timetablecards,null);
        }

        TextView coname = (TextView) view.findViewById(R.id.classname);
        TextView profname = (TextView) view.findViewById(R.id.profname);
        Button joinclassbutton = (Button) view.findViewById(R.id.joinclassbutton);

        course cor = courselist.get(i);
        coname.setText(cor.getCoursename());
        profname.setText(cor.getInstructorname());
        final String courselink = cor.getLink();

        joinclassbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(courselink));
                context.startActivity(intent);


            }
        });

        return view;
    }
}
//https://www.google.co.in/