package com.chs.myrxjavaandretrofit.http;

import android.content.Context;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.RequestBody;

/**
 * 作者：chs
 * 时间：2018-12-20 16:53
 * 描述：
 */
public class RxRetrofitBuilder {
    private static final WeakHashMap<String, Object> PARAMS = RxRetrofitor.getParams();
    private String mUrl = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private File mFile = null;

    public final  RxRetrofitBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final  RxRetrofitBuilder paramas(WeakHashMap<String, Object> params){
        PARAMS.putAll(params);
        return this;
    }
    public final  RxRetrofitBuilder parama(String key, Object value){
        PARAMS.put(key,value);
        return this;
    }
    public final  RxRetrofitBuilder file(File file){
        this.mFile = file;
        return this;
    }
    public final RxRetrofitBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RxRetrofit build() {
        return new RxRetrofit(mUrl,PARAMS, mBody, mFile, mContext);
    }

}
