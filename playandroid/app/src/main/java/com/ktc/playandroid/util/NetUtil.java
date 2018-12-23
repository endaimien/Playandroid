package com.ktc.playandroid.util;

import android.content.Context;
import android.net.ConnectivityManager;

import com.ktc.playandroid.baseview.PlayAndroidApp;

public class NetUtil {
    public static final String  CACHFILE = PlayAndroidApp.getInstance().getApplicationContext().getCacheDir().getAbsolutePath()+"/netcach";
    public static boolean isNetConnect() {
        ConnectivityManager connectivityManager = (ConnectivityManager) PlayAndroidApp.getInstance()
                .getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo() != null;
        }
        return false;
    }
}
