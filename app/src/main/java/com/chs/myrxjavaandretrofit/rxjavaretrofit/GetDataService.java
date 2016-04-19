package com.chs.myrxjavaandretrofit.rxjavaretrofit;

import com.chs.myrxjavaandretrofit.bean.DouBanEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者：chs on 2016/4/19 14:12
 * 邮箱：657083984@qq.com
 */
public interface GetDataService {
    @GET("top250")
    Observable<DouBanEntity> getDouBanData(@Query("start") int start, @Query("count") int count);
}
