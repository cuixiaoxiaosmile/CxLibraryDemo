package com.cxx.architecture.cxlibrary.log;

import androidx.annotation.Nullable;

/**
 * @author: cuixiaoxiao
 * @time: 2021/1/27
 * @email: cuixiaoxiao@58.com
 * @describe:日志打印接口
 */
public interface CxLogPrinter {
    void printer(@Nullable CxLogConfig cxLogConfig,int type,String tag,@Nullable String printString);
}
