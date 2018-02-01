package com.example.news.presenter;

import com.example.news.model.News;
import com.example.news.network.Service;
import com.example.news.view.NewsView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by xiecy on 2018/01/26.
 */

public class NewsPresenter {

    private Service service;

    private NewsView newsView;

    private CompositeDisposable compositeDisposable;

    public NewsPresenter(Service service, NewsView newsView) {
        this.service = service;
        this.newsView = newsView;
        this.compositeDisposable=new CompositeDisposable();
    }

    public void  getBitCoinNews(){

        newsView.showProgress();

        Disposable disposable=service.getBitCoinNews(new Service.GetInfoCallback() {
            @Override
            public void onSuccess(News news) {
                newsView.removeProgress();
                newsView.showNews(news);
            }

            @Override
            public void onFailure(String errorMessage) {
                newsView.removeProgress();
                newsView.showFailure(errorMessage);
            }


        });
        compositeDisposable.add(disposable);

    }

    public void  getAppleNews(){

        newsView.showProgress();

        Disposable disposable=service.getAppleNews(new Service.GetInfoCallback() {
            @Override
            public void onSuccess(News news) {
                newsView.removeProgress();
                newsView.showNews(news);
            }

            @Override
            public void onFailure(String errorMessage) {
                newsView.removeProgress();
                newsView.showFailure(errorMessage);
            }


        });
        compositeDisposable.add(disposable);

    }

    public void  getBusinessNews(){

        newsView.showProgress();

        Disposable disposable=service.getBusinessNews(new Service.GetInfoCallback() {
            @Override
            public void onSuccess(News news) {
                newsView.removeProgress();
                newsView.showNews(news);
            }

            @Override
            public void onFailure(String errorMessage) {
                newsView.removeProgress();
                newsView.showFailure(errorMessage);
            }


        });
        compositeDisposable.add(disposable);

    }

    public void  getTechCrunch(){

        newsView.showProgress();

        Disposable disposable=service.getTechCrunch(new Service.GetInfoCallback() {
            @Override
            public void onSuccess(News news) {
                newsView.removeProgress();
                newsView.showNews(news);
            }

            @Override
            public void onFailure(String errorMessage) {
                newsView.removeProgress();
                newsView.showFailure(errorMessage);
            }


        });
        compositeDisposable.add(disposable);

    }

    public void onStop(){
        compositeDisposable.clear();
    }

}
