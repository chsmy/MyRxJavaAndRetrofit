package com.chs.myrxjavaandretrofit.okhttp;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：chs
 * 时间：2018-09-27 14:56
 * 描述：
 */
public class PostHttp {
    public static void main(String[] args){
        OkHttpClient client = new OkHttpClient();
        FormBody body = new FormBody.Builder()
                .add("username","chs2018")
                .add("password","ab942559")
                .build();
        Request request = new Request.Builder().url("http://www.wanandroid.com/user/login").post(body).build();

        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()){
                System.out.println("res:"+ response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
