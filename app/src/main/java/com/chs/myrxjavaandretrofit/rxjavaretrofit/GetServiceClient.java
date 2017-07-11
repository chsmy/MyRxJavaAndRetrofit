package com.chs.myrxjavaandretrofit.rxjavaretrofit;

import com.chs.myrxjavaandretrofit.bean.DouBanEntity;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
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
    public void getTopMovie(Subscriber<DouBanEntity> subscriber, int start, int count){

//        movieService.getTopMovie(start, count)
//                .map(new HttpResultFunc<List<Subject>>())
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);

        Observable observable = getDataService.getDouBanData(start, count);

        toSubscribe(observable, subscriber);
    }
    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
//        o.subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(s);
    }
}
