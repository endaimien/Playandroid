package com.ktc.playandroid.dagger.component;

import com.ktc.playandroid.dagger.module.AppModule;
import com.ktc.playandroid.dagger.module.HttpModule;
import com.ktc.playandroid.ui.activity.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component( modules ={ AppModule.class, HttpModule.class} )
public interface AppComponent {
    LoginComponent getLoginCompnent();
}

