package com.example.ttapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PDFListPage extends AppCompatActivity {


    List<String> ass_cs204 = new ArrayList<>();
    List<String> ass_cs205 = new ArrayList<>();
    ArrayAdapter<String> cs204_adapter;
    ArrayAdapter<String> cs205_adapter;
    ListView pdflistview;

    FloatingActionButton assignmentadderfab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_d_f_list_page);

        pdflistview = findViewById(R.id.pdf_list);
        cs204_adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ass_cs204);
        cs205_adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ass_cs205);

        assignmentadderfab = findViewById(R.id.assignmentadderfab);

        assignmentadderfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(getApplicationContext(),assignmentactivity.class);
                startActivity(inte);
            }
        });


        int number = 0;

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            number = bundle.getInt("course");
        }

        if (number == 1) {
            Toast.makeText(this, "iuyt", Toast.LENGTH_SHORT).show();
            ass_cs204.add("Divide and Conquer");
            ass_cs204.add("Sorting");
            pdflistview.setAdapter(cs204_adapter);
            setTitle("CS204");
        } else {
            if (number == 2) {
                Toast.makeText(this, "no no", Toast.LENGTH_SHORT).show();
                ass_cs205.add("Assignment 1");
                ass_cs205.add("Assignment 2");
                pdflistview.setAdapter(cs205_adapter);
                setTitle("CS205");
            }else{
                Toast.makeText(this, "shut up", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
