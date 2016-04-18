package com.chs.myrxjavaandretrofit.rxjava;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.chs.myrxjavaandretrofit.R;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 作者：chs on 2016/3/31 15:14
 * 邮箱：657083984@qq.com
 */
public class RxJavaActivity extends Activity implements View.OnClickListener {
    private Button btn_1, btn_2, btn_3, btn_4,btn_5,btn_6;
    private String[] arrs = new String[]{"天空", "大海", "森林", "高山"};
    private ImageView iv_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        initView();
        initEvent();
    }

    private void initEvent() {
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
    }

    private void initView() {
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        iv_img = (ImageView) findViewById(R.id.iv_img);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1://正常用法
                Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("hello world");
                        subscriber.onCompleted();
                    }
                });
                Subscriber<String> subscriber = new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(RxJavaActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                };
                observable.subscribe(subscriber);
                break;
            case R.id.btn_2://简介用法
                Observable<String> observable1 = Observable.just("哈哈我真简单");
                Action1<String> action1 = new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Toast.makeText(RxJavaActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                };
                observable1.subscribe(action1);
                break;
            case R.id.btn_3://操作符 map
                Observable.just("哈哈")
                        .map(new Func1<String, Object>() {
                            @Override
                            public Object call(String s) {
                                return s + "嘿嘿";
                            }
                        })
                        .subscribe(new Action1<Object>() {
                            @Override
                            public void call(Object o) {
                                Toast.makeText(RxJavaActivity.this, o.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.btn_4://依次列出
                Observable.from(arrs).subscribe(new Action1<String>() {   //依次输出数组中的数据
                    @Override
                    public void call(String s) {
                        Toast.makeText(RxJavaActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn_5://筛选
                Observable.just("哈哈")
                        .flatMap(new Func1<String, Observable<?>>() {
                            @Override
                            public Observable<?> call(String s) {
                                return Observable.from(arrs);
                            }
                        })
                        .filter(new Func1<Object, Boolean>() {
                            @Override
                            public Boolean call(Object o) {
                                return "天空".equals(o.toString());
                            }
                        })
                        .subscribe(new Action1<Object>() {
                            @Override
                            public void call(Object o) {
                                Toast.makeText(RxJavaActivity.this, o.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.btn_6://android io线程
                Observable.just(getResources().getDrawable(R.mipmap.girl))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Drawable>() {
                            @Override
                            public void call(Drawable drawable) {
                                iv_img.setImageDrawable(drawable);
                            }
                        });
                break;
        }
    }
}
