package com.leancloud_text;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.multidex.MultiDexApplication;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.PushService;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMMessageManager;
import com.avos.avoscloud.im.v2.AVIMTypedMessage;
import com.leancloud_text.Activity.MainActivity;
import com.leancloud_text.Network.HttpApiClient;
import com.leancloud_text.SQL.DaoMaster;
import com.leancloud_text.SQL.DaoSession;
import com.leancloud_text.Util.LeanchatUser;
import com.leancloud_text.handler.ClientEventHandler;
import com.leancloud_text.handler.CustomConversationEventHandler;
import com.leancloud_text.handler.CustomMessageHandler;
import com.leancloud_text.handler.MessageHandler;

/**
 * Created by kevinh on 2017/4/28.
 */

//-配置方法数超过 64K 的应用 替换了 Application 类，请按如下方式对其进行更改以扩展 MultiDexApplication
public class MyLeanCloudApp  extends MultiDexApplication {
    private static Context context;
    private static MyLeanCloudApp instance;

    //---SQL
    private DaoSession daoSession;
    private DaoMaster.DevOpenHelper helper;



    public static final String USERNAME = "username";
    public static final String AVATAR = "avatar";
    public static final String LOCATION = "location";
    public static final String INSTALLATION = "installation";


    public static final String APP_ID = "gSrWhh8chYwzmyogpGI88iLf-gzGzoHsz";
    public static final String APP_KEY = "g9PdXFP1ctMeO0UiI5ph30VT";




    @Override
    public void onCreate() {
        super.onCreate();
        MyLeanCloudApp.context = getApplicationContext();
        HttpApiClient.initialize();
        instance = this;


        //AVObject.registerSubclass(FcmKey2.class);//使用自定義
        LeanchatUser.alwaysUseSubUserClass(LeanchatUser.class);//使用自定義

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,APP_ID,APP_KEY);
        // 放在 SDK 初始化语句 AVOSCloud.initialize() 后面，只需要调用一次即可
        //AVOSCloud.setLastModifyEnabled(true);// 节省流量
        AVOSCloud.setDebugLogEnabled(true);


        /**註冊消息接受邏輯*/
        //注册默认的消息处理逻辑
        //AVIMMessageManager.registerDefaultMessageHandler(new CustomMessageHandler());
        //自訂消息處理
        AVIMMessageManager.registerMessageHandler(AVIMTypedMessage.class,new MessageHandler());
        // 默认设置为离线消息仅推送数量
        AVIMClient.setOfflineMessagePush(true);
        //处理网络断开事件
        AVIMClient.setClientEventHandler(new ClientEventHandler());
        AVIMMessageManager.setConversationEventHandler(new CustomConversationEventHandler());


        // 设置默认打开的 Activity
        PushService.setDefaultPushCallback(this, MainActivity.class);
    }

    public static Context getAppContext() {
        return MyLeanCloudApp.context;
    }

    public static MyLeanCloudApp getInstance() {
        return instance;
    }

    public DaoSession getDaoSession()
    {
        if (daoSession==null) {
            initDaoSession();
        }
        return daoSession;
    }

    /**初始化DB*/
    public void initDaoSession()
    {
        /**宣告資料庫*/
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        helper = new DaoMaster.DevOpenHelper(this, "MyLean-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

    }



    /**
     * 判断网络是否可用
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