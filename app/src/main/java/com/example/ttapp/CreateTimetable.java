package com.example.ttapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateTimetable extends AppCompatActivity {

    EditText coursename ;
    EditText instructor ;
    EditText link ;
    Spinner mstart;
    Spinner mend;
    Spinner tustart;
    Spinner tuend;
    Spinner westart;
    Spinner weend;
    Spinner thstart;
    Spinner thend;
    Spinner fristart;
    Spinner friend;

    DatabaseReference dbref;
    DatabaseReference secondref;

    Button submit;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_timetable);

        coursename = findViewById(R.id.coursename);
        instructor = findViewById(R.id.instructorname);
        link = findViewById(R.id.link);
        mstart = findViewById(R.id.mondayst);
        mend = findViewById(R.id.mondayend);
        tustart = findViewById(R.id.tuesdayst);
        tuend = findViewById(R.id.tuesdayend);
        westart = findViewById(R.id.wedst);
        weend = findViewById(R.id.wedend);
        thstart = findViewById(R.id.thurst);
        thend = findViewById(R.id.thurend);
        fristart = findViewById(R.id.frist);
        friend = findViewById(R.id.friend);

        dbref = FirebaseDatabase.getInstance().getReference("course");
        secondref = FirebaseDatabase.getInstance().getReference();
        submit = findViewById(R.id.createbutton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createcourse();
            }
        });






    }

    private void createcourse(){
        String cname = coursename.getText().toString();
        String intructname = instructor.getText().toString();
        String clink = link.getText().toString();
        String mostart = mstart.getSelectedItem().toString();
        String moend = mend.getSelectedItem().toString();
        String tstart = tustart.getSelectedItem().toString();
        String tend = tuend.getSelectedItem().toString();
        String wedstart = westart.getSelectedItem().toString();
        String wednd = weend.getSelectedItem().toString();
        String thurstart = thstart.getSelectedItem().toString();
        String thurend = thend.getSelectedItem().toString();
        String frstart = fristart.getSelectedItem().toString();
        String frend = friend.getSelectedItem().toString();


        if(!TextUtils.isEmpty(cname) && !TextUtils.isEmpty(intructname) && !TextUtils.isEmpty(clink)){
            final String id = dbref.push().getKey();

            course ncourse = new course(id,cname,intructname,clink,mostart,moend,tstart,tend,wedstart,wednd,thurstart,thurend,frstart,frend);

            dbref.child(id).setValue(ncourse);

            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this,home.class);
            startActivity(intent);



        }
        else{
            Toast.makeText(this.getBaseContext(),"all fields are necessary",Toast.LENGTH_SHORT).show();
        }
    }


}