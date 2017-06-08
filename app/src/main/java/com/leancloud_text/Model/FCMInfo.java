package com.leancloud_text.Model;

/**
 * Created by kevinh on 2017/6/8.
 */

public class FCMInfo {
    private String to;
    private notification notification;
    private data data;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public com.leancloud_text.Model.notification getNotification() {
        return notification;
    }

    public void setNotification(com.leancloud_text.Model.notification notification) {
        this.notification = notification;
    }

    public com.leancloud_text.Model.data getData() {
        return data;
    }

    public void setData(com.leancloud_text.Model.data data) {
        this.data = data;
    }
}
