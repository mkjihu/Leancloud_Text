package com.leancloud_text.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.leancloud_text.Presenter.SplashPresenter;
import com.leancloud_text.R;
import com.leancloud_text.Util.LeanchatUser;
import com.leancloud_text.obj.DialogBox;
import com.leancloud_text.obj.LogU;

public class SplashPage extends BaseAppActivity {

    private SplashPresenter presenter;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);
        presenter = new SplashPresenter(this);

        /*
        try {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //申请WRITE_EXTERNAL_STORAGE权限
                LogU.i("問", "問");
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE
                        , Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE_ASK_PERMISSIONS);
            }
            else
            {
                Strike();
            }
        } catch (Exception e) {

        }
        */

    }

    //相機權限同意返回
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        LogU.i("!!!", requestCode+"");
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    LogU.i("!!!", "同意");
                    Strike();
                }
                else {
                    LogU.i("!!!", "不同意");
                    finish();
                    //DialogBox.getAlertDialog2(this, "提示", "請至設定之應用程式，開啟權限!");
                }

                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    public void Strike()
    {
        if (LeanchatUser.getCurrentUser() != null) {
            LeanchatUser.getCurrentUser().updateUserInfo();
            presenter.Strike(LeanchatUser.getCurrentUser().getObjectId());
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
            }, 1000);
        }
    }


    public void TOMain()
    {
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
            }
        }, 1000);
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }


}
