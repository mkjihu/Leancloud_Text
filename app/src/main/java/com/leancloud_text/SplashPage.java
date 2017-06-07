package com.leancloud_text;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.leancloud_text.Util.LeanchatUser;
import com.leancloud_text.obj.DialogBox;

public class SplashPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        if (LeanchatUser.getCurrentUser() != null) {
            LeanchatUser.getCurrentUser().updateUserInfo();
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    ///----连接服务器---使用登入者的ObjectId
                    AVIMClient tom = AVIMClient.getInstance(AVUser.getCurrentUser().getObjectId());
                    // 与服务器连接
                    tom.open(new AVIMClientCallback() {
                        @Override
                        public void done(AVIMClient avimClient, AVIMException e) {
                            Intent intent = new Intent(SplashPage.this,MainActivity.class);
                            startActivity(intent);
                            SplashPage.this.finish();
                        }
                    });
                    SplashPage.this.finish();
                }
            }, 2500);
        }
        else
        {
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    Intent intent = new Intent(SplashPage.this,RegisterPage.class);
                    startActivity(intent);
                    SplashPage.this.finish();
                }
            }, 2500);
        }
    }
}
