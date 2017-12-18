package com.example.administrator.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/12/13 0013.
 */

public class DownedService extends Service {
    private static DownedManager manager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        manager = new DownedManager(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (manager == null) {
            manager = new DownedManager(this);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    //创建一个方法 ，用来回去manager对象
    public static DownedManager getInstance() {
        return manager;
    }
}
