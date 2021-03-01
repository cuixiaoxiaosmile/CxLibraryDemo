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
    static CxThreadFormatter CX_THREAD_FORMATTER = new CxThreadFormatter();
    static CxStackTraceFormatter CX_STACKTRACE_FORMATTER = new CxStackTraceFormatter();

    /**
     * 返回tag
     *
     * @return
     */
    public String getGloableTag() {
        return "CxLog";
    }

    /**
     * 是否开启log打印
     *
     * @return
     */
    public boolean enable() {
        return true;
    }

    /**
     * 日志中是否包含线程信息
     *
     * @return
     */
    public boolean includeThread() {
        return false;
    }

    /**
     * 获取栈深度
     *
     * @return
     */
    public int stackTraceDepth() {
        return 5;
    }

    /**
     * 返回打印器列表
     * @return
     */
    public CxLogPrinter[] printers(){
        return null;
    }

    /**
     * 格式化数据
     *
     * @return
     */
    public JsonParse injectJsonParse() {
        return null;
    }

    public interface JsonParse {
        String toJson(Object object);
    }
}
