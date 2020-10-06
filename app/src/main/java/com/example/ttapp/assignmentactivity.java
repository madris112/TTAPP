package com.example.ttapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.Constants;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class assignmentactivity extends AppCompatActivity {


    Button upploadbutton;
    ImageView dateButton;
    EditText assignmentname;
    Spinner subjectname;
    TextView dateshow;

    DatabaseReference databaseReference;
    StorageReference strorageReference;
    uploadPDF uploadPDF;
    UploadTask uploadTask;


    int year,month,day;

    String dateinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignmentactivity);

        upploadbutton = findViewById(R.id.uploadbutt);
        assignmentname = findViewById(R.id.assignmentname);
        dateButton = findViewById(R.id.datebutton);
        subjectname = findViewById(R.id.dubjectname);
        dateshow = findViewById(R.id.dateshow);

        final Calendar calendar = Calendar.getInstance();

        strorageReference = FirebaseStorage.getInstance().getReference("uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(assignmentactivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                       dateinfo =(i2 + "/" +i1 + "/" + i);
                        dateshow.setText(dateinfo);


                    }
                }, year, month,day);
                datePickerDialog.show();
            }
        });



        upploadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!subjectname.getSelectedItem().toString().equals("Select Subject") && !assignmentname.getText().toString().equals("")) {
                    selectPdfFile();
                } else {
                    Toast.makeText(assignmentactivity.this, "All the fields are requiured", Toast.LENGTH_SHORT).show();
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

        final StorageReference reference = strorageReference.child(System.currentTimeMillis() + ".pdf");
        uploadTask = reference.putFile(data);

        uploadTask.
                addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress = (100.0 * snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                        progressDialog.setMessage("Upload : "+(int)progress + " %");

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
                    uploadPDF uploadPDF = new uploadPDF(assignmentname.getText().toString().trim(), dateinfo,subjectname.getSelectedItem().toString(),url.toString());
                    databaseReference.child(databaseReference.push().getKey()).setValue(uploadPDF);
                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Intent intent = new Intent(getApplicationContext(),home.class);
                    startActivity(intent);


                }
            }
        });





//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//
//
//                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
//                        while(!uri.isComplete()){
//                            Uri url = uri.getResult();
//                            uploadPDF uploadPDF = new uploadPDF(assignmentname.getText().toString(),lastdate.getText().toString(),subjectname.getSelectedItem().toString(),url.toString());
//                            databaseReference.child(databaseReference.push().getKey()).setValue(uploadPDF);
//
//                        }
//
//                        Intent inten = new Intent(getApplicationContext(),home.class);
//                        startActivity(inten);this is not working
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
}