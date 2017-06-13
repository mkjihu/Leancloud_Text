/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.leancloud_text.FCM;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.leancloud_text.Model.FcmKey;
import com.leancloud_text.Network.HttpApiClient;
import com.leancloud_text.Network.Shared;
import com.leancloud_text.Util.LeanchatUser;
import com.leancloud_text.obj.LogU;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.i(TAG, "Refreshed token: " + refreshedToken);

        Shared.SetFCMKey(getApplicationContext(),refreshedToken);//如果有系統更換Token 先紀錄
        sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]



    /**如果有系統更換Token*/
    private void sendRegistrationToServer(String token) {

        if (LeanchatUser.getCurrentUser() != null)
        {
            //FcmKey fcmKey = new FcmKey(LeanchatUser.getCurrentUserId(),token);
            HttpApiClient.getInstance()
                    .PostFcmKey(new FcmKey(LeanchatUser.getCurrentUserId(),token))
                    .subscribeOn(Schedulers.io())//--跑在線程背後--只讀一次
                    .unsubscribeOn(Schedulers.io())//允許取消訂閱
                    .subscribeWith(new DisposableSubscriber<String>() {
                        @Override
                        public void onNext(String s) {
                            //-完成
                            LogU.i("完成",s);

                        }
                        @Override
                        public void onError(Throwable e) {}
                        @Override
                        public void onComplete() {}
                    });

        }

    }
}
