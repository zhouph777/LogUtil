package com.util.logutil;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;

/**
 * 日志初始类
 * create by zhouph
 * date 2021/2/26
 */
public class LogUtil {
    private static String isPrintLog;   //是否开启log输出
    private static String LogTAG = "LogUtil"; //log输出TAG

    public static void i(String msg) {
        if (isPrintLog.equals("true")) {
            Log.i(LogTAG, msg);
        }
    }

    public static void d(String msg) {
        if (isPrintLog.equals("true")) {
            Log.d(LogTAG, msg);
        }
    }

    public static void e(String msg) {
        if (isPrintLog.equals("true")) {
            Log.e(LogTAG, msg);
        }
    }

    public static void w(String msg) {
        if (isPrintLog.equals("true")) {
            Log.w(LogTAG, msg);
        }
    }


    public static void v(String msg) {
        if (isPrintLog.equals("true")) {
            Log.v(LogTAG, msg);
        }
    }


    /**
     * 连续点击view五次则开启log打印
     *
     * @param view 控制日志开关的view
     */
    private final static int COUNTS = 5;// 点击次数
    private final static long DURATION = 1000;// 规定有效时间
    private static long[] mHits = new long[COUNTS];

    public static void setPrintLog(View view) {
        view.setOnClickListener(v -> continuousClick(COUNTS, DURATION));
    }

    /**
     * 首先我们点击的时候都将数组向左移动一位，将时间赋值给最后一位，从上面的代码中我们可以看出当我们点击了四次，那么最后一位就已经被移到了第一位，然后我们比较时间：
     *
     * mHits[0] >= (SystemClock.uptimeMillis() - DURATION)
     *
     * 如果是在我们规定的时间内，那么就生效，执行我们所要的操作。
     *
     * @param count 点击次数
     * @param time  点击总时长
     */
    private static void continuousClick(int count, long time) {
        //每次点击时，数组向前移动一位
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        //为数组最后一位赋值 系统启动时间
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        if (mHits[0] >= (SystemClock.uptimeMillis() - DURATION)) {
            mHits = new long[COUNTS];//重新初始化数组

            System.setProperty("persist.danny.log", "true");
            isPrintLog = System.getProperty("persist.danny.log");//将开关设置为系统属性
            Log.i("LogUtil","Log打开");


        }
    }

}
