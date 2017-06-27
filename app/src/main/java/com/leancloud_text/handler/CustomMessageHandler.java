package com.leancloud_text.handler;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.AVIMMessageHandler;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.leancloud_text.MyLeanCloudApp;
import com.leancloud_text.obj.LogU;

/**
 * Created by kevinh on 2017/6/22.
 */

public class CustomMessageHandler extends AVIMMessageHandler {
    //接收到消息后的处理逻辑
    @Override
    public void onMessage(AVIMMessage message, AVIMConversation conversation, AVIMClient client){
        if(message instanceof AVIMTextMessage){

            LogU.i("默认的消息处理逻辑",((AVIMTextMessage)message).getText()+ MyLeanCloudApp.isInMainThread());
        }
    }

    public void onMessageReceipt(AVIMMessage message,AVIMConversation conversation,AVIMClient client){
        //此处就是对方收到消息以后的回调
    }
}