package com.vudrag.lorablok.activities;

import android.os.Bundle;
import android.widget.TableLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.vudrag.lorablok.R;
import com.vudrag.lorablok.adapters.pagerAdapterRezultati;
import com.vudrag.lorablok.fragments.FragmentOdabraneIgre;

public class RezultatiActivity extends AppCompatActivity {

    private static final String TAG = "RezultatiActivity";

    private ViewPager viewPager;
    private TabLayout tab;

    private pagerAdapterRezultati pagerAdapterRezultati;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezultati);

        pagerAdapterRezultati = new pagerAdapterRezultati(getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapterRezultati);

        tab = findViewById(R.id.tabs);
        tab.setupWithViewPager(viewPager);

    }

    private void init(){

    }
}
