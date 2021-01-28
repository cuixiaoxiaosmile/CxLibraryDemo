package com.cxx.architecture.cxlibrary.log;

/**
 * @author: cuixiaoxiao
 * @time: 2021/1/27
 * @email: cuixiaoxiao@58.com
 * @describe:线程打印器
 */
public class CxThreadFormatter implements CxLogFormatter<Thread> {
    @Override
    public String format(Thread data) {
        return data.getName();
    }
}
