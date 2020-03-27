package com.vudrag.lorablok.adapters;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.vudrag.lorablok.fragments.FragmentRezultati;

public class pagerAdapterRezultati extends FragmentPagerAdapter {

    private IRezultati iRezultati;

    public pagerAdapterRezultati(FragmentManager fm, IRezultati iRezultati) {
        super(fm);
        this.iRezultati = iRezultati;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new FragmentRezultati();
        Bundle args = new Bundle();
        args.putInt(FragmentRezultati.ARG_OBJECT, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return (iRezultati.naslov(position));
    }

    public interface IRezultati{
        String naslov(int position);
    }
}
