package com.chs.myrxjavaandretrofit;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * 作者：chs
 * 时间：2018-12-21 16:23
 * 描述：
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
