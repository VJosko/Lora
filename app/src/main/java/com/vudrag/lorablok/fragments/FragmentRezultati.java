package com.vudrag.lorablok.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.vudrag.lorablok.R;
import com.vudrag.lorablok.classes.Game;
import com.vudrag.lorablok.classes.Igra;
import com.vudrag.lorablok.room.GamesRepository;
import com.vudrag.lorablok.room.IgreRepository;

import java.util.ArrayList;
import java.util.List;

public class FragmentRezultati extends Fragment {

    public static final String ARG_OBJECT = "object";

    private GamesRepository mGamesRepository;
    private IgreRepository mIgreRepository;
    private List<Game> mGames;
    private List<Igra> mIgre;

    private TextView tvIgrac1, tvIgrac2, tvIgrac3, tvIgrac4;

    private int nPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rezultati, container, false);

        mGamesRepository = new GamesRepository(getContext());
        mIgreRepository = new IgreRepository(getContext());
        mGames = new ArrayList<Game>();
        mIgre = new ArrayList<Igra>();

        tvIgrac1 = view.findViewById(R.id.tv_igrac_1);
        tvIgrac2 = view.findViewById(R.id.tv_igrac_2);
        tvIgrac3 = view.findViewById(R.id.tv_igrac_3);
        tvIgrac4 = view.findViewById(R.id.tv_igrac_4);

        retrieveGames();

        Bundle args = getArguments();
        nPosition = args.getInt(ARG_OBJECT);


        return view;
    }


    private void retrieveGames(){
        mGamesRepository.retrieveGamesTask().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(List<Game> igras) {
                if(mGames.size() > 0){
                    mGames.clear();
                }
                if(igras != null){
                    mGames.addAll(igras);
                }
                setImena();
            }
        });
    }

    private void setImena(){
        tvIgrac1.setText(mGames.get(mGames.size() - 1).getIgrac1());
        tvIgrac2.setText(mGames.get(mGames.size() - 1).getIgrac2());
        tvIgrac3.setText(mGames.get(mGames.size() - 1).getIgrac3());
        tvIgrac4.setText(mGames.get(mGames.size() - 1).getIgrac4());

    }
}
