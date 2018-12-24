package com.ktc.playandroid.baseview;

import android.app.Application;

import com.ktc.playandroid.dagger.component.AppComponent;
import com.ktc.playandroid.dagger.component.DaggerAppComponent;
import com.ktc.playandroid.dagger.module.AppModule;
import com.ktc.playandroid.dagger.module.HttpModule;

public class PlayAndroidApp extends Application {
    public static PlayAndroidApp instance;
    public AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    public static synchronized PlayAndroidApp getInstance(){return instance;}

    public void setDaggerAppComponent(){
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build();
    }
    /*
    * 提供得到AppComponent的方法，方便subComponent引用*/
    public AppComponent getAppComponent(){
        //sington 只能初始化一次
        if(mAppComponent==null){
            this.setDaggerAppComponent();
        }
        return mAppComponent;
    }
}
