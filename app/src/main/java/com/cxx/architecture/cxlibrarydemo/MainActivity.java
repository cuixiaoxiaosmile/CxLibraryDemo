package com.cxx.architecture.cxlibrarydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cxx.architecture.cxlibrarydemo.demo.CxLogDemoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTestLog(View view) {
        startActivity(new Intent(this, CxLogDemoActivity.class));
    }
}