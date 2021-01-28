package com.cxx.architecture.cxlibrary.log;

/**
 * @author: cuixiaoxiao
 * @time: 2021/1/27
 * @email: cuixiaoxiao@58.com
 * @describe:堆栈的打印器
 */
public class CxStackTraceFormatter implements CxLogFormatter<StackTraceElement[]> {
    @Override
    public String format(StackTraceElement[] data) {
        StringBuilder stringBuilder = new StringBuilder(128);
        if (null == data || data.length == 0) {
            return null;
        } else if (data.length == 1) {
            return "\t-" + data[0].toString();
        } else {
            for (int i = 0, len = data.length; i < len; i++) {
                if (i == 0) {
                    stringBuilder.append("stackTrace:  \n");
                }
                if (i != len - 1) {
                    stringBuilder.append("\t├ ");
                    stringBuilder.append(data[i].toString());
                    stringBuilder.append("\n");
                } else {
                    stringBuilder.append("\t├ ");
                    stringBuilder.append(data[i].toString());
                }
            }
            return stringBuilder.toString();
        }
    }
}
