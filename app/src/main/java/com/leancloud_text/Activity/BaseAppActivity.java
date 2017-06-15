package com.leancloud_text.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.view.inputmethod.InputMethodManager;

import com.leancloud_text.Presenter.BasePresenter;
import com.leancloud_text.View.maView;

/**
 * Created by kevinh on 2017/6/15.
 */

public class BaseAppActivity extends AppCompatActivity implements maView {
    private ProgressDialog logdialogs;
    //public AlertDialog alertDialogas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        logdialogs = new ProgressDialog(this);
        logdialogs.setCancelable(false);
        logdialogs.setInverseBackgroundForced(false);
        logdialogs.setCanceledOnTouchOutside(false);
        logdialogs.setMessage("加載中...");


    }

    @Override
    protected void onStop() {
        //APP暫停時執行
        super.onStop();
    }

    @Override
    protected void onRestart()
    {
        //APP從onStop回覆時執行
        super.onRestart();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    /**顯示加載*/
    public void showload() {
        if(logdialogs!=null && !logdialogs.isShowing()) {
            logdialogs.show();
        }
    }
    /**顯示加載*/
    public void showload(String text) {
        if(logdialogs!=null && !logdialogs.isShowing()) {
            logdialogs.setMessage(text);
            logdialogs.show();
        }
    }
    /**此处关闭加載*/
    public void hideload() {
        if(logdialogs!=null && logdialogs.isShowing()) {
            logdialogs.dismiss();
        }
    }

    /**關閉鍵盤*/
    public void Closekeyboard(Activity context) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {}
    }
}
