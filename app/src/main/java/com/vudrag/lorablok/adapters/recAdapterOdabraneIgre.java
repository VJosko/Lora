package com.vudrag.lorablok.adapters;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.vudrag.lorablok.R;
import com.vudrag.lorablok.classes.Igra;
import com.vudrag.lorablok.interfaces.ItemTouchHelperAdapterOdabrane;

import java.util.ArrayList;

public class recAdapterOdabraneIgre extends RecyclerView.Adapter<recAdapterOdabraneIgre.MyViewHolder> implements
        ItemTouchHelperAdapterOdabrane {

    ArrayList<Igra> igre;
    private ItemTouchHelper mTouchHelper;
    private OnIgraListener mOnIgraListener;

    public recAdapterOdabraneIgre(ArrayList<Igra> igra, OnIgraListener onIgraListener){
        igre = igra;
        this.mOnIgraListener = onIgraListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_odabrane_igre, parent, false);
        MyViewHolder vh = new MyViewHolder(v, mOnIgraListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewIme.setText(igre.get(position).name);
        holder.textViewOpis.setText(igre.get(position).opis);



        boolean expanded = igre.get(position).isExpanded();
        holder.subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);
    }


    @Override
    public int getItemCount() {
        return igre.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Igra fromIgra = igre.get(fromPosition);
        igre.remove(fromIgra);
        igre.add(toPosition, fromIgra);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemSwipe(int position) {
        igre.remove(position);
        notifyItemRemoved(position);

    }

    public void setTouchHelper(ItemTouchHelper touchHelper){
        this.mTouchHelper = touchHelper;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements
            View.OnTouchListener,
            GestureDetector.OnGestureListener
    {
        public TextView textViewIme, textViewOpis;
        public LinearLayout subItem;
        public ImageView opis;
        OnIgraListener mOnIgraListener;
        GestureDetector mGestureDetector;

        public MyViewHolder(View v, final OnIgraListener onIgraListener){
            super(v);
            textViewIme = v.findViewById(R.id.txt_ime);
            subItem = v.findViewById(R.id.sub_item);
            textViewOpis = v.findViewById(R.id.txt_opis);
            opis = v.findViewById(R.id.iv_opis);
            mOnIgraListener = onIgraListener;

            opis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onIgraListener.onOpisClick(getAdapterPosition());
                }
            });
            mGestureDetector = new GestureDetector(v.getContext(), this);
            v.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mGestureDetector.onTouchEvent(event);
            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            mOnIgraListener.onIgraClick(getAdapterPosition());
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            mTouchHelper.startDrag(this);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }


    public interface OnIgraListener{
        void onIgraClick(int position);
        void onOpisClick(int position);
    }

}
