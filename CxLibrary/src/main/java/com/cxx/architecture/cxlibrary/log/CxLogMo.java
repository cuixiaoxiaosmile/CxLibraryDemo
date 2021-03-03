package com.cxx.architecture.cxlibrary.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author: cuixiaoxiao
 * @time: 2021/3/2
 * @email: cuixiaoxiao@58.com
 * @describe:log信息打印类
 */
public class CxLogMo {
    /**
     * 时间戳
     */
    private long timeMillis;
    /**
     * 日志级别
     */
    private int level;
    /**
     * tag标识
     */
    private String tag;
    /**
     * 日志内容
     */
    private String log;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);

    public CxLogMo(long timeMillis, int level, String tag, String log) {
        this.timeMillis = timeMillis;
        this.level = level;
        this.tag = tag;
        this.log = log;
    }

    public String getLog() {
        return log;
    }

    public int getLevel() {
        return level;
    }

    /**
     * 将组合标志信息(时间戳+tag+日志等级)与日志内容换行组合打印
     *
     * @return
     */
    public String flattenedLog() {
        return getFlattened() + "\n" + log;
    }

    /**
     * 将时间戳+日志等级+tag 格式化
     *
     * @return
     */
    public String getFlattened() {
        return format(timeMillis) + '|' + level + '|' + tag + "|:";
    }

    /**
     * 将时间戳格式化为字符串
     *
     * @param timeMillis
     * @return
     */
    private String format(long timeMillis) {
        return simpleDateFormat.format(new Date(timeMillis));
    }
}
