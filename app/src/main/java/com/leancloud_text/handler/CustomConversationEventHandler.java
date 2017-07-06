package com.leancloud_text.handler;

import android.widget.Toast;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMConversationEventHandler;
import com.leancloud_text.obj.LogU;

import java.util.List;

/**
 * Created by kevinh on 2017/6/14.
 */

public class CustomConversationEventHandler extends AVIMConversationEventHandler {

    @Override
    public void onMemberLeft(AVIMClient client, AVIMConversation conversation, List<String> members,String kickedBy) {
        // 有其他成员离开时，执行此处逻辑
    }

    @Override
    public void onMemberJoined(AVIMClient client, AVIMConversation conversation,
                               List<String> members, String invitedBy) {
        // 手机屏幕上会显示一小段文字：Tom 加入到 551260efe4b01608686c3e0f ；操作者为：Tom

        //Toast.makeText(AVOSCloud.applicationContext,members + "加入到" + conversation.getConversationId() + "；操作者为： "+ invitedBy, Toast.LENGTH_SHORT).show();
        LogU.i("有人聯絡上了", conversation.getConversationId()+"_加入到_ 未讀："+ conversation.getUnreadMessagesCount());


    }

    @Override
    public void onUnreadMessagesCountUpdated(AVIMClient client, AVIMConversation conversation) {
        super.onUnreadMessagesCountUpdated(client, conversation);
        LogU.i("未讀數",""+conversation.getUnreadMessagesCount());
    }

    @Override
    public void onKicked(AVIMClient client, AVIMConversation conversation, String kickedBy) {
        // 当前 ClientId(Bob) 被踢出对话，执行此处逻辑
    }

    @Override
    public void onInvited(AVIMClient client, AVIMConversation conversation, String invitedBy) {
        // 当前 ClientId(Bob) 被邀请到对话，执行此处逻辑
    }
}