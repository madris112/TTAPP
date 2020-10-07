package com.example.ttapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class pdfAdapter extends RecyclerView.Adapter<pdfAdapter.pdfViewHolder> {

    private Context context;
    private List<uploadPDF> pdflist;
    private RecyclerViewClickListener listener;

    DatabaseReference dbrefuploads;



    public pdfAdapter(Context context, List<uploadPDF> pdflist, RecyclerViewClickListener listener) {
        this.context = context;
        this.pdflist = pdflist;
        this.listener = listener;
    }

    @NonNull
    @Override
    public pdfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pdf_list_cardview,parent,false);
        return new pdfViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pdfViewHolder holder, final int position) {
        final uploadPDF upldpdf = pdflist.get(position);
        holder.name.setText(upldpdf.getName());
        holder.deadline.setText(upldpdf.getLastdateofsubmission());

        holder.deleteimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete this file?")
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String name = upldpdf.getName();

                                dbrefuploads = FirebaseDatabase.getInstance().getReference().child("uploads").child(name);
                                Toast.makeText(context,dbrefuploads.getKey().toString(),Toast.LENGTH_SHORT).show();
                                dbrefuploads.removeValue();



                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.create().show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return pdflist.size();
    }

    public interface RecyclerViewClickListener{void onClick(View v,int position);}

    public class pdfViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name,deadline;
        ImageView deleteimage;
        public pdfViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.pdf_file_name);
            deadline = itemView.findViewById(R.id.deadline_date);
            deleteimage = itemView.findViewById(R.id.delete_pdf);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
           listener.onClick(view,getAdapterPosition());
        }
    }
}
