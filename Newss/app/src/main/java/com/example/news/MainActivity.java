package com.example.news;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.example.news.fragment.AppleFragment;
import com.example.news.fragment.BitcoinFragment;
import com.example.news.fragment.FragmentAdapter;
import com.example.news.fragment.BusinessFragment;
import com.example.news.fragment.TechFragment;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView imageView;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private String TAG = "===========";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewpager);

        toolbar = findViewById(R.id.toolbar);
        collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        imageView = findViewById(R.id.titleImage);

        collapsingToolbar.setTitle("News");
        setSupportActionBar(toolbar);
        Glide.with(this).load(R.mipmap.image2).into(imageView);


        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new AppleFragment());
        fragments.add(new BusinessFragment());
        fragments.add(new BitcoinFragment());
        fragments.add(new TechFragment());

        viewPager.setOffscreenPageLimit(3);


        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("apple");
        tabLayout.getTabAt(1).setText("business");
        tabLayout.getTabAt(2).setText("bitcoin");
        tabLayout.getTabAt(3).setText("tech");


    }



    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "resume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "start");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "destroy");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "restart");
    }


}
