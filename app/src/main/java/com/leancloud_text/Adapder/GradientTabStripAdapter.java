package com.leancloud_text.Adapder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;

import com.leancloud_text.R;

import java.util.List;

import am.widget.gradienttabstrip.GradientTabStrip;

/**
 * Created by kevinh on 2017/6/19.
 */

public class GradientTabStripAdapter extends FragmentPagerAdapter implements GradientTabStrip.GradientTabAdapter {

    private List<Fragment> mFragments;
    private Integer[] NormalDrawab = {R.mipmap.icon_a1,R.mipmap.icon_b1
            ,R.mipmap.icon_c1,R.mipmap.icon_d1};

    private Integer[] SelectedDrawab = {R.mipmap.icon_a2,R.mipmap.icon_b2
            ,R.mipmap.icon_c2,R.mipmap.icon_d2};

    private String[] texts;
    public GradientTabStripAdapter(FragmentManager fm,List<Fragment> mFragments,String[] texts) {
        super(fm);
        this.mFragments= mFragments;
        this.texts= texts;
    }

    @Override
    public Drawable getNormalDrawable(int position, Context context) {
        return ContextCompat.getDrawable(context, NormalDrawab[position]);
    }

    @Override
    public Drawable getSelectedDrawable(int position, Context context) {
        return ContextCompat.getDrawable(context, SelectedDrawab[position]);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return texts[position];
    }


    @Override
    public boolean isTagEnable(int position) {
        return position != 3;
    }

    @Override
    public String getTag(int position) {
        switch (position) {
            default:
            case 0:
                return "888";
            case 1:
                return "";
            case 2:
                return "new";
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
