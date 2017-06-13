package com.leancloud_text.Network;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kevinh on 2017/6/13.
 */

public class Shared {

    //---記錄FCMKey
    public static void SetFCMKey(Context context, String FCMKey) {
        SharedPreferences settings = context.getSharedPreferences("leancloud_text", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("FCMKey", FCMKey);
        editor.commit();
    }

    //--取得FCMKey
    public static String GetFCMKey(Context context) {
        SharedPreferences settings = context.getSharedPreferences("leancloud_text", 0);
        String FCMKey = settings.getString("FCMKey", "0");
        return FCMKey;
    }



}
