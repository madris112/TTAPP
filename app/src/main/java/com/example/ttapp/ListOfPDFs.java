package com.example.ttapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListOfPDFs extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView pdflist;
    DatabaseReference databaseReference;
    List<uploadPDF> pdftobeshown;
    FloatingActionButton assignmentadderfab;
    pdfAdapter adapter;
    pdfAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_p_d_fs);

        toolbar = findViewById(R.id.pdflisttoolbar);
        pdflist = findViewById(R.id.listofpdf);


        pdftobeshown = new ArrayList<>();



        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("assignmentactivity","button pressed");
                Intent homeintent = new Intent(getApplicationContext(),home.class);
                startActivity(homeintent);


            }
        });

        pdflist.setLayoutManager(new LinearLayoutManager(this));


        assignmentadderfab = findViewById(R.id.assignmentadderfab);

        assignmentadderfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(getApplicationContext(),assignmentactivity.class);
                startActivity(inte);
            }
        });

//        assignmentadderfab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent inte = new Intent(getApplicationContext(),assignmentactivity.class);
//                startActivity(inte);
//            }
//        });

        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot postsnapshot :  snapshot.getChildren()){
                    uploadPDF uppdf = postsnapshot.getValue(com.example.ttapp.uploadPDF.class);
                    pdftobeshown.add(uppdf);

                }
             setOnClickListener();
                adapter = new pdfAdapter(ListOfPDFs.this,pdftobeshown,listener);
                pdflist.setAdapter(adapter);

            }

            private void setOnClickListener() {
                listener = new pdfAdapter.RecyclerViewClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        uploadPDF uppdf = pdftobeshown.get(position);
                        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(uppdf.getUrl()));
                        startActivity(intent);
                    }
                };
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

 //       pdflist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
  //          @Override
    //        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
      //          uploadPDF uppdf = pdftobeshown.get(i);
//
  //              Log.i("pdflink",uppdf.getUrl());
//
  //              Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(uppdf.getUrl()));
    //           startActivity(intent);
      //  //    }
       // });



        }


        }
