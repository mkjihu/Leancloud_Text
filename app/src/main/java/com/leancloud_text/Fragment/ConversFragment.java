package com.leancloud_text.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.leancloud_text.R;

/**
 * Created by kevinh on 2017/6/19.
 */

public class ConversFragment extends BaseFragment {


    private RecyclerView recycler_view;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    //-對話
    public ConversFragment() {
    }






    @Override
    protected void findViewById(View view) {
        recycler_view =(RecyclerView)view.findViewById(R.id.mSwipeRefreshLayout);

        mSwipeRefreshLayout =(SwipeRefreshLayout)view.findViewById(R.id.mSwipeRefreshLayout);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_convers;
    }

    @Override
    public void ToComicDirectory(Object object) {

    }
}
