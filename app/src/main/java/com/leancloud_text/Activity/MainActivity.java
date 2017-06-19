package com.leancloud_text.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.TextView;

import com.leancloud_text.Adapder.GradientTabStripAdapter;
import com.leancloud_text.Fragment.AccountFragment;
import com.leancloud_text.Fragment.ContactsFragment;
import com.leancloud_text.Fragment.ConversFragment;
import com.leancloud_text.Fragment.DiscoveryFragment;
import com.leancloud_text.R;

import java.util.ArrayList;
import java.util.List;

import am.widget.basetabstrip.BaseTabStrip;
import am.widget.gradienttabstrip.GradientTabStrip;

public class MainActivity extends BaseAppActivity implements BaseTabStrip.OnItemClickListener {

    private ViewPager vpFragments;
    private TextView tvTitle;
    private GradientTabStrip tabStrip;
    private GradientTabStripAdapter adapter;
    private String[] texts = {"對話","連繫人","搜尋","設置"};





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fid();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ConversFragment());//(new FragmentA());//Fragment1_Home()
        fragmentList.add(new ContactsFragment());//(new Fragment2_Record()());
        fragmentList.add(new DiscoveryFragment());//Fragment3_Search()
        fragmentList.add(new AccountFragment());//Fragment4_Settings()
        adapter = new GradientTabStripAdapter(getSupportFragmentManager(),fragmentList,texts);
        vpFragments.setAdapter(adapter);
        tabStrip.setAdapter(adapter);
        tabStrip.bindViewPager(vpFragments);

    }

    private void fid() {
        tabStrip = (GradientTabStrip) findViewById(R.id.gts_gts_tabs);
        tvTitle = (TextView) findViewById(R.id.gts_tv_title);
        vpFragments = (ViewPager) findViewById(R.id.gts_vp_fragments);



    }


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onSelectedClick(int position) {

    }

    @Override
    public void onDoubleClick(int position) {

    }
}
