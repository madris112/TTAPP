package com.example.ttapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText emailEdit, passwordEdit;
    Button loginButton;
    TextView sinupText;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEdit = findViewById(R.id.emailEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        loginButton.findViewById(R.id.loginButton);
        sinupText = findViewById(R.id.signupText);

        fAuth= FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEdit.getText().toString().trim();
                String password = passwordEdit.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    emailEdit.setError("Email is requied");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    passwordEdit.setError("Password is requied");
                    return;
                }
                if(password.length()<4){
                    passwordEdit.setError("Password must contain atleast 4 characters !");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"Login Successful.",Toast.LENGTH_SHORT);
                            Intent intent = new Intent(getApplicationContext(),home.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Login.this,"Error : " + task.getException().getMessage(),Toast.LENGTH_SHORT);

                        }
                    }
                });
            }
        });


        sinupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}