package com.ktc.playandroid.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ktc.playandroid.R;
import com.ktc.playandroid.baseview.PlayAndroidApp;
import com.ktc.playandroid.ui.baseui.BaseActivity;

public class welcomActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                judgego();
            }
        },1500);
            // judgego();

    }
    private void judgego(){
        if(isFirstIn()){
            startActivity(new Intent(this,NavigationActivity.class));
        }else {
            if(hasLogin()){
                startActivity(new Intent(this,MainActivity.class));
            }else {
                startActivity(new Intent(this,LoginActivity.class));
            }
        }
    }
    /*
      判断是否第一次进入
    */
    private boolean isFirstIn(){
        SharedPreferences nSharedPreference = PlayAndroidApp.getInstance().getSharedPreferences("playandroidflag",MODE_PRIVATE);
        boolean isFirstIn = nSharedPreference.getBoolean("isFirstIn",true);
        return isFirstIn;
    }
    private void setFirstInvalue(boolean isFirst){
        SharedPreferences nSharedPreference = PlayAndroidApp.getInstance()
                .getSharedPreferences("playandroidflag",MODE_PRIVATE);
        SharedPreferences.Editor nEditer = nSharedPreference.edit();
        nEditer.putBoolean("isFirstIn",isFirst);
        nEditer.commit();
    }

    private boolean hasLogin(){
        SharedPreferences nSharedPreference = PlayAndroidApp.getInstance().getSharedPreferences("playandroidflag",MODE_PRIVATE);
        boolean hasLogin = nSharedPreference.getBoolean("hasLogin",false);
        return hasLogin;
    }
}
