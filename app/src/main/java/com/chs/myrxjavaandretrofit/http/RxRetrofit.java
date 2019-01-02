package com.chs.myrxjavaandretrofit.http;

import com.chs.myrxjavaandretrofit.http.request.GetRequest;
import com.chs.myrxjavaandretrofit.http.request.PostRequest;

/**
 * 作者：chs
 * 时间：2018-12-20 16:45
 * 描述：
 */
public class RxRetrofit {


    public static GetRequest get(String url) {
        return new GetRequest(url);
    }

    public static PostRequest post(String url) {
        return new PostRequest(url);
    }

}
