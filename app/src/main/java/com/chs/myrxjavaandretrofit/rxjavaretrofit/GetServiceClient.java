package com.chs.myrxjavaandretrofit.rxjavaretrofit;

import com.chs.myrxjavaandretrofit.bean.DouBanEntity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：chs on 2016/4/19 14:15
 * 邮箱：657083984@qq.com
 */
public class GetServiceClient {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private GetDataService getDataService;
    //构造方法私有
    private GetServiceClient() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())//设置解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//跟rxjaba关联
                .baseUrl(BASE_URL)
                .build();

        getDataService = retrofit.create(GetDataService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final GetServiceClient INSTANCE = new GetServiceClient();
    }

    //获取单例
    public static GetServiceClient getInstance(){
        return SingletonHolder.INSTANCE;
    }
    public void getTopMovie(Observer<DouBanEntity> observer, int start, int count){

        Observable<DouBanEntity> observable = getDataService.getDouBanData(start, count);

        toSubscribe(observable, observer);
    }
    private <T> void toSubscribe(Observable<T> o, Observer<T> observer){
                o.subscribeOn(Schedulers.io())//指定Observable
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//指定observer
                .subscribe(observer);
    }
}
