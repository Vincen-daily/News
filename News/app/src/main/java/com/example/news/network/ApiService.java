package com.example.news.network;

import com.example.news.model.News;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by xiecy on 2018/01/25.
 */

public interface ApiService {

    @GET("v2/top-headlines?country=us&category=business&apiKey=7fc33f862ad44746bf4e60e2f117d848")
    Observable<News> getBusinessNews();

    @GET("v2/everything?q=bitcoin&sortBy=publishedAt&apiKey=7fc33f862ad44746bf4e60e2f117d848")
    Observable<News> getBitCoinNews();

    @GET("v2/top-headlines?sources=techcrunch&apiKey=7fc33f862ad44746bf4e60e2f117d848")
    Observable<News> getTechCrunch();

    @GET("v2/everything?q=apple&from=2018-01-29&to=2018-01-29&sortBy=popularity&apiKey=7fc33f862ad44746bf4e60e2f117d848")
    Observable<News> getAppleNews();


}
