package com.cxx.architecture.cxlibrary.log;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * @author: cuixiaoxiao
 * @time: 2021/1/27
 * @email: cuixiaoxiao@58.com
 * @describe: 打印log的门面
 */
public class CxLog {
    public static void v(Object... contents) {
        log(CxLogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(CxLogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(CxLogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(CxLogType.D, tag, contents);
    }

    public static void i(Object... contents) {
        log(CxLogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(CxLogType.I, tag, contents);
    }

    public static void w(Object... contents) {
        log(CxLogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(CxLogType.W, tag, contents);
    }

    public static void e(Object... contents) {
        log(CxLogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(CxLogType.E, tag, contents);
    }

    public static void a(Object... contents) {
        log(CxLogType.A, contents);
    }

    public static void at(String tag, Object... contents) {
        log(CxLogType.A, tag, contents);
    }

    /**
     * 打印log信息
     *
     * @param type
     * @param contents
     */
    private static void log(@CxLogType.TYPE int type, Object... contents) {
        log(type, CxLogManager.getInstance().getCxLogConfig().getGloableTag(), contents);
    }

    private static void log(@CxLogType.TYPE int type, @NonNull String tag, Object... contents) {
        log(CxLogManager.getInstance().getCxLogConfig(), type, tag, contents);
    }

    private static void log(@NonNull CxLogConfig cxLogConfig, @CxLogType.TYPE int type, @NonNull String tag, Object... contents) {
        if (!cxLogConfig.enable()) {
            return;
        }
        Log.println(type, tag, parseBody(contents));
    }

    /**
     * 将打印的多个数据转换为字符串
     *
     * @param contents
     * @return
     */
    private static String parseBody(Object... contents) {
        if (null == contents || contents.length < 1) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < contents.length; i++) {
            stringBuilder.append(contents[i].toString());
            stringBuilder.append(";");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
