package com.chs.myrxjavaandretrofit.okhttp;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：chs
 * 时间：2018-09-29 14:35
 * 描述：
 */
public class RangeHttp {
    public static void main(String[] args){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("https://himg.bdimg.com/sys/portrait/item/4d43f26d.jpg?time=5593").
                addHeader("Range","bytes=0-")
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            System.out.println("content-length:"+response.body().contentLength());
            if(response.isSuccessful()){
                for (int i = 0; i < response.headers().size(); i++) {
                    System.out.println(response.headers().name(i)+":"+response.headers().value(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
