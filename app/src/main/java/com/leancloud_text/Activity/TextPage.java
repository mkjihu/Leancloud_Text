package com.leancloud_text.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.androidquery.AQuery;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.AVIMMessageManager;
import com.avos.avoscloud.im.v2.AVIMTypedMessage;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.leancloud_text.MyLeanCloudApp;
import com.leancloud_text.R;
import com.leancloud_text.Util.LeanchatUser;
import com.leancloud_text.handler.MessageHandler;
import com.leancloud_text.obj.LogU;

import java.util.Arrays;

public class TextPage extends AppCompatActivity {
    public AQuery aq;
    public EditText editText;
    public AVIMConversation athis;
    public String ConversationID;//-該對話識別碼
    //public MessageHandler messageHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_page);
        aq = new AQuery(this);
        editText = (EditText)findViewById(R.id.editText);
        // 测试 SDK 是否正常工作的代码
        /*
        AVObject testObject = new AVObject("TestObject");
        testObject.put("words","Hello World!");
        testObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if(e == null){
                    Log.i("saved","success!");
                }
            }
        });
        */
        //messageHandler = new MessageHandler();

        LogU.i("本基帳號",LeanchatUser.getCurrentUser().getObjectId()+"!"+LeanchatUser.getCurrentUser().getClassName());

        aq.id(R.id.button).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LeanchatUser.getCurrentUser() != null)
                {
                    AVIMClient.getInstance(LeanchatUser.getCurrentUser().getObjectId())
                            /**
                             * 创建或查询一个已有 conversation
                             *
                             * @param members 对话的初始成员列表。在对话创建成功后，这些成员会收到和邀请加入对话一样的相应通知。
                             * @param name 对话的名字 主要是用于标记对话，让用户更好地识别对话。
                             * @param attributes 对话的额外属性
                             * @param isTransient 是否是暂态对话
                             * @param isUnique 如果当前已经有相同成员的对话存在则返回该对话，否则会创建新的对话。该值默认为 false。
                             *                 为 false 时，则一直为创建新的对话
                             *                 为 true 时，则先查询，如果已有符合条件的对话，则返回已有的，否则，创建新的并返回
                             *                 为 true 时，仅 members 为有效查询条件
                             * @param callback
                             */
                            .createConversation(Arrays.asList("5941f988fe88c2006a533044"), "測試對話1", null, false, true, new AVIMConversationCreatedCallback() {
                                @Override
                                public void done(AVIMConversation avimConversation, AVIMException e) {

                                    athis = avimConversation;//--先取得該對話
                                    ConversationID = avimConversation.getConversationId();//-取得對話ID
                                    AVIMTextMessage msg = new AVIMTextMessage();
                                    msg.setText("連線成功");
                                    athis.sendMessage(msg, new AVIMConversationCallback() {
                                        @Override
                                        public void done(AVIMException e) {
                                            if (e == null) {
                                                LogU.i("測試對話1", "发送成功！");
                                            }
                                        }
                                    });

                                }
                            });


                }


            }
        });
        aq.id(R.id.button5).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVIMTextMessage msg = new AVIMTextMessage();
                msg.setText(editText.getText().toString());
                athis.sendMessage(msg, new AVIMConversationCallback() {
                    @Override
                    public void done(AVIMException e) {
                        if (e == null) {
                            LogU.i("測試對話1", "发送成功！");
                        }
                    }
                });
            }
        });

        aq.id(R.id.button6).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVIMClient.getInstance(LeanchatUser.getCurrentUser().getObjectId()).close(new AVIMClientCallback() {
                    @Override
                    public void done(AVIMClient avimClient, AVIMException e) {
                        if (e == null) {
                            LogU.i("關閉通道", "成功！");
                        }
                        else
                        {
                            LogU.i("關閉通道", e.getMessage());
                        }
                    }
                });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onResume() {
        super.onResume();
        LogU.i("onResume","onResume畫面");
        AVIMMessageManager.registerMessageHandler(AVIMTypedMessage.class,MyLeanCloudApp.getMessageHandler());
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogU.i("暫停畫面onPause","暫停畫面");
        AVIMMessageManager.unregisterMessageHandler(AVIMTypedMessage.class, MyLeanCloudApp.getMessageHandler());
    }
    @Override
    protected void onStop() {
        super.onStop();
        LogU.i("暫停畫面onStop","暫停畫面");
        //AVIMMessageManager.registerMessageHandler(AVIMTypedMessage.class,MyLeanCloudApp.getMessageHandler());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
