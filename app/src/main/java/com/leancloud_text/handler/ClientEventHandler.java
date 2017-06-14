package com.leancloud_text.handler;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMClientEventHandler;
import com.leancloud_text.obj.LogU;

/**
 * Created by kevinh on 2017/6/14.
 */

public class ClientEventHandler extends AVIMClientEventHandler {
    @Override
    public void onConnectionPaused(AVIMClient avimClient) {
        //实现本方法以处理网络断开事件
        LogU.i("斷網","斷網");
    }

    @Override
    public void onConnectionResume(AVIMClient avimClient) {
        //实现本方法以处理网络恢复事件
        LogU.i("網路回復","網路回復");
    }

    @Override
    public void onClientOffline(AVIMClient avimClient, int i) {
        //实现本方法以处理当前登录被踢下线的情况
        LogU.i("被踢","無法登入");
    }
}
