package com.example.ttapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText rollnoText, passwordText;
    Button signUpButton;
    TextView loginText, timeBox;
    boolean signUpViewIsVisible = true;
    DatabaseReference ref;
    com.example.ttapp.Member member;

    public void register(View view){
        String rollno = rollnoText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        member.setRollno(rollno);
        member.setPassword(password);

        ref.push().setValue(member);

        Intent intent = new Intent(getApplicationContext(),home.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollnoText =(EditText) findViewById(R.id.rollnoEditText);
        passwordText = (EditText)findViewById(R.id.passwordEditText);
        signUpButton =(Button) findViewById(R.id.signUpButton);
        loginText = (TextView)findViewById(R.id.loginTextView);
        timeBox = findViewById(R.id.timeBox);
        member = new Member();

        ref = FirebaseDatabase.getInstance().getReference().child("Members");

        final Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Calendar calendar = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
                                String timeDate = simpleDateFormat.format(calendar.getTime());
                                timeBox.setText(timeDate);
                            }
                        });
                    }
                }catch (Exception e){
                    timeBox.setText(R.string.app_name);
                }
            }
        };
        thread.start();





        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(signUpViewIsVisible){
                    signUpViewIsVisible = false;
                    signUpButton.setText("Login");
                    loginText.setText("or, SignUp");
                }else{
                    signUpViewIsVisible = true;
                    signUpButton.setText("SignUp");
                    loginText.setText("or, Login");
                }
            }
        });

    }
}