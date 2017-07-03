package com.leancloud_text.Fragment;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.leancloud_text.R;
import com.leancloud_text.UIView.CircleImageView;

/**
 * Created by kevinh on 2017/6/19.
 */

public class AccountFragment extends BaseFragment {

    private CircleImageView icage;
    private ImageView qrimage;
    private TextView tv1,idtv,textView7;
    private Button loutbt;



    public AccountFragment() {
    }


    @Override
    protected void initVariables() {
        super.initVariables();




    }

    @Override
    protected void findViewById(View view) {
        icage = (CircleImageView)view.findViewById(R.id.icage);

        qrimage = (ImageView)view.findViewById(R.id.qrimage);
        tv1 = (TextView)view.findViewById(R.id.tv1);
        idtv = (TextView)view.findViewById(R.id.idtv);
        textView7 = (TextView)view.findViewById(R.id.textView7);
        loutbt = (Button)view.findViewById(R.id.loutbt);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_account;
    }

    @Override
    public void ToComicDirectory(Object object) {

    }
}
