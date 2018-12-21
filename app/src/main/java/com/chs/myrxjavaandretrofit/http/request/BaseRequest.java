package com.chs.myrxjavaandretrofit.http.request;

import android.util.Log;

import com.chs.myrxjavaandretrofit.http.ApiService;
import com.chs.myrxjavaandretrofit.http.HttpMethod;
import com.chs.myrxjavaandretrofit.http.RxRetrofitor;

import java.io.File;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 作者：chs
 * 时间：2018-12-21 11:10
 * 描述：
 */
public abstract class BaseRequest {
    private static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    private  String mUrl;
    private RequestBody BODY;
    private File mFile;

    BaseRequest(String url){
        this.mUrl = url;
    }
    abstract HttpMethod getMethod();


    public final BaseRequest url(String url){
        this.mUrl = url;
        return this;
    }

    public final  BaseRequest paramas(WeakHashMap<String, Object> params){
        PARAMS.putAll(params);
        return this;
    }
    public final  BaseRequest parama(String key, Object value){
        PARAMS.put(key,value);
        return this;
    }
    public final  BaseRequest file(File file){
        this.mFile = file;
        return this;
    }
    public final BaseRequest file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public void execute(){
        final ApiService service = RxRetrofitor.getRestService();
        Observable observable = null;
        switch (getMethod()){
            case GET:
                observable = service.get(mUrl, PARAMS);
                break;
            case POST:
                observable = service.post(mUrl, PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(mUrl, BODY);
                break;
            case PUT:
                observable = service.put(mUrl, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(mUrl, BODY);
                break;
            case DELETE:
                observable = service.delete(mUrl, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), mFile);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", mFile.getName(), requestBody);
                observable = service.upload(mUrl, body);
                break;
            default:
                break;
        }
        toSubscribe(observable);
    }
    private <T> void toSubscribe(Observable<T> o){
        o.subscribeOn(Schedulers.io())//指定Observable
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//指定observer
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(T t) {
                        Log.i("onNext",t.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("onError",e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("onComplete","onComplete");
                    }
                });
    }
}
