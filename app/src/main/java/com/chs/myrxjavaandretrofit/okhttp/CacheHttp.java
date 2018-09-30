package com.chs.myrxjavaandretrofit.okhttp;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：chs
 * 时间：2018-09-28 14:00
 * 描述：
 */
public class CacheHttp {
    public static void main(String[] args){
        int maxCacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(new File("/E/svn"),maxCacheSize);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache).build();
        Request request = new Request.Builder().url("http://www.qq.com/")
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if(response.isSuccessful()){
                String string = response.body().string();
                System.out.println("res-network:"+ response.networkResponse());
                System.out.println("res-cache:"+ response.cacheResponse());
            }

            Response response1 = okHttpClient.newCall(request).execute();
            if(response1.isSuccessful()){
                String string = response1.body().string();
                System.out.println("res-network:"+ response1.networkResponse());
                System.out.println("res-cache:"+ response1.cacheResponse());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
