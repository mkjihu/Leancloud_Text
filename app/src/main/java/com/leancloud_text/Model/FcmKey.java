package com.leancloud_text.Model;

/**
 * Created by kevinh on 2017/6/13.
 */

public class FcmKey {
    private String objectId;
    private String fcm_tokey;

    public FcmKey(String objectId, String fcm_tokey) {
        this.objectId = objectId;
        this.fcm_tokey = fcm_tokey;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getFcm_tokey() {
        return fcm_tokey;
    }

    public void setFcm_tokey(String fcm_tokey) {
        this.fcm_tokey = fcm_tokey;
    }

}
