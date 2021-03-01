package com.cxx.architecture.cxlibrary.log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: cuixiaoxiao
 * @time: 2021/1/28
 * @email: cuixiaoxiao@58.com
 * @describe:日志管理类
 */
public class CxLogManager {
    private static CxLogManager instance;
    private CxLogConfig cxLogConfig;
    private List<CxLogPrinter> cxLogPrinters = new ArrayList<>();

    private CxLogManager(CxLogConfig cxLogConfig,CxLogPrinter[] cxLogPrinters) {
        this.cxLogConfig = cxLogConfig;
        this.cxLogPrinters.addAll(Arrays.asList(cxLogPrinters));
    }

    public static CxLogManager getInstance() {
        return instance;
    }

    public static void init(CxLogConfig cxLogConfig,CxLogPrinter... cxLogPrinters) {
        instance = new CxLogManager(cxLogConfig,cxLogPrinters);
    }

    public CxLogConfig getCxLogConfig() {
        return cxLogConfig;
    }

    /**
     * 添加打印器
     * @param cxLogPrinter
     */
    public void addPrinter(CxLogPrinter cxLogPrinter){
        cxLogPrinters.add(cxLogPrinter);
    }

    /**
     * 移除打印器
     * @param cxLogPrinter
     */
    public void removePrinter(CxLogPrinter cxLogPrinter){
        cxLogPrinters.remove(cxLogPrinter);
    }

    /**
     * 得到打印器列表
     * @return
     */
    public List<CxLogPrinter> getPrinters(){
        return cxLogPrinters;
    }
}
