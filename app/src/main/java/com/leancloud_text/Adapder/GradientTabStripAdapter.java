package com.leancloud_text.Adapder;


import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;

import com.leancloud_text.R;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import java.util.List;


/**
 * Created by kevinh on 2017/6/19.
 */

public class GradientTabStripAdapter extends FragmentPagerAdapter implements AdvancedPagerSlidingTabStrip.IconTabProvider{

    private List<Fragment> mFragments;

    private Integer[] NormalDrawab = {R.mipmap.icon_a1,R.mipmap.icon_b1
            ,R.mipmap.icon_c1,R.mipmap.icon_d1};

    private Integer[] SelectedDrawab = {R.mipmap.icon_a2,R.mipmap.icon_b2
            ,R.mipmap.icon_c2,R.mipmap.icon_d2};
    private String[] texts;

    private Context context;

    public GradientTabStripAdapter(FragmentManager fm, Context context, List<Fragment> mFragments, String[] texts) {
        super(fm);
        this.mFragments= mFragments;
        this.texts= texts;
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return texts[position];
    }



    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }



    @Override
    public int getCount() {
        return mFragments.size();
    }


    @Override
    public Drawable getPageIcon(int position) {
        return ContextCompat.getDrawable(context, NormalDrawab[position]);
    }

    @Override
    public Drawable getPageSelectIcon(int position) {
        return ContextCompat.getDrawable(context, SelectedDrawab[position]);
    }

    @Override
    public Rect getPageIconBounds(int position) {
        return null;
    }
}
