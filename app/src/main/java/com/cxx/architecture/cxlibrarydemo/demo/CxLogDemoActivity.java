package com.cxx.architecture.cxlibrarydemo.demo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cxx.architecture.cxlibrary.log.CxLog;
import com.cxx.architecture.cxlibrary.log.CxLogConfig;
import com.cxx.architecture.cxlibrary.log.CxLogManager;
import com.cxx.architecture.cxlibrary.log.CxLogType;
import com.cxx.architecture.cxlibrary.log.CxViewPrinter;
import com.cxx.architecture.cxlibrarydemo.R;

/**
 * @author: cuixiaoxiao
 * @time: 2021/1/28
 * @email: cuixiaoxiao@58.com
 * @describe:
 */
public class CxLogDemoActivity extends AppCompatActivity {
    private CxViewPrinter cxViewPrinter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cxlogdemo);
        //添加界面悬浮展示log
        cxViewPrinter = new CxViewPrinter(this);
        cxViewPrinter.getViewProvider().showFloatingView();
    }

    public void onTestLog(View view) {
        CxLogManager.getInstance().addPrinter(cxViewPrinter);
        CxLog.log(new CxLogConfig() {
            @Override
            public boolean includeThread() {
                return false;
            }
        }, CxLogType.I, "-----","----","开始了呦");
        CxLog.a("哈喽");
    }
}
