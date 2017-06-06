package com.leancloud_text;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.PushService;
import com.avos.avoscloud.SaveCallback;

import cn.leancloud.chatkit.LCChatKit;

/**
 * Created by kevinh on 2017/4/28.
 */
public class MyLeanCloudApp extends Application {
    private static Context context;

    public static final String USERNAME = "username";
    public static final String AVATAR = "avatar";
    public static final String LOCATION = "location";
    public static final String INSTALLATION = "installation";


    private final String APP_ID = "gSrWhh8chYwzmyogpGI88iLf-gzGzoHsz";
    private final String APP_KEY = "g9PdXFP1ctMeO0UiI5ph30VT";
    @Override
    public void onCreate() {
        super.onCreate();
        MyLeanCloudApp.context = getApplicationContext();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,APP_ID,APP_KEY);
        // 放在 SDK 初始化语句 AVOSCloud.initialize() 后面，只需要调用一次即可
        //AVOSCloud.setLastModifyEnabled(true);// 节省流量
        AVOSCloud.setDebugLogEnabled(true);




        // 关于 CustomUserProvider 可以参看后面的文档


        PushService.setDefaultPushCallback(this, MainActivity.class);
    }



    /**
     * 判断网络是否可用
     * @param context
     */
    public static Boolean isNetworkReachable() {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivity) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }
}