package com.cxx.architecture.cxlibrary.log;

/**
 * @author: cuixiaoxiao
 * @time: 2021/1/27
 * @email: cuixiaoxiao@58.com
 * @describe:
 */
public abstract class CxLogConfig {
    /**
     * 单行日志的最大长度
     */
    public static final int MAX_LEN = 512;

    /**
     * 返回tag
     * @return
     */
    public String getGloableTag(){
        return "CxLog";
    }

    /**
     * 是否开启log打印
     * @return
     */
    public boolean enable(){
        return true;
    }
}
