package com.example.lorablok.classes;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lorablok.R;
import com.example.lorablok.interfaces.ItemTouchHelperAdapterOdabrane;
import com.example.lorablok.room.OdabraneIgreRepository;

import java.util.ArrayList;

public class OdabraneItemTouchHelper extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapterOdabrane mAdapter;
    private onMove onMove;

    public OdabraneItemTouchHelper(ItemTouchHelperAdapterOdabrane mAdapter, onMove onMove) {
        this.mAdapter = mAdapter;
        this.onMove = onMove;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(0x000000000);
        onMove.changeList();
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if(actionState == ItemTouchHelper.ACTION_STATE_DRAG){
            viewHolder.itemView.setBackgroundColor(Color.DKGRAY);
        }
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int swipeFlags = ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemSwipe(viewHolder.getAdapterPosition());
        onMove.removeFromList();

    }

    public interface onMove{
        void changeList();
        void removeFromList();
    }
}
