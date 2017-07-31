package com.leancloud_text.handler;

import android.content.Context;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMReservedMessageType;
import com.avos.avoscloud.im.v2.AVIMTypedMessage;
import com.avos.avoscloud.im.v2.AVIMTypedMessageHandler;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.messages.AVIMAudioMessage;
import com.avos.avoscloud.im.v2.messages.AVIMFileMessage;
import com.avos.avoscloud.im.v2.messages.AVIMImageMessage;
import com.avos.avoscloud.im.v2.messages.AVIMLocationMessage;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.avos.avoscloud.im.v2.messages.AVIMVideoMessage;
import com.google.gson.Gson;
import com.leancloud_text.Model.User;
import com.leancloud_text.MyLeanCloudApp;
import com.leancloud_text.Network.HttpApiClient;
import com.leancloud_text.SQL.Passers;
import com.leancloud_text.SQL.PassersDao;
import com.leancloud_text.obj.LogU;

import org.reactivestreams.Publisher;

import java.util.Date;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

import static com.avos.avoscloud.im.v2.AVIMReservedMessageType.*;

/**
 * Created by kevinh on 2017/6/14.
 */

public class MessageHandler extends AVIMTypedMessageHandler<AVIMTypedMessage> {

    private Context context;
    public MessageHandler(Context context)
    {
        this.context = context;
    }
    public MessageHandler() {}

    //接收到消息后的处理逻辑
    @Override
    public void onMessage(AVIMTypedMessage message, AVIMConversation conversation, AVIMClient client) {
        // 请按自己需求改写
        LogU.i("誰?"+ MyLeanCloudApp.isInMainThread(),conversation.getConversationId()+"_"+conversation.getName()+ MyLeanCloudApp.isInMainThread());


        LogU.i("A對話ID",conversation.getConversationId());
        for (String string : conversation.getMembers()) {
            LogU.i("A成员列表",string);
        }
        LogU.i("A發話對象ID",message.getFrom());
        LogU.i("A聊天室名字",conversation.getName());
        LogU.i("A未讀數",conversation.getUnreadMessagesCount()+"");
        LogU.i("A內容",((AVIMTextMessage) message).getText());
        LogU.i("A內容類型",((AVIMTextMessage) message).getMessageType()+"");
        LogU.i("A更新時間",new Date().toString()+"___"+conversation.getUpdatedAt());



        //


        //Uac(message.getFrom());

        //取得對話後,先存入DB
        //以對話ID為PK


        if(AVUser.getCurrentUser().getObjectId() == null) {
            //-如果ID不存在表示已登出
            client.close((AVIMClientCallback) null);
        }
        else if(!client.getClientId().equals(AVUser.getCurrentUser().getObjectId())) {
            //--已登出且是別人的一樣登出
            client.close((AVIMClientCallback)null);
        }
        else if(!message.getFrom().equals(client.getClientId())){
            //-获取消息的发送者 ID 不等於 登入ID時
            String fromClientId = message.getFrom();
            //不是自己发言时 --增加未读数
            LogU.i("不是自己发言时，對方ID",fromClientId+"_增加未读数");
            CacVie(message);
        }
        else
        {
            LogU.i("剩餘","自己发言时");

        }

    }

    @Override
    public void onMessageReceipt(AVIMTypedMessage message, AVIMConversation conversation, AVIMClient client) {
        // 请加入你自己需要的逻辑...
        //此处就是对方收到消息以后的回调
        LogU.i("對方已收到","對方已收到");
        super.onMessageReceipt(message, conversation, client);
    }



    public void CacVie(AVIMTypedMessage message)
    {


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




    //---額外控制
    public void Uac(String UserId)
    {

        Flowable.just(UserId)
                .subscribeOn(Schedulers.io())//--跑在線程背後--只讀一次
                .flatMap(new Function<String,  Flowable<Passers>>() {
                    @Override
                    public Flowable<Passers> apply(@NonNull String s) throws Exception {
                        PassersDao passersDao = MyLeanCloudApp.getInstance().getDaoSession().getPassersDao();
                        Passers passers = passersDao.queryBuilder().where(PassersDao.Properties.UserObjectId.eq(s)).unique();
                        if (passers ==null)
                        {
                            return NiGetUser(s);
                        }
                        //-如果有資料就直接傳
                        return  Flowable.just(passers);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//--結果在主線程中顯示
                .unsubscribeOn(Schedulers.io())//允許取消訂閱
                .subscribeWith(new DisposableSubscriber<Passers>() {
                    @Override
                    public void onNext(Passers passers) {
                        LogU.i("完成", "完成");
                        //--取得User資料




                    }
                    @Override
                    public void onError(Throwable e) {
                        LogU.i("完成GG", e.getMessage());
                    }
                    @Override
                    public void onComplete() {}
                });

    }


    //--網路取資料
    public Flowable<Passers> NiGetUser(String UserId)
    {
        return  HttpApiClient.getInstance().QrUser(UserId)
                .subscribeOn(Schedulers.io())//--跑在線程背後--只讀一次
                .map(new Function<String, Passers>() {
                    @Override
                    public Passers apply(@NonNull String s) throws Exception {
                        //--取得對方資料
                        User user = new Gson().fromJson(s,User.class);

                        Passers passers = new Passers();
                        passers.setId(null);
                        passers.setUserObjectId(user.getObjectId());
                        passers.setIsfollow(false);

                        if (user.getNickname()==null){
                            passers.setName(user.getUsername());
                        }
                        else{
                            passers.setName(user.getNickname());
                        }
                        passers.setUserImage(user.getImage().getUrl());

                        return passers;
                    }
                });
    }

}
