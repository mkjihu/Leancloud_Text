package com.leancloud_text;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.jakewharton.rxbinding2.view.RxView;
import com.leancloud_text.obj.DialogBox;
import com.leancloud_text.obj.ToastUnity;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.leancloud.chatkit.LCChatKit;
import io.reactivex.functions.Consumer;

public class RegisterPage extends AppCompatActivity {

    public TextInputLayout til1,til2;
    public TextInputEditText ed1;
    public EditText ed2;
    private DialogBox dialogBox;

    private Button bt1,bt2,bt3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        fid();
        vitin();
        //EventBus.getDefault().register(this);//注册



    }

    private void vitin() {
        til1.setHint("帳號");
        til2.setHint("密碼");
        //设置可以计数
        til1.setCounterEnabled(true);
        til2.setCounterEnabled(true);

        //计数的最大值
        til1.setCounterMaxLength(10);
        til2.setCounterMaxLength(5);

        RxView.clicks(bt1)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object arg0) throws Exception {
                        attemptRegister();
                    }
                });
        RxView.clicks(bt2)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object arg0) throws Exception {
                        /*
                        AVUser currentUser = AVUser.getCurrentUser();
                        if (currentUser!=null)
                            Log.i("成功",currentUser.getObjectId());
                            Log.i("成功",currentUser.getSessionToken());
                            ToastUnity.ShowTost(RegisterPage.this,currentUser.getObjectId());
                            sendMessageToJerryFromTom(currentUser.getObjectId());
                        */

                    }
                });
        RxView.clicks(bt3)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object arg0) throws Exception {
                        AVUser currentUser = AVUser.getCurrentUser();
                        Log.i("1",currentUser.getObjectId());
                        //Log.i("2",idid);
                        AVIMConversation conv = AVIMClient.getInstance(currentUser.getObjectId()).getConversation("5935250e1b69e6005cafb876");
                        AVIMTextMessage message = new AVIMTextMessage();
                        message.setText("測試式式ASAF");
                        conv.sendMessage(message, new AVIMConversationCallback() {
                            @Override
                            public void done(AVIMException e) {
                                if (e == null) {
                                    Log.i("測試a", "发送成功！");
                                }
                                else {
                                    Log.i("失败",e.getLocalizedMessage()+"");
                                }
                            }
                        });
                    }
                });
    }
    public String idid;
    AVIMConversation conversationaaa;

    private void fid() {
        til1 = (TextInputLayout) findViewById(R.id.til1);
        til2 = (TextInputLayout)findViewById(R.id.til2);
        ed1 = (TextInputEditText)findViewById(R.id.ed1);
        ed2 = (EditText)findViewById(R.id.ed2);
        dialogBox = new DialogBox(this);
        bt1 = (Button)findViewById(R.id.bt1);
        bt2 = (Button)findViewById(R.id.bt2);
        bt3 = (Button)findViewById(R.id.bt3);

    }
    /**使用官方封裝過的方法*/
    private void attemptRegister_x()
    {


    }








    private void attemptRegister()
    {
        til1.setError(null);
        til2.setError(null);
        if(!TextUtils.isEmpty(ed1.getText().toString())) {
            if (!TextUtils.isEmpty(ed2.getText().toString())  && isPasswordValid(ed2.getText().toString()))
            {
                dialogBox.ShoDi();
                AVUser user = new AVUser();// 新建 AVUser 对象实例
                user.setUsername(ed1.getText().toString());// 设置用户名
                user.setPassword(ed2.getText().toString());// 设置密码
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                            // 注册成功，把用户对象赋值给当前用户 AVUser.getCurrentUser()
                            Log.i("成功","成功");

                        } else {
                            // 失败的原因可能有多种，常见的是用户名已经存在。
                            Log.i("失败",e.getCode()+"");
                            if (e.getCode()==202){
                                DialogBox.getAlertDialog1(RegisterPage.this,"錯誤","用戶名已被使用");
                            }
                            ToastUnity.ShowTost(RegisterPage.this, e.getMessage());
                        }
                        dialogBox.DisDi();
                    }
                });
            }
            else{
                til2.setError("密碼長度須大於4");
            }
        }
        else {
            til1.setError("未填帳號");
        }
    }
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }


    public void sendMessageToJerryFromTom(String clientId) {
        // Tom 用自己的名字作为clientId，获取AVIMClient对象实例
        AVIMClient tom = AVIMClient.getInstance(clientId);
        // 与服务器连接
        tom.open(new AVIMClientCallback() {
            @Override
            public void done(AVIMClient client, AVIMException e) {
                if (e == null) {


                    Log.i("客戶端ID",client.getClientId());

                    // 创建与Jerry之间的对话
                    client.createConversation(Arrays.asList("Jerry"), "Tom & Jerry", null,
                            new AVIMConversationCreatedCallback() {

                                @Override
                                public void done(AVIMConversation conversation, AVIMException e) {
                                    if (e == null) {

                                        Log.i("對話ID",conversation.getConversationId());
                                        idid = conversation.getConversationId();
                                        conversationaaa = conversation;
                                        AVIMTextMessage msg = new AVIMTextMessage();
                                        msg.setText("耗子，起床！");
                                        // 发送消息
                                        conversation.sendMessage(msg, new AVIMConversationCallback() {

                                            @Override
                                            public void done(AVIMException e) {
                                                if (e == null) {
                                                    Log.i("Tom & Jerry", "发送成功！");
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                }
            }
        });
    }
}
