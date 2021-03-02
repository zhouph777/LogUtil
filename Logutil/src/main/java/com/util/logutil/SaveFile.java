package com.util.logutil;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SaveFile {

    /**
     * 创建一个线程池
     * 2个线程
     * 最大3个线程
     * 空闲线程等待时间为0
     * Unit单位
     * 队列
     */
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            2, 3, 0,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(2));

    /**
     * 将文件保存在应用专属存储区域
     *
     * @param content     上下文
     * @param fileName    文件名
     * @param fileContent 要保存得内容
     */
    public void saveInFilesDir(Context content, String fileName, String fileContent) {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                FileOutputStream fos = null;
                try {
                    fos = content.openFileOutput(fileName, Context.MODE_PRIVATE);
                    fos.write(fileContent.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


}
