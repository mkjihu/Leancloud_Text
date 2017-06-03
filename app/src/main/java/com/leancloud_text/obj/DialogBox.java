package com.leancloud_text.obj;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
/**
 * Created by kevinh on 2017/6/3.
 */

public class DialogBox {

    public ProgressDialog logdialogs;
    public DialogBox(Context Context)
    {
        logdialogs = new ProgressDialog(Context);
        logdialogs.setCancelable(false);
        logdialogs.setInverseBackgroundForced(false);
        logdialogs.setCanceledOnTouchOutside(false);
        logdialogs.setMessage("加載中...");
    }


    public void ShoDi()
    {
        logdialogs.show();
    }
    public void DisDi()
    {
        logdialogs.dismiss();
    }


    //--提示框製造機

    /**一般提示框，不強制關機*/
    public static void getAlertDialog1(Context context,final String title,String message)
    {

        Builder builder = new Builder(context);
        if (!title.equals("")) {
            builder.setTitle(title);
        }
        builder.setCancelable(false);
        builder.setMessage(message);
        //設定Negative按鈕資料
        builder.setNegativeButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {}
        });
        builder.create().show();
    }


    /**機掰顯示框   會強制關閉Activity(っ・Д・)っ*/
    public static void getAlertDialog2(final Activity activity,final String title,String message)
    {
        Builder builder = new Builder(activity);
        if (!title.equals("")) {
            builder.setTitle(title);
        }
        builder.setCancelable(false);
        builder.setMessage(message);
        //設定Negative按鈕資料
        builder.setNegativeButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                activity.finish();
            }
        });
        builder.create().show();
    }


    /**導向顯示框
     * 是否关闭元页
     * */
    public static void getAlertDialog3(final Activity activity, final String title, String message, final String Url, final boolean b){
        Builder builder = new Builder(activity);
        if (!title.equals("")) {
            builder.setTitle(title);
        }
        builder.setCancelable(false);
        builder.setMessage(message);
        //設定Negative按鈕資料
        builder.setNegativeButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                try {
                    if (Url!=null) {
                        Intent i = new Intent(Intent.ACTION_VIEW , Uri.parse(Url));
                        activity.startActivity(i);
                    }
                } catch (Exception e) {}
                if (b) {
                    activity.finish();
                }
            }
        });
        builder.create().show();
    }
}
