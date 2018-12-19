package com.ktc.playandroid.dagger.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
   private Context mContext;
   public AppModule(Context pContext){
       this.mContext = pContext;
   }
   @Provides
   @Singleton
    public Context provideAppContext(){
       return mContext;
   }
   @Provides
   @Singleton
    public SharedPreferences provideSharedPre(){
       return PreferenceManager.getDefaultSharedPreferences(mContext);
   }
}
