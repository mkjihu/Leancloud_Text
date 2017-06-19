package com.leancloud_text.Presenter;


import com.google.firebase.iid.FirebaseInstanceId;
import com.leancloud_text.Activity.RegisterPage;
import com.leancloud_text.Model.FcmKey;
import com.leancloud_text.Network.HttpApiClient;
import com.leancloud_text.obj.LogU;
import org.reactivestreams.Subscription;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class RegisterPresenter extends BasePresenter<RegisterPage> {
    public RegisterPresenter(RegisterPage view) {
        super(view);
    }


    public void Strike(final String UserId)
    {
        Disposable d = HttpApiClient.getInstance().GetFcmKey(UserId)
                .subscribeOn(Schedulers.io())//--跑在線程背後--只讀一次
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription arg0) throws Exception {
                        //显示加载-解析資料中
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread()) // 指定上面的doOnSubscribe跑主线程
                .flatMap(new Function<FcmKey, Flowable<String>>() {
                    @Override
                    public Flowable<String> apply(@NonNull FcmKey fcmKey) throws Exception {

                        LogU.i("ฅ(๑*д*๑)ฅ!!", "FCM token: " + FirebaseInstanceId.getInstance().getToken());
                        String Token = FirebaseInstanceId.getInstance().getToken();

                        if (fcmKey==null ||fcmKey.getObjectId()==null)  {
                            //-帳號尚未關連FCMkey
                            return Pa1(UserId,Token);
                        } else {
                            return Pa2(UserId,Token);
                        }

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//--結果在主線程中顯示
                .unsubscribeOn(Schedulers.io())//允許取消訂閱
                .subscribeWith(new DisposableSubscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        LogU.i("完成", s);
                    }
                    @Override
                    public void onError(Throwable e) {
                        LogU.i("完成GG", e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                        getView().Connected();
                    }
                });

        getDisposable().add(d);
    }

    /**創*/
    private Flowable<String> Pa1(String CurrentUserId,String Token)
    {
        return HttpApiClient.getInstance().PostFcmKey(new FcmKey(CurrentUserId, Token));
    }

    /**更*/
    private Flowable<String> Pa2(String CurrentUserId,String Token)
    {
        return HttpApiClient.getInstance().UpPostFcmKey(CurrentUserId,new FcmKey(CurrentUserId, Token));
    }

}