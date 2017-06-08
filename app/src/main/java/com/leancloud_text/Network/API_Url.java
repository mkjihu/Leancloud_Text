package com.leancloud_text.Network;

import com.leancloud_text.Model.FCMInfo;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;
public interface API_Url {

    //伺服器金鑰
    //AAAA7rOgZK4:APA91bFEBdwanf0nS_x6wiT7nxA2_QzDoP06gSfOw_I6wnemRtBBChdhN2YinnCidTUb_Ri0tkuAyrXKZZTvab9lMvWMVfIHR6mX1QnhTDM_nN20TFTmIEmO53_Ey1dtwQ1rcCMPtdJM

    @POST
    @Headers({
            "Content-Type: application/json",
            "Authorization: key=AAAA7rOgZK4:APA91bFEBdwanf0nS_x6wiT7nxA2_QzDoP06gSfOw_I6wnemRtBBChdhN2YinnCidTUb_Ri0tkuAyrXKZZTvab9lMvWMVfIHR6mX1QnhTDM_nN20TFTmIEmO53_Ey1dtwQ1rcCMPtdJM"
    })
    Flowable<String> PostFCM(@Url String url, @Body FCMInfo info);


}
