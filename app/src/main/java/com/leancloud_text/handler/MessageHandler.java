package com.leancloud_text.handler;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMReservedMessageType;
import com.avos.avoscloud.im.v2.AVIMTypedMessage;
import com.avos.avoscloud.im.v2.AVIMTypedMessageHandler;
import com.avos.avoscloud.im.v2.messages.AVIMAudioMessage;
import com.avos.avoscloud.im.v2.messages.AVIMFileMessage;
import com.avos.avoscloud.im.v2.messages.AVIMImageMessage;
import com.avos.avoscloud.im.v2.messages.AVIMLocationMessage;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.avos.avoscloud.im.v2.messages.AVIMVideoMessage;
import com.leancloud_text.obj.LogU;

import static com.avos.avoscloud.im.v2.AVIMReservedMessageType.*;

/**
 * Created by kevinh on 2017/6/14.
 */

public class MessageHandler extends AVIMTypedMessageHandler<AVIMTypedMessage> {
    @Override
    public void onMessage(AVIMTypedMessage message, AVIMConversation conversation, AVIMClient client) {
        // 请按自己需求改写

        switch(AVIMReservedMessageType.getAVIMReservedMessageType(message.getMessageType())) {
            case TextMessageType:
                AVIMTextMessage textMsg = (AVIMTextMessage)message;
                LogU.i("收到文本消息:","" + textMsg.getText() + ", msgId:" + textMsg.getMessageId());
                break;

            case FileMessageType:
                AVIMFileMessage fileMsg = (AVIMFileMessage)message;
                LogU.i("收到文件消息。msgId=",""  + fileMsg.getMessageId() + ", url=" + fileMsg.getFileUrl() + ", size=" + fileMsg.getSize());
                break;

            case ImageMessageType:
                AVIMImageMessage imageMsg = (AVIMImageMessage)message;
                LogU.i("收到图片消息。msgId=",""  + imageMsg.getMessageId() + ", url=" + imageMsg.getFileUrl() + ", width=" + imageMsg.getWidth() + ", height=" + imageMsg.getHeight());
                break;

            case AudioMessageType:
                AVIMAudioMessage audioMsg = (AVIMAudioMessage)message;
                LogU.i("收到音频消息。msgId=",""  + audioMsg.getMessageId() + ", url=" + audioMsg.getFileUrl() + ", duration=" + audioMsg.getDuration());
                break;

            case VideoMessageType:
                AVIMVideoMessage videoMsg = (AVIMVideoMessage)message;
                LogU.i("收到视频消息。msgId=",""  + videoMsg.getMessageId() + ", url=" + videoMsg.getFileUrl() + ", duration=" + videoMsg.getDuration());
                break;

            case LocationMessageType:
                AVIMLocationMessage locMsg = (AVIMLocationMessage)message;
                LogU.i("收到位置消息。msgId=",""  + locMsg.getMessageId() + ", latitude=" + locMsg.getLocation().getLatitude() + ", longitude=" + locMsg.getLocation().getLongitude());
                break;
        }
    }

    @Override
    public void onMessageReceipt(AVIMTypedMessage message, AVIMConversation conversation, AVIMClient client) {
        // 请加入你自己需要的逻辑...
        //此处就是对方收到消息以后的回调
        LogU.i("對方已收到","對方已收到");
        super.onMessageReceipt(message, conversation, client);
    }

}
