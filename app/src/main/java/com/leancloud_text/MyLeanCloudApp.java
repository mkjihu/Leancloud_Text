package com.leancloud_text;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.avos.avoscloud.AVOSCloud;

import cn.leancloud.chatkit.LCChatKit;

import static android.provider.UserDictionary.Words.APP_ID;

/**
 * Created by kevinh on 2017/4/28.
 */
public class MyLeanCloudApp extends Application {
    private static Context context;


    private final String APP_ID = "gSrWhh8chYwzmyogpGI88iLf-gzGzoHsz";
    private final String APP_KEY = "g9PdXFP1ctMeO0UiI5ph30VT";
    @Override
    public void onCreate() {
        super.onCreate();
        MyLeanCloudApp.context = getApplicationContext();
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,APP_ID,APP_KEY);
        // 放在 SDK 初始化语句 AVOSCloud.initialize() 后面，只需要调用一次即可
        AVOSCloud.setDebugLogEnabled(true);

        // 节省流量
        //AVOSCloud.setLastModifyEnabled(true);



        // 关于 CustomUserProvider 可以参看后面的文档
        LCChatKit.getInstance().setProfileProvider(CustomUserProvider.getInstance());
        LCChatKit.getInstance().init(getApplicationContext(), APP_ID, APP_KEY);


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