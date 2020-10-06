package com.example.ttapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class todaydeadline extends Fragment {

    List<uploadPDF> retrievedeadline;
    DatabaseReference dbref;
    RecyclerView deadline_list;
    pdfAdapter deadline_adapter;
    pdfAdapter.RecyclerViewClickListener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View view = inflater.inflate(R.layout.fragment_todaydeadline, container, false);

        retrievedeadline = new ArrayList<>();


        deadline_list = view.findViewById(R.id.deadline_list_view);

        dbref = FirebaseDatabase.getInstance().getReference("uploads");

        final Calendar calendar = Calendar.getInstance();
        int year, month, date;

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        date = calendar.get(Calendar.DAY_OF_MONTH);

        String finaldate=Integer.toString(date);
        String finalmonth = Integer.toString(month);

        if(date/10==0){
            finaldate = "0"+finaldate;
        }

        if(month/10==0){
            finalmonth = "0"+finalmonth;
        }

        final String today_date = finaldate+"/"+finalmonth+"/"+year;

        Toast.makeText(getContext(),today_date,Toast.LENGTH_SHORT).show();

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                for(DataSnapshot values: snapshot.getChildren()){

                    uploadPDF upload = values.getValue(com.example.ttapp.uploadPDF.class);

                    String dateofsubmission = upload.getLastdateofsubmission();



                    if(today_date.equals(dateofsubmission)){
                        retrievedeadline.add(upload);
                    }
                }

                setOnClickListener();
                deadline_adapter = new pdfAdapter(getContext(),retrievedeadline,listener);
                deadline_list.setAdapter(deadline_adapter);

            }

            private void setOnClickListener() {
                listener = new pdfAdapter.RecyclerViewClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        uploadPDF uppdf = retrievedeadline.get(position);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uppdf.getUrl()));
                        startActivity(intent);
                    }
                };
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}