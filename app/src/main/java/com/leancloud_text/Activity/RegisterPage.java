package com.leancloud_text.Activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SignUpCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.jakewharton.rxbinding2.view.RxView;
import com.leancloud_text.Presenter.RegisterPresenter;
import com.leancloud_text.R;
import com.leancloud_text.obj.DialogBox;
import com.leancloud_text.obj.LogU;
import com.leancloud_text.obj.ToastUnity;
import java.util.concurrent.TimeUnit;
import io.reactivex.functions.Consumer;

public class RegisterPage extends BaseAppActivity {

    public TextInputLayout til1,til2;
    public TextInputEditText ed1;
    public EditText ed2;


    private Button bt1,bt2,bt3;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        presenter= new RegisterPresenter(this);
        fid();
        vitin();
        //EventBus.getDefault().register(this);//注册

        /*
        AVUser.getCurrentUser().put("age", 25);
        AVUser.getCurrentUser().saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if(e == null){
                    Log.i("saved","success!");
                }
            }
        });
        */
        /*
        Log.i("QAAVInstallation",AVInstallation.getCurrentInstallation().getInstallationId());
        AVInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            public void done(AVException e) {
                if (e == null) {
                    // 保存成功
                    Log.i("保存成功",AVInstallation.getCurrentInstallation().getInstallationId());
                    AVInstallation.getCurrentInstallation().saveInBackground();

                } else {
                    // 保存失败，输出错误信息
                    Log.i("保存失败",e.getLocalizedMessage()+"");
                }
            }
        });
        */
        /*
        if (AVUser.getCurrentUser() !=null)
        {
            AVInstallation installation = AVInstallation.getCurrentInstallation();
            installation.saveInBackground();
            if (installation != null) {
                AVUser.getCurrentUser().put(LeanchatUser.INSTALLATION, installation);
                AVUser.getCurrentUser().saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        if(e!=null)
                            Log.i("大失败A",e.getLocalizedMessage()+"");
                        else
                            Log.i("QQ","成啦");
                    }
                });
            }
            else{
                Log.i("大錯誤","沒有installation");
            }
        }
        */
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
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
                        attemptRegister(ed1.getText().toString(),ed2.getText().toString());
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
                        loing(ed1.getText().toString(),ed2.getText().toString());
                    }
                });
        RxView.clicks(bt3)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object arg0) throws Exception {
                        /*
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
                        *///
                        final AVUser product = AVUser.getCurrentUser();
                        AVFile file = new AVFile("url", "https://pic.pimg.tw/acatandcats/1386481325-3920179167.jpg",null);

                        product.put("image", file);

                        file.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(AVException e) {
                                if (e == null) {
                                    product.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(AVException e) {
                                            if(e!=null) LogU.i("大失败W",e.getLocalizedMessage()+"");
                                        }
                                    });
                                } else {
                                    LogU.i("失败W",e.getLocalizedMessage()+"");
                                }
                            }
                        });
                    }
                });
    }
    //public String idid;
    //AVIMConversation conversationaaa;

    private void fid() {
        til1 = (TextInputLayout) findViewById(R.id.til1);
        til2 = (TextInputLayout)findViewById(R.id.til2);
        ed1 = (TextInputEditText)findViewById(R.id.ed1);
        ed2 = (EditText)findViewById(R.id.ed2);
        bt1 = (Button)findViewById(R.id.bt1);
        bt2 = (Button)findViewById(R.id.bt2);
        bt3 = (Button)findViewById(R.id.bt3);

    }



    /**使用官方封裝過的方法*/
    private void attemptRegister_x()
    {
        //-註冊好之後關連推送ID-
        if (AVUser.getCurrentUser() != null) {
            AVInstallation installation = AVInstallation.getCurrentInstallation();
            LogU.i("AVInstallation成功",AVInstallation.getCurrentInstallation().getInstallationId()+"");
            if (installation!=null)
            {
                AVUser.getCurrentUser().put("installation",AVInstallation.getCurrentInstallation());
                AVUser.getCurrentUser().saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        if(e!=null)
                            LogU.i("出錯",e.getLocalizedMessage()+"");
                        else
                            LogU.i("QQ","1成啦");
                    }
                });
            }
            else
            {
                LogU.i("AVInstallation失败","AVInstallation失败");
            }

            //--註冊FCMKEY
            presenter.Strike(AVUser.getCurrentUser().getObjectId());
        }
    }


    /**註冊*/
    private void attemptRegister(String username,String password)
    {
        til1.setError(null);
        til2.setError(null);
        if(!TextUtils.isEmpty(ed1.getText().toString())) {
            if (!TextUtils.isEmpty(username)  && isPasswordValid(password))
            {
                showload();
                AVUser user = new AVUser();// 新建 AVUser 对象实例
                user.setUsername(username);// 设置用户名
                user.setPassword(password);// 设置密码
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                            // 注册成功，把用户对象赋值给当前用户 AVUser.getCurrentUser()
                            LogU.i("成功","成功");
                            attemptRegister_x();
                        } else {
                            hideload();//
                            // 失败的原因可能有多种，常见的是用户名已经存在。
                            LogU.i("失败",e.getMessage()+"");
                            if (e.getCode()==202){
                                DialogBox.getAlertDialog1(RegisterPage.this,"錯誤","用戶名已被使用");
                            }
                            ToastUnity.ShowTost(RegisterPage.this, e.getMessage());
                        }
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



    public void Connected()
    {
        ///----连接服务器---使用登入者的ObjectId
        AVIMClient tom = AVIMClient.getInstance(AVUser.getCurrentUser().getObjectId());
        // 与服务器连接
        tom.open(new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                hideload();
                if(e!=null){
                    LogU.i("连接服务器失败",e.getLocalizedMessage()+"");
                    DialogBox.getAlertDialog1(RegisterPage.this,"连接服务器失败",e.getLocalizedMessage());
                } else {
                    Intent intent = new Intent(RegisterPage.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    //--登入
    private void loing(String username,String password)
    {
        showload();
        AVUser.logInInBackground(username, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e == null) {
                    attemptRegister_x();
                } else {
                    hideload();//
                    LogU.i("登入出錯",e.getLocalizedMessage()+"");
                    ToastUnity.ShowTost(RegisterPage.this, e.getMessage());
                }
            }
        });
    }


//    public void sendMessageToJerryFromTom(String clientId) {
//        // Tom 用自己的名字作为clientId，获取AVIMClient对象实例
//        AVIMClient tom = AVIMClient.getInstance(clientId);
//        // 与服务器连接
//        tom.open(new AVIMClientCallback() {
//            @Override
//            public void done(AVIMClient client, AVIMException e) {
//                if (e == null) {
//
//
//                    Log.i("客戶端ID",client.getClientId());
//
//                    // 创建与Jerry之间的对话
//                    client.createConversation(Arrays.asList("Jerry"), "Tom & Jerry", null,
//                            new AVIMConversationCreatedCallback() {
//
//                                @Override
//                                public void done(AVIMConversation conversation, AVIMException e) {
//                                    if (e == null) {
//
//                                        Log.i("對話ID",conversation.getConversationId());
//                                        idid = conversation.getConversationId();
//                                        conversationaaa = conversation;
//                                        AVIMTextMessage msg = new AVIMTextMessage();
//                                        msg.setText("耗子，起床！");
//                                        // 发送消息
//                                        conversation.sendMessage(msg, new AVIMConversationCallback() {
//
//                                            @Override
//                                            public void done(AVIMException e) {
//                                                if (e == null) {
//                                                    Log.i("Tom & Jerry", "发送成功！");
//                                                }
//                                            }
//                                        });
//                                    }
//                                }
//                            });
//                }
//            }
//        });
//    }
}
