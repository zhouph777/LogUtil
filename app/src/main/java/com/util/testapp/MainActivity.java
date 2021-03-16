package com.util.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.util.logutil.LogUtil;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.start_log).setOnClickListener(this);
        findViewById(R.id.btn_log_d).setOnClickListener(this);
        findViewById(R.id.btn_log_i).setOnClickListener(this);
        findViewById(R.id.btn_log_e).setOnClickListener(this);
        findViewById(R.id.btn_log_w).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_log:
                LogUtil.getInstance().initLogUtil(v,false,"============>>>>>>>>","LogFile");
                break;
            case R.id.btn_log_i:
                LogUtil.getInstance().i("输出info");
                break;
            case R.id.btn_log_e:
                LogUtil.getInstance().e("输出error");
                break;
            case R.id.btn_log_d:
                LogUtil.getInstance().d("输出debug");
                break;
            case R.id.btn_log_w:
                LogUtil.getInstance().w("输出warn");
                break;
            default:
                break;
        }
    }
}