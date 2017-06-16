package com.leancloud_text.Network;

import com.avos.avoscloud.AVUser;
import com.leancloud_text.Model.FCMInfo;
import com.leancloud_text.Model.FcmKey;
import com.leancloud_text.MyLeanCloudApp;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;
public interface API_Url {

    public static final String BaseUrl = "https://api.leancloud.cn/1.1/";

    //伺服器金鑰
    //AAAA7rOgZK4:APA91bFEBdwanf0nS_x6wiT7nxA2_QzDoP06gSfOw_I6wnemRtBBChdhN2YinnCidTUb_Ri0tkuAyrXKZZTvab9lMvWMVfIHR6mX1QnhTDM_nN20TFTmIEmO53_Ey1dtwQ1rcCMPtdJM

    /**自己發自己推播*/
    @POST
    @Headers({
            "Content-Type: application/json",
            "Authorization: key=AAAA7rOgZK4:APA91bFEBdwanf0nS_x6wiT7nxA2_QzDoP06gSfOw_I6wnemRtBBChdhN2YinnCidTUb_Ri0tkuAyrXKZZTvab9lMvWMVfIHR6mX1QnhTDM_nN20TFTmIEmO53_Ey1dtwQ1rcCMPtdJM"
    })
    Flowable<String> PushFCM(@Url String url, @Body FCMInfo info);



    /**創資料*/
    @POST("classes/FcmKey")
    @Headers({
            "Content-Type: application/json",
            "X-LC-Id: "+ MyLeanCloudApp.APP_ID,
            "X-LC-Key: "+MyLeanCloudApp.APP_KEY
    })
    Flowable<String> PostFcmKey(@Body FcmKey fcmKey);

    /**更新用*/
    @PUT("classes/FcmKey/{objectId}")
    @Headers({
            "Content-Type: application/json",
            "X-LC-Id: "+ MyLeanCloudApp.APP_ID,
            "X-LC-Key: "+MyLeanCloudApp.APP_KEY
    })
    Flowable<String> UpPostFcmKey(@Path("objectId")String objectId,@Body FcmKey fcmKey);

    /**查詢*/
    @GET("classes/FcmKey/{objectId}")
    @Headers({
            "X-LC-Id: "+ MyLeanCloudApp.APP_ID,
            "X-LC-Key: "+MyLeanCloudApp.APP_KEY
    })
    Flowable<FcmKey> GetFcmKey(@Path("objectId")String objectId);
}
