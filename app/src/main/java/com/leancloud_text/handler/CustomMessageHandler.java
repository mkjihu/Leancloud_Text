package com.leancloud_text.handler;

import java.util.Date;

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

            //-APP已AVIMMessageManager.unregisterMessageHandler(AVIMTypedMessage.class, MyLeanCloudApp.getMessageHandler());
            //-將訊息存入DB
            LogU.i("對話ID",conversation.getConversationId());
            for (String string : conversation.getMembers()) {
                LogU.i("成员列表",string);
            }
            LogU.i("發話對象ID",message.getFrom());
            LogU.i("聊天室名字",conversation.getName());
            LogU.i("未讀數",conversation.getUnreadMessagesCount()+"");
            LogU.i("內容",((AVIMTextMessage) message).getText());
            LogU.i("內容類型",((AVIMTextMessage) message).getMessageType()+"");
            LogU.i("更新時間",new Date().toString()+"___"+conversation.getUpdatedAt());

            //LogU.i("是否讀取",false);

        }
    }


    public void onMessageReceipt(AVIMMessage message,AVIMConversation conversation,AVIMClient client){
        //此处就是对方收到消息以后的回调
    }




}