package com.cxx.architecture.cxlibrary.log;

/**
 * @author: cuixiaoxiao
 * @time: 2021/1/27
 * @email: cuixiaoxiao@58.com
 * @describe: 日志格式化接口
 */
public interface CxLogFormatter<T> {
    String format(T data);
}
