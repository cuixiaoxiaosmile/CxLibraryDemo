package com.cxx.architecture.cxlibrarydemo.demo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cxx.architecture.cxlibrary.log.CxLog;
import com.cxx.architecture.cxlibrarydemo.R;

/**
 * @author: cuixiaoxiao
 * @time: 2021/1/28
 * @email: cuixiaoxiao@58.com
 * @describe:
 */
public class CxLogDemoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cxlogdemo);
    }

    public void onTestLog(View view) {
        CxLog.i("哈喽");
    }
}