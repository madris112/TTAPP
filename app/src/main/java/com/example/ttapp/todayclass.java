package com.example.ttapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class todayclass extends Fragment {

    List<course> retreivecoursedata;
    DatabaseReference dbref ;
    ListView lstvi;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_todayclass, container, false);

        retreivecoursedata = new ArrayList<>();

        lstvi = view.findViewById(R.id.lstview);

        dbref = FirebaseDatabase.getInstance().getReference("course");

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot values: dataSnapshot.getChildren()) {

                    String weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(System.currentTimeMillis());
                    weekday_name.toLowerCase();

                    Log.i("day",weekday_name);

                    course coursecontent = values.getValue(course.class);

                    if(weekday_name.equals("Monday"))
                    {
                        String sttime = coursecontent.getMstart();

                        if(!sttime.equals("NA"))
                        {
                            Log.i("sttime",sttime);
                            retreivecoursedata.add(coursecontent);
                        }
                    }
                    else if(weekday_name.equals("Tuesday"))
                    {
                        String sttime = coursecontent.getTustart();
                        if(!sttime.equals("NA"))
                        {
                            retreivecoursedata.add(coursecontent);
                        }
                    }
                    else if(weekday_name.equals("Wednesday"))
                    {
                        String sttime = coursecontent.getWedstart();
                        if(!sttime.equals("NA"))
                        {
                            retreivecoursedata.add(coursecontent);
                        }
                    }
                    else if(weekday_name.equals("Thursday"))
                    {
                        String sttime = coursecontent.getThstart();
                        if(!sttime.equals("NA"))
                        {
                            retreivecoursedata.add(coursecontent);
                        }
                    }
                    else if(weekday_name.equals("Friday"))
                    {
                        String sttime = coursecontent.getFristart();
                        if(!sttime.equals("NA"))
                        {
                            retreivecoursedata.add(coursecontent);
                        }
                    }





                }

                ArrayAdapter adpt = new ArrayAdapter(getContext(),retreivecoursedata);
                lstvi.setAdapter(adpt);






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }
}