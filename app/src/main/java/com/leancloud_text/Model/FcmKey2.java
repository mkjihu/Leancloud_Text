package com.leancloud_text.Model;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Created by kevinh on 2017/6/13.
 */
@AVClassName("FcmKey")
public class FcmKey2 extends AVObject{


    public void setObjectId(String objectId) {
        put("objectId",objectId);
    }


    public void setFcm_tokey(String fcm_tokey) {
        put("fcm_tokey",fcm_tokey);
    }

}
