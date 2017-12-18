package com.example.bangdingshi;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/12/13 0013.
 */

public class MyService extends Service {
    private int num = 0;

    public MyService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        createThread();
        return new MyBinder();
    }

    private void createThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long endTime = System.currentTimeMillis();
                while (true) {
                    if (System.currentTimeMillis() - endTime > 500) {
                        endTime = System.currentTimeMillis();
                        num++;
                    }
                }
            }
        });
        thread.start();
    }

    class MyBinder extends Binder {
        public int getNum() {
            return num;
        }
    }
}
