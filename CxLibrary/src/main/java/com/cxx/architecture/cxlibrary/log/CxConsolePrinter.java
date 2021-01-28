package com.cxx.architecture.cxlibrary.log;

import android.util.Log;

import androidx.annotation.Nullable;

import static com.cxx.architecture.cxlibrary.log.CxLogConfig.MAX_LEN;

/**
 * @author: cuixiaoxiao
 * @time: 2021/1/27
 * @email: cuixiaoxiao@58.com
 * @describe:
 */
public class CxConsolePrinter implements CxLogPrinter {
    @Override
    public void printer(@Nullable CxLogConfig cxLogConfig, int type, String tag, @Nullable String printString) {
        int len = printString.length();
        int countLines = len / MAX_LEN;
        if (countLines > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            int index = 0;
            for (int i = 0; i < countLines; i++) {
                stringBuilder.append(printString.substring(index, index + MAX_LEN));
                index += MAX_LEN;
            }
            if (index < len) {
                stringBuilder.append(printString.substring(index, len));
            }
            Log.println(type, tag, stringBuilder.toString());
        } else {
            Log.println(type, tag, printString);
        }
    }
}
