package com.example.ttapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class home extends AppCompatActivity {

    FloatingActionButton fab;
    PagerAdapter pgadapter;
    ArrayList<String> tabnames;

    todayclass todayclass ;
    todaydeadline todaydeadline;



    ViewPager vpgr;
    TabLayout tblyt;
    TabItem tabitem1;
    TabItem tabitem2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fab = findViewById(R.id.FAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),CreateTimetable.class);
                startActivity(intent);
            }
        });

        vpgr = findViewById(R.id.vpgr);
        tblyt = findViewById(R.id.tblyt);
        tabitem1 = findViewById(R.id.tl);
        tabitem2 = findViewById(R.id.tdl);

        tabnames = new ArrayList<>();
        tabnames.add("Today's class");
        tabnames.add("Todaydeadline");

        todayclass = new todayclass();
        todaydeadline = new todaydeadline();

        pgadapter = new PagerAdapter(getSupportFragmentManager(),2);
        pgadapter.addfragment(todayclass,"Today's class");
        pgadapter.addfragment(todaydeadline,"Today's deadline");
        vpgr.setAdapter(pgadapter);




    }
}