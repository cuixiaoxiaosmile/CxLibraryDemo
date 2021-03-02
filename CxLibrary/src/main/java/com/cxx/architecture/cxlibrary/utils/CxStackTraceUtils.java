package com.cxx.architecture.cxlibrary.utils;

import android.text.TextUtils;

/**
 * @author: cuixiaoxiao
 * @time: 2021/3/2
 * @email: cuixiaoxiao@58.com
 * @describe:
 */
public class CxStackTraceUtils {
    /**
     * 过滤无用堆栈信息，并截取指定长度
     * @param stackTrace
     * @param ignorePackage
     * @param maxDepth
     * @return
     */
    public static StackTraceElement[] getCroppedRealStackTrack(StackTraceElement[] stackTrace, String ignorePackage, int maxDepth) {
        return cropStackTrace(getRealStackTrack(stackTrace,ignorePackage),maxDepth);
    }

    /**
     * 过滤指定包名的堆栈信息
     * @param stackTrace
     * @param ignorePackage
     * @return
     */
    private static StackTraceElement[] getRealStackTrack(StackTraceElement[] stackTrace, String ignorePackage) {
        if(null == stackTrace || TextUtils.isEmpty(ignorePackage)){
            return null;
        }
        int ignoreDepth = 0;
        String className = "";
        for(int i = stackTrace.length-1;i>0;i--){
            className = stackTrace[i].getClassName();
            if(!TextUtils.isEmpty(className) && className.startsWith(ignorePackage)){
                ignoreDepth = i +1;
                break;
            }
        }
        int resultDepth = stackTrace.length - ignoreDepth;
        StackTraceElement[] result = new StackTraceElement[resultDepth];
        System.arraycopy(stackTrace,ignoreDepth,result,0,resultDepth);
        return result;
    }

    /***
     * 截取指定长度的堆栈信息
     * @param callStack
     * @param maxDepth
     * @return
     */
    private static StackTraceElement[] cropStackTrace(StackTraceElement[] callStack, int maxDepth) {
        int resultLen;
        if(null != callStack && callStack.length >= maxDepth){
            resultLen = callStack.length;
        }else{
            resultLen = maxDepth;
        }
        StackTraceElement[] resultArray = new StackTraceElement[resultLen];
        System.arraycopy(callStack,0,resultArray,0,resultLen);
        return resultArray;
    }
}
