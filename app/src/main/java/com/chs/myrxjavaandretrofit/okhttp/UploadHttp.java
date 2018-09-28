package com.chs.myrxjavaandretrofit.okhttp;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者：chs
 * 时间：2018-09-27 16:19
 * 描述：
 */
public class UploadHttp {
    public static void main(String[] args){
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"),new File(""));
        MultipartBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name","")
                .addFormDataPart("filename","",requestBody)
                .build();
        Request request = new Request.Builder().url("")
                .post(body).build();
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
