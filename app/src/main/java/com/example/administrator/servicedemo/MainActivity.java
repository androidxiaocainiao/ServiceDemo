package com.example.administrator.servicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    /*
    普通式
     */
    private String path = "http://image.baidu.com/search/down?tn=download&ipn=dwnl&word=download&ie=utf8&fr=result&url=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F78%2F54%2F98a58PICCQf_1024.jpg&thumburl=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D1783596492%2C2888832223%26fm%3D27%26gp%3D0.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view) {
        //启动服务
        Intent intent = new Intent(this, DownedService.class);
        startService(intent);
    }

    public void download(View view) {
        //现在开始
        DownedManager manager = DownedService.getInstance();
        manager.addRask(path);
    }
}
