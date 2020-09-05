package com.example.ttapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText nameText, emailText, rollnoText, passwordText;
    Button signUpButton;
    TextView loginText, timeBox;
    ProgressBar progressBar;
    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);
        rollnoText =(EditText) findViewById(R.id.rollnoText);
        passwordText = (EditText)findViewById(R.id.passwordText);
        signUpButton =(Button) findViewById(R.id.signupButton);
        loginText = (TextView)findViewById(R.id.loginText);
        timeBox = findViewById(R.id.timeBox);
        progressBar = findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();


        if(fAuth.getCurrentUser() != null){
            Intent intent = new Intent(getApplicationContext(),home.class);
            startActivity(intent);
            finish();
        }

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


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailText.getText().toString().trim();
                String password = passwordText.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    emailText.setError("Email is requied");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    passwordText.setError("Password is requied");
                    return;
                }
                if(password.length()<4){
                    passwordText.setError("Password must contain atleast 4 characters !");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Successfully Registered",Toast.LENGTH_SHORT);
                            Intent intent = new Intent(getApplicationContext(),home.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this,"Error : " + task.getException().getMessage(),Toast.LENGTH_SHORT);
                        }
                    }
                });

            }
        });




        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }
}