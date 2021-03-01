package com.cxx.architecture.cxlibrarydemo;

import android.app.Application;

import com.cxx.architecture.cxlibrary.log.CxConsolePrinter;
import com.cxx.architecture.cxlibrary.log.CxLogConfig;
import com.cxx.architecture.cxlibrary.log.CxLogManager;
import com.google.gson.Gson;

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

            @Override
            public JsonParse injectJsonParse() {
                return new JsonParse() {
                    @Override
                    public String toJson(Object object) {
                        return new Gson().toJson(object);
                    }
                };
            }

            @Override
            public boolean includeThread() {
                return true;
            }

            @Override
            public int stackTraceDepth() {
                return 5;
            }
        }, new CxConsolePrinter());
    }
}
