package com.vudrag.lorablok.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vudrag.lorablok.R;
import com.vudrag.lorablok.adapters.recAdapterIgre;
import com.vudrag.lorablok.classes.Igra;
import com.vudrag.lorablok.interfaces.INovaIgra;
import com.vudrag.lorablok.room.IgreRepository;
import com.vudrag.lorablok.room.OdabraneIgreRepository;

import java.util.ArrayList;
import java.util.List;

public class FragmentOstaleIgre extends Fragment implements recAdapterIgre.OnIgraListener {

    private static final String TAG = "FragmentOstaleIgre";

    private INovaIgra mINovaIgra;

    private IgreRepository mIgreRepository;
    private OdabraneIgreRepository mOdabraneIgreRepository;
    private List<Igra> mIgre;
    private List<Igra> mOdabraneIgre;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ostale_igre, container, false);

        mINovaIgra.setNaslov("Ostale igre");

        mIgreRepository = new IgreRepository(getContext());
        mOdabraneIgreRepository = new OdabraneIgreRepository(getContext());
        mIgre = new ArrayList<>();
        mOdabraneIgre = new ArrayList<>();

        retrieveIgre();
        retrieveOdabraneIgre();

        //------------Recycler-view------------------
        recyclerView = view.findViewById(R.id.rec_igre);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new recAdapterIgre(mIgre,this);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    private void retrieveIgre(){
        mIgreRepository.retrieveIgreTask().observe(this, new Observer<List<Igra>>() {
            @Override
            public void onChanged(List<Igra> igras) {
                if(mIgre.size() > 0){
                    mIgre.clear();
                }
                if(igras != null){
                    mIgre.addAll(igras);
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void retrieveOdabraneIgre(){
        mOdabraneIgreRepository.retrieveIgreTask().observe(this, new Observer<List<Igra>>() {
            @Override
            public void onChanged(List<Igra> igras) {
                if(mOdabraneIgre.size() > 0){
                    mOdabraneIgre.clear();
                }
                if(igras != null){
                    mOdabraneIgre.addAll(igras);
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mINovaIgra = (INovaIgra)getActivity();
    }

    @Override
    public void onIgraClick(int position) {
        Igra igra = mIgre.get(position);
        mIgre.remove(position);
        if(mOdabraneIgre.size() != 0){
            igra.red = mOdabraneIgre.size();
        }
        else
            igra.red = 0;
        mOdabraneIgreRepository.insertIgraTask(igra);
        mIgreRepository.deleteIgra(igra);
    }

}
