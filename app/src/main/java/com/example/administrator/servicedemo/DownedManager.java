package com.example.administrator.servicedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/12/13 0013.
 */

public class DownedManager {
    private Context context;
    private ThreadPoolExecutor pool;

    public DownedManager(Context context) {
        this.context = context;
        //创建线程池
        init();
    }

    private void init() {
        pool = new ThreadPoolExecutor(
                5, 5, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3000)
        );
    }

    //执行请求任务
    public void addRask(String path) {
        //执行线程池
        pool.execute(new MyThread(path));
    }

    class MyThread extends Thread {
        public String path;

        public MyThread(String path) {
            this.path = path;
        }

        @Override
        public void run() {
            super.run();
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.setDoOutput(true);
                //判断网络是否连接成功
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    long maxSize = conn.getContentLength();
                    InputStream ins = conn.getInputStream();
                    byte[] by = new byte[24];
                    int len = 0;
                    Log.i("tag", "-------" + maxSize);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int readToatal = 0;
                    while ((len = ins.read(by)) != -1) {
                        bos.write(by, 0, len);
                        readToatal += len;
                        double dd = readToatal / maxSize * 1.0f;
                        DecimalFormat format = new DecimalFormat("##.00%");
                        String value = format.format(dd);
                        Log.i("tag", "======下载完成了=====" + value);
                    }
                    byte[] bytes = bos.toByteArray();
                    bos.close();
                    ins.close();
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
