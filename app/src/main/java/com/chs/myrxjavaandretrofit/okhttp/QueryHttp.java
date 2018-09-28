package com.chs.myrxjavaandretrofit.okhttp;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：chs
 * 时间：2018-09-27 11:24
 * 描述：
 */
public class QueryHttp {

    public static void main(String[] args){
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl httpUrl = HttpUrl.parse("https://api.douban.com/v2/movie/top250")
                .newBuilder()
                .addQueryParameter("start","0")
                .addQueryParameter("count","5")
                .build();

        Request request = new Request.Builder().url(httpUrl.toString()).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if(response.isSuccessful()){
                System.out.println("res:"+response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
