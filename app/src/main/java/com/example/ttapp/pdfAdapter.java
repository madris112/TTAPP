package com.example.ttapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class pdfAdapter extends RecyclerView.Adapter<pdfAdapter.pdfViewHolder> {

    private Context context;
    private List<uploadPDF> pdflist;
    private RecyclerViewClickListener listener;

    public pdfAdapter(Context context, List<uploadPDF> pdflist,RecyclerViewClickListener listener) {
        this.context = context;
        this.pdflist = pdflist;
        this.listener = listener;
    }

    @NonNull
    @Override
    public pdfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pdf_list_cardview,null);
        return new pdfViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pdfViewHolder holder, int position) {
        uploadPDF upldpdf = pdflist.get(position);
        holder.name.setText(upldpdf.getName());
        holder.deadline.setText(upldpdf.getLastdateofsubmission());

    }

    @Override
    public int getItemCount() {
        return pdflist.size();
    }

    public interface RecyclerViewClickListener{void onClick(View v,int position);}

    public class pdfViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name,deadline;
        public pdfViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.pdf_file_name);
            deadline = itemView.findViewById(R.id.deadline_date);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
           listener.onClick(view,getAdapterPosition());
        }
    }
}
