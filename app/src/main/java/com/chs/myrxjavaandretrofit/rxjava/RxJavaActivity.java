package com.chs.myrxjavaandretrofit.rxjava;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chs.myrxjavaandretrofit.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * 作者：chs on 2016/3/31 15:14
 * 邮箱：657083984@qq.com
 */
public class RxJavaActivity extends Activity implements View.OnClickListener {
    private String TAG = "";
    private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8;
    private String[] arrs = new String[]{"天空", "大海", "森林", "高山"};
    private ImageView iv_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        TAG = getPackageName();
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
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
    }

    private void initView() {
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        iv_img = (ImageView) findViewById(R.id.iv_img);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1://正常用法
                //创建被观察者
                Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        e.onNext("hello");
                        e.onNext("Lily");
                        e.onComplete();
                    }
                });
                //创建观察者
                Observer<String> observer = new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        Log.i(TAG, value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete");
                    }
                };
                //建立联系
                observable.subscribe(observer);
                break;
            case R.id.btn_2://简介用法
                Observable<String> observable1 = Observable.just("哈哈我真简单");
                Consumer<String> consumer = new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG, s);
                    }
                };
                observable1.subscribe(consumer);
                break;
            case R.id.btn_3://操作符 map
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        e.onNext("hello");
                        e.onNext("LiLy");
                    }
                }).map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s + "---me";
                    }
                }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG, s);
                    }
                });

                break;
            case R.id.btn_4://flatMap
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        e.onNext("hello");
                        e.onNext("LiLy");
                    }
                }).flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        List<String> list = new ArrayList<String>();
                        list.add(s + "----1");
                        list.add(s + "----2");
                        return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
                    }
                }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG, s);
                    }
                });
                break;
            case R.id.btn_5://concatMap
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        e.onNext("hello");
                        e.onNext("LiLy");
                    }
                }).concatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        List<String> list = new ArrayList<String>();
                        list.add(s + "----1");
                        list.add(s + "----2");
                        list.add(s + "----3");
                        return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
                    }
                }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG, s);
                    }
                });
                break;
            case R.id.btn_6://Concat
                Observable.concat(Observable.just("hello"), Observable.just("LiLy"))
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String s) throws Exception {
                                Log.i(TAG, "concat : " + s);
                            }
                        });
                break;
            case R.id.btn_7://Concat
                Observable.create(new ObservableOnSubscribe<Drawable>() {
                    @Override
                    public void subscribe(ObservableEmitter<Drawable> e) throws Exception {
                        Drawable drawable = ContextCompat.getDrawable(RxJavaActivity.this, R.mipmap.girl);
                        e.onNext(drawable);
                        e.onComplete();
                    }
                })
                        .subscribeOn(Schedulers.io())// 指定 Observable（被观察者 事件） 发生在 IO 线程
                        .observeOn(AndroidSchedulers.mainThread()) // 指定 observer（观察者） 的回调发生在主线程
                        .subscribe(new Consumer<Drawable>() {
                            @Override
                            public void accept(Drawable drawable) throws Exception {
                                iv_img.setImageDrawable(drawable);
                            }
                        });

                break;
            case R.id.btn_8:
                Observable.just("hello")
                        .subscribeOn(Schedulers.newThread())
                        .doOnNext(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                Log.i(TAG, "subscribeOn:"+Thread.currentThread().getName());
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .doOnNext(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                Log.i(TAG, "subscribeOn:"+Thread.currentThread().getName());
                            }
                        })
                        .observeOn(Schedulers.io())
                        .doOnNext(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                Log.i(TAG, "observeOn:"+Thread.currentThread().getName());
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                Log.i(TAG, "observeOn:"+Thread.currentThread().getName());
                            }
                        }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG, Thread.currentThread().getName());
                    }
                });
                break;
        }
    }
}
