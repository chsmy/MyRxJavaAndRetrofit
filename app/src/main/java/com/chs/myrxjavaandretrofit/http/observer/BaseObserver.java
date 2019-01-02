package com.chs.myrxjavaandretrofit.http.observer;


import android.net.ParseException;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 作者：chs
 * 时间：2018-12-21 16:40
 * 描述：
 */
public abstract class BaseObserver<T> implements Observer {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Object o) {
         onSuccess((T) o);
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e,exceptionHandler(e));
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T result);

    public abstract void onFailure(Throwable e,String errorMsg);
    public static String exceptionHandler(Throwable e){
        String errorMsg = "未知错误";
        if (e instanceof UnknownHostException) {
            errorMsg = "网络不可用";
        } else if (e instanceof SocketTimeoutException) {
            errorMsg = "请求网络超时";
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            errorMsg = convertStatusCode(httpException);
        } else if (e instanceof ParseException || e instanceof JSONException
                || e instanceof JSONException) {
            errorMsg = "数据解析错误";
        }
        return errorMsg;
    }

    private static String convertStatusCode(HttpException httpException) {
        String msg;
        if (httpException.code() >= 500 && httpException.code() < 600) {
            msg = "服务器处理请求出错";
        } else if (httpException.code() >= 400 && httpException.code() < 500) {
            msg = "服务器无法处理请求";
        } else if (httpException.code() >= 300 && httpException.code() < 400) {
            msg = "请求被重定向到其他页面";
        } else {
            msg = httpException.message();
        }
        return msg;
    }

}
