package com.example.ttapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
    ListView pdflist;
    DatabaseReference databaseReference;
    List<uploadPDF> pdftobeshown;
    FloatingActionButton assignmentadderfab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_p_d_fs);

        toolbar = findViewById(R.id.pdflisttoolbar);
        pdflist = findViewById(R.id.listofpdf);

        pdftobeshown = new ArrayList<>();

        setSupportActionBar(toolbar);


        assignmentadderfab = findViewById(R.id.assignmentadderfab);

        assignmentadderfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(getApplicationContext(),assignmentactivity.class);
                startActivity(inte);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference().child("uploads");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot postsnapshot :  snapshot.getChildren()){
                    uploadPDF uploadPDF = postsnapshot.getValue(com.example.ttapp.uploadPDF.class);
                    pdftobeshown.add(uploadPDF);

                }
                String[] uploads = new String[pdftobeshown.size()];

                for(int i=0; i<uploads.length; i++){
                    uploads[i]= pdftobeshown.get(i).getName();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,uploads);
                pdflist.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        pdflist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                uploadPDF uploadPDF = pdftobeshown.get(i);

                Intent intent = new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(uploadPDF.getUrl()));
                startActivity(intent);
            }
        });


    }
}