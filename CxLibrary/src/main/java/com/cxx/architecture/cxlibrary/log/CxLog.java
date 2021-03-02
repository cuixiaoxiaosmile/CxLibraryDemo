package com.cxx.architecture.cxlibrary.log;

import androidx.annotation.NonNull;

import com.cxx.architecture.cxlibrary.utils.CxStackTraceUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author: cuixiaoxiao
 * @time: 2021/1/27
 * @email: cuixiaoxiao@58.com
 * @describe: 打印log的门面
 */
public class CxLog {
    private static String CXLOG_PACKAGE_NAME;

    static {
        String className = CxLog.class.getName();
        CXLOG_PACKAGE_NAME = className.substring(0, className.lastIndexOf(".") + 1);
    }

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
    public static void log(@CxLogType.TYPE int type, Object... contents) {
        log(type, CxLogManager.getInstance().getCxLogConfig().getGloableTag(), contents);
    }

    public static void log(@CxLogType.TYPE int type, @NonNull String tag, Object... contents) {
        log(CxLogManager.getInstance().getCxLogConfig(), type, tag, contents);
    }

    public static void log(@NonNull CxLogConfig cxLogConfig, @CxLogType.TYPE int type, @NonNull String tag, Object... contents) {
        if (!cxLogConfig.enable()) {
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (cxLogConfig.includeThread()) {
            String threadName = CxLogConfig.CX_THREAD_FORMATTER.format(Thread.currentThread());
            stringBuilder.append(threadName);
            stringBuilder.append("\n");
        }
        if (cxLogConfig.stackTraceDepth() > 0) {
            String stackTrace = CxLogConfig.CX_STACKTRACE_FORMATTER
                    .format(CxStackTraceUtils.getCroppedRealStackTrack(new Throwable().getStackTrace(), CXLOG_PACKAGE_NAME, cxLogConfig.stackTraceDepth()));
            stringBuilder.append(stackTrace);
            stringBuilder.append("\n");
        }
        String body = parseBody(cxLogConfig, contents);
        stringBuilder.append(body);
        List<CxLogPrinter> printers = null != cxLogConfig.printers() ? Arrays.asList(cxLogConfig.printers())
                : CxLogManager.getInstance().getPrinters();
        if (null == printers || printers.size() < 1) {
            return;
        }
        for (CxLogPrinter printer : printers) {
            printer.printer(cxLogConfig, type, tag, stringBuilder.toString());
        }
    }

    /**
     * 将打印的多个数据转换为字符串
     *
     * @param contents
     * @return
     */
    private static String parseBody(@NonNull CxLogConfig cxLogConfig, Object... contents) {
        if (null == contents || contents.length < 1) {
            return "";
        }
        if (null != cxLogConfig.injectJsonParse()) {
            if (contents.length == 1 && contents[0] instanceof String) {
                return contents[0].toString();
            }
            return cxLogConfig.injectJsonParse().toJson(contents);
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
