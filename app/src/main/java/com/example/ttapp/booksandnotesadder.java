package com.example.ttapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class booksandnotesadder extends AppCompatActivity {

    EditText edittxt;
    Button uploadresouce;

    StorageReference mstorrageref;
    DatabaseReference mdbref;
    UploadTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booksandnotesadder);

        edittxt = findViewById(R.id.resourcename);
        uploadresouce = findViewById(R.id.bookuploadbutton);

        mstorrageref = FirebaseStorage.getInstance().getReference("resources");
        mdbref = FirebaseDatabase.getInstance().getReference("resources");

        uploadresouce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edittxt.getText().toString().equals("")) {
                    selectPdfFile();
                } else {
                    Toast.makeText(booksandnotesadder.this, "All the fields are requiured", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void selectPdfFile() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select PDF File"), 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {


            uploadPDFfile(data.getData());
        }
    }

    private void uploadPDFfile(Uri data) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        final StorageReference reference = mstorrageref.child(System.currentTimeMillis() + ".pdf");
        uploadTask = reference.putFile(data);

        uploadTask.
                addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                        progressDialog.setMessage("Upload : " + (int) progress + " %");

                    }
                });

        Task<Uri> uri = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                return reference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri url = task.getResult();
                    uploadbooksandnotes upbookandnotes = new uploadbooksandnotes(edittxt.getText().toString(), url.toString());
                    mdbref.child(mdbref.push().getKey()).setValue(upbookandnotes);
                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Intent intent = new Intent(getApplicationContext(), home.class);
                    startActivity(intent);


                }
            }
        });

    }
}