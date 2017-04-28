package com.leancloud_text;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by kevinh on 2017/4/28.
 */
public class MyLeanCloudApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"gSrWhh8chYwzmyogpGI88iLf-gzGzoHsz","g9PdXFP1ctMeO0UiI5ph30VT");
        // 放在 SDK 初始化语句 AVOSCloud.initialize() 后面，只需要调用一次即可
        AVOSCloud.setDebugLogEnabled(true);

    }
}