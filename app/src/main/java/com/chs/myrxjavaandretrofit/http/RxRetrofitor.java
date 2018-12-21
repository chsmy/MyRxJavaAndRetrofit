package com.chs.myrxjavaandretrofit.http;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 作者：chs
 * 时间：2018-12-20 16:57
 * 描述：
 */
public class RxRetrofitor {
    private static final String BASE_URL = "http://api.douban.com";
    private static final int TIME_OUT = 60;
    public static final boolean debug = true;

    public static final class ParamasHolder{
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }
    public static WeakHashMap<String, Object> getParams() {
        return ParamasHolder.PARAMS;
    }

    public static final class Holder{
        public static final RxRetrofitor instance = new RxRetrofitor();
    }

    public Retrofit getRetorfit(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if(debug){
            //显示日志
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(TIME_OUT,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    /**
     * Service接口
     */
    private static final class RestServiceHolder {
        private static final ApiService REST_SERVICE =
                Holder.instance.getRetorfit().create(ApiService.class);
    }

    public static ApiService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

}
