package com.example.ttapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.SnapHelper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadActivity extends AppCompatActivity {

//    Button uoloadButton;
//    EditText pdfname;
//    DatabaseReference databaseReference;
//    StorageReference strorageReference;
//    uploadPDF uploadPDF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
//
//        uoloadButton = findViewById(R.id.uploadButton);
//        pdfname = (EditText)findViewById(R.id.pdfname);
//        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");
//        strorageReference = FirebaseStorage.getInstance().getReference();
//        uploadPDF = new uploadPDF();
//
//        uoloadButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                selectPdfFile();
//
//            }
//
//        });
    }
//            private void selectPdfFile() {
//
//        Intent intent  = new Intent();
//        intent.setType("application/pdf");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent,"Select PDF File"),1);
//
//            }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//        if(requestCode ==1 && resultCode == RESULT_OK && data!=null && data.getData()!=null){
//
//            uploadPDFfile(data.getData());
//        }
//    }
//
//    private void uploadPDFfile(Uri data) {
//
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle("Uploading...");
//        progressDialog.show();
//
//        StorageReference reference = strorageReference.child("uploads/"+System.currentTimeMillis()+".pdf");
//        reference.putFile(data)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
//                        while(!uri.isComplete()){
//                            Uri url = uri.getResult();
//                            uploadPDF uploadPDF = new uploadPDF(pdfname.getText().toString(),url.toString());
//                            databaseReference.child(databaseReference.push().getKey()).setValue(uploadPDF);
//
//                        }
//
//                    }
//                })
//                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//
//                        double progress = (100.0 * snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
//                        progressDialog.setMessage("Upload : "+(int)progress + " %");
//
//                    }
//                });
//    }

}