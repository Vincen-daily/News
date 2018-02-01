package com.example.news.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by xiecy on 2018/01/30.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mfragments;


    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=mfragments.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return mfragments.size();
    }

    public void setFragments(ArrayList<Fragment> fragments){
        this.mfragments=fragments;
    }
}
