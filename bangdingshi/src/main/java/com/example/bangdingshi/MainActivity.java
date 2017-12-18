package com.example.bangdingshi;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    protected ServiceConnection connection = new ServiceConnection() {
        //componentName:链接值
        //service:绑定之后返回的IBinder对象
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MyService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private MyService.MyBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //用来绑定服务
    public void onBind(View view) {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, connection, Service.BIND_AUTO_CREATE);
    }

    //获取服务中的数据
    public void loadData(View view) {
        int num = binder.getNum();
        Toast.makeText(this, "获取到的数据为： " + num, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
