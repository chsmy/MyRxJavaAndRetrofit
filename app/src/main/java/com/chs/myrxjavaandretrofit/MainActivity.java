package com.chs.myrxjavaandretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chs.myrxjavaandretrofit.retrofit.RetrofitActivity;
import com.chs.myrxjavaandretrofit.rxjava.RxJavaActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_rxjava,btn_retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initView() {
        btn_rxjava = (Button) findViewById(R.id.btn_rxjava);
        btn_retrofit = (Button) findViewById(R.id.btn_retrofit);
    }

    private void initEvent() {
        btn_rxjava.setOnClickListener(this);
        btn_retrofit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn_rxjava:
                intent = new Intent(this, RxJavaActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_retrofit:
                intent = new Intent(this, RetrofitActivity.class);
                startActivity(intent);
                break;
        }
    }
}
