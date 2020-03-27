package com.vudrag.lorablok.activities;

import android.os.Bundle;
import android.widget.TableLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.vudrag.lorablok.R;
import com.vudrag.lorablok.adapters.pagerAdapterRezultati;
import com.vudrag.lorablok.classes.Game;
import com.vudrag.lorablok.fragments.FragmentOdabraneIgre;
import com.vudrag.lorablok.room.GamesRepository;

import java.util.ArrayList;
import java.util.List;

public class RezultatiActivity extends AppCompatActivity implements pagerAdapterRezultati.IRezultati {

    private static final String TAG = "RezultatiActivity";

    private ViewPager viewPager;
    private TabLayout tab;

    private pagerAdapterRezultati pagerAdapterRezultati;

    private GamesRepository mGamesRepository;

    private List<Game> mGames;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezultati);

        pagerAdapterRezultati = new pagerAdapterRezultati(getSupportFragmentManager(), this);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapterRezultati);

        mGamesRepository = new GamesRepository(this);
        mGames = new ArrayList<Game>();

        tab = findViewById(R.id.tabs);
        tab.setupWithViewPager(viewPager);

    }

    private void init(){

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
            }
        });
    }

    @Override
    public String naslov(int position) {
        String ime = "";
        switch (position) {
            case 0:
                ime = "1. krug";
                break;
            case 1:
                ime = "2. krug";
                break;
            case 2:
                ime = "3. krug";
                break;
            case 3:
                ime = "4. krug";
                break;
        }
        return ime;
    }
}
