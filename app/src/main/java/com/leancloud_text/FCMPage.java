package com.leancloud_text;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androidquery.AQuery;
import com.google.firebase.iid.FirebaseInstanceId;
import com.leancloud_text.Model.FCMInfo;
import com.leancloud_text.Model.data;
import com.leancloud_text.Model.notification;
import com.leancloud_text.Network.API_Url;
import com.leancloud_text.Network.HttpApiClient;
import com.leancloud_text.obj.ToastUnity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class FCMPage extends AppCompatActivity {

    public AQuery aq;
    public API_Url api_url;
    public FCMInfo fcmInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcmpage);
        aq= new AQuery(this);

        aq.id(R.id.button2).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<String,Void,String>()
                {
                    @Override
                    protected String doInBackground(String... params) {
                        String sd="";
                        try{
                            Log.i("ฅ(๑*д*๑)ฅ!!", "InstanceID token: " + FirebaseInstanceId.getInstance().getToken());
                            //eGlGuuhj_7Q:APA91bFWxkBZOc_A-99bU_3IEIVMbibwwITFbM6UGMpqjBG2JpjWNvYfT7k_fHSy6vUeMLhn8Fnnzqm1_X3pNJfvOlBdSV0onQDmLX0VL9aKc-2qzhWZ87h_JURzyIWLEhm3kXOBdQPn
                            sd = FirebaseInstanceId.getInstance().getToken();
                        }catch (Exception e)
                        {
                            Log.i("（ﾟДﾟ）σ弌弌弌弌弌弌弌弌弌弌弌弌弌弌弌弌弌弌弌弌⊃", e.getMessage());
                        }
                        return sd;
                    }

                    @Override
                    protected void onPostExecute(String aVoid) {
                        super.onPostExecute(aVoid);
                        ToastUnity.ShowTost(FCMPage.this,aVoid);
                    }
                }.execute("");
            }
        });
        api_url = HttpApiClient.getRetrofit().create(API_Url.class);

        fcmInfo = new FCMInfo();
        fcmInfo.setTo("eGlGuuhj_7Q:APA91bFWxkBZOc_A-99bU_3IEIVMbibwwITFbM6UGMpqjBG2JpjWNvYfT7k_fHSy6vUeMLhn8Fnnzqm1_X3pNJfvOlBdSV0onQDmLX0VL9aKc-2qzhWZ87h_JURzyIWLEhm3kXOBdQPn");
        data data = new data();
        data.setTitle("A這是 data Title");
        data.setContent("A這是 data Content");
        notification notification = new notification();
        notification.setTitle("A這是Notification title");
        notification.setBody("A這是Notification body");
        notification.setIcon("A癢癢");
        fcmInfo.setData(data);
        fcmInfo.setNotification(notification);

        aq.id(R.id.button3).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                api_url.PostFCM("https://fcm.googleapis.com/fcm/send",fcmInfo)
                        .subscribeOn(Schedulers.io())//--跑在線程背後--只讀一次
                        .unsubscribeOn(Schedulers.io())//允許取消訂閱
                        .observeOn(AndroidSchedulers.mainThread())//--結果在主線程中顯示
                        .subscribe(new ResourceSubscriber<String>(){
                            @Override
                            public void onError(Throwable arg0) {
                                Log.i("GG", arg0.getMessage());
                            }
                            @Override
                            public void onNext(String arg0) {
                                Log.i("AA", arg0);
                            }
                            @Override
                            public void onComplete() {}
                        });

            }
        });

    }



}
