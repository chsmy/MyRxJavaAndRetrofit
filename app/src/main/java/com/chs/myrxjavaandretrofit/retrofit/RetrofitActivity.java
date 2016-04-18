package com.chs.myrxjavaandretrofit.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chs.myrxjavaandretrofit.R;
import com.chs.myrxjavaandretrofit.bean.DouBanEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：chs on 2016/3/31 15:15
 * 邮箱：657083984@qq.com
 */
public class RetrofitActivity extends Activity implements View.OnClickListener {
    private Button btn_1;
    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        initView();
    }

    private void initView() {
        btn_1 = (Button) findViewById(R.id.btn_1);
        tv_content = (TextView) findViewById(R.id.tv_content);
        btn_1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .baseUrl("https://api.douban.com/v2/movie/")
                        .build();
                GetService service = retrofit.create(GetService.class);
                Call<DouBanEntity> entityCall = service.getList(0, 5);
                entityCall.enqueue(new Callback<DouBanEntity>() {
                    @Override
                    public void onResponse(Call<DouBanEntity> call, Response<DouBanEntity> response) {
                        DouBanEntity entity = response.body();
                        tv_content.setText(entity.getTitle()+"      "+entity.getSubjects().get(0).getImages().getLarge());
                    }

                    @Override
                    public void onFailure(Call<DouBanEntity> call, Throwable t) {

                    }
                });
                break;
        }
    }
}
