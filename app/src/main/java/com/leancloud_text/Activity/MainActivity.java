package com.leancloud_text.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.TextView;

import com.avos.avoscloud.im.v2.AVIMMessageManager;
import com.avos.avoscloud.im.v2.AVIMTypedMessage;
import com.leancloud_text.Adapder.GradientTabStripAdapter;
import com.leancloud_text.Fragment.AccountFragment;
import com.leancloud_text.Fragment.ContactsFragment;
import com.leancloud_text.Fragment.ConversFragment;
import com.leancloud_text.Fragment.DiscoveryFragment;
import com.leancloud_text.MyLeanCloudApp;
import com.leancloud_text.R;
import com.leancloud_text.handler.MessageHandler;
import com.leancloud_text.obj.LogU;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseAppActivity {

    private ViewPager vpFragments;
    private TextView tvTitle;

    private GradientTabStripAdapter adapter;

    private String[] texts = {"對話","連繫人","搜尋","設置"};

    public AdvancedPagerSlidingTabStrip tabs;

    
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
        adapter = new GradientTabStripAdapter(getSupportFragmentManager(),this,fragmentList,texts);
        vpFragments.setAdapter(adapter);

        tabs.setViewPager(vpFragments);

        tabs.showDot(0, "99+");
        tvTitle.setText(texts[0]);
        vpFragments.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                tvTitle.setText(texts[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    private void fid() {
        tvTitle = (TextView) findViewById(R.id.gts_tv_title);
        vpFragments = (ViewPager) findViewById(R.id.gts_vp_fragments);
        tabs = (AdvancedPagerSlidingTabStrip)findViewById(R.id.tabs);

    }
    @Override
    protected void onResume() {
        super.onResume();
        LogU.i("onResume","onResume畫面");
        AVIMMessageManager.registerMessageHandler(AVIMTypedMessage.class, MyLeanCloudApp.getMessageHandler());
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogU.i("暫停畫面onPause","暫停畫面");
        AVIMMessageManager.unregisterMessageHandler(AVIMTypedMessage.class, MyLeanCloudApp.getMessageHandler());
    }
    @Override
    protected void onStop() {
        super.onStop();
        LogU.i("暫停畫面onStop","暫停畫面");
        //AVIMMessageManager.registerMessageHandler(AVIMTypedMessage.class,MyLeanCloudApp.getMessageHandler());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
