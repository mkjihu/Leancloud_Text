package com.leancloud_text.Presenter;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.leancloud_text.Activity.SplashPage;
import com.leancloud_text.Network.HttpApiClient;
import com.leancloud_text.Util.LeanchatUser;
import com.leancloud_text.obj.LogU;

import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kevinh on 2017/6/15.
 */

public class SplashPresenter extends BasePresenter<SplashPage> {
    public SplashPresenter(SplashPage view) {
        super(view);
    }

    public void Strike(LeanchatUser leanchatUser)
    {
        Disposable d = Flowable.just(leanchatUser)
                .subscribeOn(Schedulers.io())//--跑在線程背後--只讀一次
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription arg0) throws Exception {
                        //显示加载-解析資料中
                        getView().showload("連線伺服器..");
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread()) // 指定上面的doOnSubscribe跑主线程
                .flatMap(new Function<LeanchatUser,Flowable<String>>() {
                    @Override
                    public Flowable<String> apply(@NonNull LeanchatUser leanchatUser) throws Exception {
                        try{
                            LogU.i("ฅ(๑*д*๑)ฅ!!", "InstanceID token: " + FirebaseInstanceId.getInstance().getToken());
                            String Token = FirebaseInstanceId.getInstance().getToken();
                        }catch (Exception e)
                        {
                            LogU.i("（ﾟДﾟ）σ弌弌弌弌弌弌弌弌弌弌弌弌弌弌弌弌弌弌弌弌⊃", e.getMessage());
                            throw Exceptions.propagate(new RuntimeException("無法註冊FCM"));
                        }
                        return null;
                    }
                })

            getDisposable().add(d);
    }


    public Flowable<String> Pa1()
    {
        return HttpApiClient.getInstance().
    }


}
