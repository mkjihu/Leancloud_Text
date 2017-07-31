package com.leancloud_text.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.leancloud_text.Presenter.Frag.ConversFragmentPresenter;
import com.leancloud_text.R;
import com.leancloud_text.View.maView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by kevinh on 2017/6/19.
 */

public class ConversFragment extends BaseFragment implements maView,SwipeRefreshLayout.OnRefreshListener {


    private RecyclerView recycler_view;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private ConversFragmentPresenter presenter;

    //-對話
    public ConversFragment() {
    }





    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        presenter =new ConversFragmentPresenter(this);
        EventBus.getDefault().register(this);//注册

    }


    @Subscribe(threadMode = ThreadMode.POSTING)
    public void UpdateListEventBus(String event) {

    }



    @Override
    protected void findViewById(View view) {
        recycler_view =(RecyclerView)view.findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mSwipeRefreshLayout =(SwipeRefreshLayout)view.findViewById(R.id.mSwipeRefreshLayout);

        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        mSwipeRefreshLayout.setProgressViewOffset(true, 0, 200);
        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        //mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_convers;
    }

    @Override
    public void ToComicDirectory(Object object) {

    }


    @Override
    public void onRefresh() {
        //下拉更新執行
        new Handler().postDelayed(new Runnable()  {
            @Override
            public void run()  {

            }
        }, 1000);
    }

    @Override
    public void onDestroy()
    {
        presenter.destroy();
        EventBus.getDefault().unregister(this);//反注册
        super.onDestroy();
    }



}
