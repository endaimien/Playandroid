package com.ktc.playandroid.dagger.component;

import com.ktc.playandroid.dagger.module.AppModule;
import com.ktc.playandroid.dagger.module.HttpModule;

import dagger.Component;

@Component( modules ={ AppModule.class, HttpModule.class} )
public interface AppComponent {
}
