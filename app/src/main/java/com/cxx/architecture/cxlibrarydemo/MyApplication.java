package com.cxx.architecture.cxlibrarydemo;

import android.app.Application;

import com.cxx.architecture.cxlibrary.log.CxLog;
import com.cxx.architecture.cxlibrary.log.CxLogConfig;
import com.cxx.architecture.cxlibrary.log.CxLogManager;

/**
 * @author: cuixiaoxiao
 * @time: 2021/1/28
 * @email: cuixiaoxiao@58.com
 * @describe:
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CxLogManager.init(new CxLogConfig() {
            @Override
            public boolean enable() {
                return true;
            }
        });
    }
}
