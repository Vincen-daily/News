package com.example.news.di;


import com.example.news.fragment.AppleFragment;
import com.example.news.fragment.BitcoinFragment;
import com.example.news.fragment.BusinessFragment;
import com.example.news.fragment.TechFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by xiecy on 2018/01/26.
 */
@Singleton
@Component(modules = ApiModule.class)
public interface AppComponent {

    //Service service();



    void inject(BusinessFragment fragment);

    void inject(BitcoinFragment fragment);

    void inject(AppleFragment fragment);

    void inject(TechFragment fragment);
}
