package com.example.news.presenter;

import com.example.news.view.NewsDetailView;

/**
 * Created by xiecy on 2018/01/26.
 */

public class DetailNewsPresenter {

    private NewsDetailView detailView;

    public DetailNewsPresenter(NewsDetailView detailView) {
        this.detailView = detailView;
    }

    public void showDetail(){

        detailView.showDetail();
    }
}
