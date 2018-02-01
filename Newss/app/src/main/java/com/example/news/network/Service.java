package com.example.news.network;

import com.example.news.model.News;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiecy on 2018/01/26.
 */

public class Service {


    private ApiService apiService;

    public Service(ApiService apiService) {
        this.apiService = apiService;
    }

    public Disposable getAppleNews(final GetInfoCallback infoCallback) {

        return apiService.getAppleNews().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<News>() {
                    @Override
                    public void accept(News news) throws Exception {
                        infoCallback.onSuccess(news);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        infoCallback.onFailure(throwable.getMessage());
                    }
                });

    }


    public Disposable getBitCoinNews(final GetInfoCallback infoCallback) {

        return apiService.getBitCoinNews().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<News>() {
                    @Override
                    public void accept(News news) throws Exception {
                        infoCallback.onSuccess(news);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        infoCallback.onFailure(throwable.getMessage());
                    }
                });

    }

    public Disposable getTechCrunch(final GetInfoCallback infoCallback) {

        return apiService.getTechCrunch().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<News>() {
                    @Override
                    public void accept(News news) throws Exception {
                        infoCallback.onSuccess(news);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        infoCallback.onFailure(throwable.getMessage());
                    }
                });

    }

    public Disposable getBusinessNews(final GetInfoCallback infoCallback) {

        return apiService.getBusinessNews().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<News>() {
                    @Override
                    public void accept(News news) throws Exception {
                        infoCallback.onSuccess(news);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        infoCallback.onFailure(throwable.getMessage());
                    }
                });

    }


    public interface GetInfoCallback {

        void onSuccess(News news);

        void onFailure(String errorMessage);
    }

}
