package com.example.news.network;

/**
 * Created by xiecy on 2018/01/26.
 */

public class NetworkError extends Throwable {

    private Throwable error;

    public NetworkError(Throwable error) {
        super(error);
        this.error = error;
    }
    public String getMessage(){
        return error.getMessage();
    }
}
