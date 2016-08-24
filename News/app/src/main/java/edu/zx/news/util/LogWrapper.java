package edu.zx.news.util;

import android.util.Log;

/**
 * Created by BAIBINGSHAN0122 on 2016/7/21.
 */
public class LogWrapper {
    //对于打印的封装类
    static boolean isDebug = true;

    public static void e(String tag, String msg) {
        if (isDebug) ;
        Log.e(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug) ;
        Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (isDebug) ;
        Log.i(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (isDebug) ;
        Log.w(tag, msg);
    }
}
