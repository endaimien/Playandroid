package com.ktc.playandroid.dagger.component;

import com.ktc.playandroid.ui.activity.LoginActivity;

import dagger.Subcomponent;

@Subcomponent
public interface LoginComponent {
    void inject(LoginActivity pLoginActivity);
}
