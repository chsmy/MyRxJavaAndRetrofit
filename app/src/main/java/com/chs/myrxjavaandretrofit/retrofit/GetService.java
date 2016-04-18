package com.chs.myrxjavaandretrofit.retrofit;

import com.chs.myrxjavaandretrofit.bean.DouBanEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者：chs on 2016/3/31 17:27
 * 邮箱：657083984@qq.com
 */
public interface GetService {
    @GET("top250")
    Call<DouBanEntity> getList(@Query("start") int start,@Query("count") int count);
}
