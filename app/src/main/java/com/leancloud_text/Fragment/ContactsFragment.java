package com.leancloud_text.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.leancloud_text.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by kevinh on 2017/6/19.
 */

public class ContactsFragment  extends BaseFragment {

    ///-聯絡人
    public ContactsFragment() {
    }



    @Override
    protected void initVariables() {
        //EventBus.getDefault().register(this);//注册


    }




    @Override
    public void onDestroy() {
        //EventBus.getDefault().unregister(this);//反注册
        super.onDestroyView();
    }



    @Override
    protected void findViewById(View view) {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_a;
    }

    @Override
    public void ToComicDirectory(Object object) {

    }
}
