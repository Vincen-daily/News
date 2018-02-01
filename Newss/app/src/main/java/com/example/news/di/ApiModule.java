package com.example.news.di;

import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;

import com.example.news.network.ApiService;
import com.example.news.network.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;




import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xiecy on 2018/01/26.
 */
@Module
public class ApiModule {

    private String baseURL = "https://newsapi.org/";

    @Singleton
    @Provides
    public Service providesService(ApiService apiService) {
        return new Service(apiService);
    }
    @Singleton
    @Provides
    public ApiService providesApiServices(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
    @Singleton
    @Provides
    public Retrofit providesRetrofit() {


        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

}
