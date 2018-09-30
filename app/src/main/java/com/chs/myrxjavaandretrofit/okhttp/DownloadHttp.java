package com.chs.myrxjavaandretrofit.okhttp;

import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：chs
 * 时间：2018-09-29 15:43
 * 描述：
 */
public class DownloadHttp {
    ThreadPoolExecutor mPoolExecutor = new ThreadPoolExecutor(2, 2, 60,
            TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
        private AtomicInteger mAtomicInteger = new AtomicInteger(1);
        @Override
        public Thread newThread(@NonNull Runnable r) {
            Thread thread = new Thread(r,"download"+mAtomicInteger.getAndIncrement());
            return thread;
        }
    });
    public static void main(String[] args){
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url("https://himg.bdimg.com/sys/portrait/item/4d43f26d.jpg?time=5593").build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            long contentLength = request.body().contentLength();

            InputStream in = response.body().byteStream();
            String fileName = "";
            FileOutputStream out = new FileOutputStream(new File(fileName));
            byte[] buffer = new byte[1024 * 500];
            int len = 0;
            while ((len = in.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
