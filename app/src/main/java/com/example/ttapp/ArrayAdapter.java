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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ArrayAdapter<P> extends BaseAdapter {

    private Context context;
    private List<course> courselist;
    private String start;
    private String end;

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
        TextView starttime = (TextView) view.findViewById(R.id.starttime);
        TextView endtime = (TextView) view.findViewById(R.id.endtime);

        course cor = courselist.get(i);
        coname.setText(cor.getCoursename());
        profname.setText(cor.getInstructorname());

        String weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(System.currentTimeMillis());
        switch(weekday_name)
        {
            case "Monday":
                starttime.setText(cor.getMstart());
                endtime.setText(cor.getMend());
                break;
            case "Tuesday":
                starttime.setText(cor.getTustart());
                endtime.setText(cor.getTuend());
                break;
            case "Wednesday":
                starttime.setText(cor.getWedstart());
                endtime.setText(cor.getWedend());
                break;
            case "Thursday":
                starttime.setText(cor.getThstart());
                endtime.setText(cor.getThend());
                break;
            case "Friday":
                starttime.setText(cor.getFristart());
                endtime.setText(cor.getFriend());
                break;
            default:
                starttime.setText("holiday");
                endtime.setText("holiday");
                break;
        }

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