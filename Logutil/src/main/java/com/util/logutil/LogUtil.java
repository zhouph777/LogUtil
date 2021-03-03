package com.util.logutil;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

/**
 * 日志初始类
 * create by zhouph
 * date 2021/2/26
 */
public class LogUtil {
    private  String LogPrintSwitch;   //是否开启log输出
    private  String LogTAG = "LogUtil"; //log输出TAG
    private boolean SaveLogSwitch = false;  //是否保存log
    private String fileName = null;
    private String fileContent = null;

    /**
     * 单例模式（静态内部类）
     */
    private static class LazyHolder {
        private static final LogUtil INSTANCE = new LogUtil();
    }
    private LogUtil (){}
    public static final LogUtil getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * 初始话LogUtil
     * @param view 控制日志开启得View
     * @param SaveLogSwitch 是否保存日志
     */
    public void initLogUtil(View view, boolean SaveLogSwitch,String LogTAG,String fileName,String fileContent) {
        setPrintLog(view);
        this.SaveLogSwitch = SaveLogSwitch;
        this.LogTAG = LogTAG;
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    public void i(String msg) {
        if (LogPrintSwitch.equals("true")) {
            Log.i(LogTAG, msg);
        }
    }

    public void i(Context context,String msg) {
        if (LogPrintSwitch.equals("true")) {
            Log.i(LogTAG, msg);
            saveLog(context,fileName,fileContent);
        }
    }

    public  void d(String msg) {
        if (LogPrintSwitch.equals("true")) {
            Log.d(LogTAG, msg);
        }
    }

    public  void e(String msg) {
        if (LogPrintSwitch.equals("true")) {
            Log.e(LogTAG, msg);
        }
    }

    public  void w(String msg) {
        if (LogPrintSwitch.equals("true")) {
            Log.w(LogTAG, msg);
        }
    }


    public  void v(String msg) {
        if (LogPrintSwitch.equals("true")) {
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

    public void setPrintLog(View view) {
        view.setOnClickListener(v -> continuousClick(COUNTS, DURATION));
    }

    /**
     * 首先我们点击的时候都将数组向左移动一位，将时间赋值给最后一位，从上面的代码中我们可以看出当我们点击了四次，那么最后一位就已经被移到了第一位，然后我们比较时间：
     * <p>
     * mHits[0] >= (SystemClock.uptimeMillis() - DURATION)
     * <p>
     * 如果是在我们规定的时间内，那么就生效，执行我们所要的操作。
     *
     * @param count 点击次数
     * @param time  点击总时长
     */
    private  void continuousClick(int count, long time) {
        //每次点击时，数组向前移动一位
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        //为数组最后一位赋值 系统启动时间
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        if (mHits[0] >= (SystemClock.uptimeMillis() - DURATION)) {
            mHits = new long[COUNTS];//重新初始化数组

            System.setProperty("persist.danny.log", "true");
            LogPrintSwitch = System.getProperty("persist.danny.log");//将开关设置为系统属性
            Log.i("LogUtil", "Log打开");

        }
    }

    private void saveLog(Context context, String fileName, String fileContent) {
        if (SaveLogSwitch) {
            SaveFile saveFile = new SaveFile();
            saveFile.saveInFilesDir(context, fileName, fileContent);
        }
    }

}
