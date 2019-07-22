package com.jinlanmeng.mytencentlivebroadcast.utils;

import android.util.Log;

import com.jinlanmeng.mytencentlivebroadcast.BuildConfig;


/**
 * ssq
 * log工具类
 */
public class LogUtil {

    public static final String DEFAULT_TAG = "打印信息--------";
    public static boolean showLog = BuildConfig.DEBUG;

    public static void v(String logText) {
        if (showLog) {
            Log.v(DEFAULT_TAG, String.valueOf(logText));
        }
    }

    public static void v(String TAG, String logText) {
        if (showLog) {
            Log.v(TAG, logText);
        }
    }

    public static void d(String logText) {
        if (showLog) {
            Log.d(DEFAULT_TAG, String.valueOf(logText));
        }
    }

    public static void e(String logText) {
        if (showLog) {
            Log.e(DEFAULT_TAG, String.valueOf(logText));
        }
    }

    public static void d(String TAG, String logText) {
        if (showLog) {
            Log.d(TAG, logText);
        }
    }

    public static void i(String logText) {
        if (showLog) {
            Log.i(DEFAULT_TAG, logText);
        }
    }

    public static void i(String TAG, String logText) {
        if (showLog) {
            Log.i(TAG, logText);
        }
    }

    public static void w(String TAG, String logText) {
        if (showLog) {
            Log.w(TAG, logText);
        }
    }

    public static void w(String logText) {
        if (showLog) {
            Log.w(DEFAULT_TAG, String.valueOf(logText));
        }
    }

    public static void e(String TAG, String logText) {
        if (showLog) {
            Log.e(TAG, logText);
        }
    }

    public static void d(Class<?> c, String logText) {
        if (showLog) {
            Log.d(DEFAULT_TAG, "[" + c.getSimpleName() + "]" + logText);
        }
    }

    public static void d(Object c, String logText) {
        if (showLog) {
            Log.d(DEFAULT_TAG, "[" + c.getClass().getSimpleName() + "]" + logText);
        }
    }

    /**
     * 超长log
     *
     * @param msg
     */
    public static void ee(String tag, String msg) {
//        我们采取分段打印日志的方法：当长度超过4000时，我们就来分段截取打印
        //剩余的字符串长度如果大于4000
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0)
            return;

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize) {// 长度小于等于限制直接打印
            Log.e(tag, msg);
        } else {
            while (msg.length() > segmentSize) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
            Log.e(tag, msg);// 打印剩余日志
        }
    }
}
