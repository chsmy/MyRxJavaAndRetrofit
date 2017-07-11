package com.chs.myrxjavaandretrofit.rxjavaretrofit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chs.myrxjavaandretrofit.R;

/**
 * 作者：chs on 2016/4/19 14:32
 * 邮箱：657083984@qq.com
 */
public class RxJavaRetrofitActivity extends Activity implements View.OnClickListener {
    private Button btn_1;
    private TextView tv_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_retrofit);
        initView();
    }

    private void initView() {
        btn_1 = (Button) findViewById(R.id.btn_1);
        tv_content = (TextView) findViewById(R.id.tv_content);
        btn_1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
//                GetServiceClient.getInstance().getTopMovie(new Subscriber<DouBanEntity>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(DouBanEntity douBanEntity) {
//                        tv_content.setText(douBanEntity.getTitle()+"      "+douBanEntity.getSubjects().get(0).getImages().getLarge());
//                    }
//                },0,5);
                break;
        }
    }
}
