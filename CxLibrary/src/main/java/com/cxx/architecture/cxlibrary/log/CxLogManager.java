package com.cxx.architecture.cxlibrary.log;

/**
 * @author: cuixiaoxiao
 * @time: 2021/1/28
 * @email: cuixiaoxiao@58.com
 * @describe:日志管理类
 */
public class CxLogManager {
    private static CxLogManager instance;
    private CxLogConfig cxLogConfig;

    private CxLogManager(CxLogConfig cxLogConfig) {
        this.cxLogConfig = cxLogConfig;
    }

    public static CxLogManager getInstance() {
        return instance;
    }

    public static void init(CxLogConfig cxLogConfig) {
        instance = new CxLogManager(cxLogConfig);
    }

    public CxLogConfig getCxLogConfig() {
        return cxLogConfig;
    }
}
