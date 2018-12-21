package com.chs.myrxjavaandretrofit.http.request;

import com.chs.myrxjavaandretrofit.http.HttpMethod;

/**
 * 作者：chs
 * 时间：2018-12-21 11:23
 * 描述：
 */
public class GetRequest extends BaseRequest {

    public GetRequest(String url) {
        super(url);
    }

    @Override
    HttpMethod getMethod() {
        return HttpMethod.GET;
    }

}
