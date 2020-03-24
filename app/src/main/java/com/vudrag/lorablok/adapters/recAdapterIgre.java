package com.vudrag.lorablok.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vudrag.lorablok.R;
import com.vudrag.lorablok.classes.Igra;

import java.util.List;

public class recAdapterIgre extends RecyclerView.Adapter<recAdapterIgre.MyViewHolder> {

    List<Igra> igre;
    private OnIgraListener mOnIgraListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textViewName;
        OnIgraListener onIgraListener;

        public MyViewHolder(View v, OnIgraListener onIgraListener){
            super(v);
            textViewName = v.findViewById(R.id.txt_ime);
            this.onIgraListener = onIgraListener;

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onIgraListener.onIgraClick(getAdapterPosition());
        }
    }

    public interface OnIgraListener{
        void onIgraClick(int position);
    }

    public recAdapterIgre(List<Igra> igra, OnIgraListener onIgraListener){
        igre = igra;
        this.mOnIgraListener = onIgraListener;
    }



    @NonNull
    @Override
    public recAdapterIgre.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_igre, parent, false);
        MyViewHolder vh = new MyViewHolder(v, mOnIgraListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull recAdapterIgre.MyViewHolder holder, int position) {
        holder.textViewName.setText(igre.get(position).name);
    }

    @Override
    public int getItemCount() {
        return igre.size();
    }
}
