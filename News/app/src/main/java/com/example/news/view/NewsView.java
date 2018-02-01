package com.example.news.view;

import com.example.news.model.News;
import com.example.news.network.NetworkError;

/**
 * Created by xiecy on 2018/01/26.
 */

public interface NewsView {

    void showProgress();

    void removeProgress();

    void showNews(News news);

    void showFailure(String errorMessage);
}
