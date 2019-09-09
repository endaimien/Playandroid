package com.ktc.playandroid.dagger.component;

import com.ktc.playandroid.ui.fragment.NavigationFragment;

import dagger.Subcomponent;

@Subcomponent
public interface NaviComponent {
    void inject(NavigationFragment navigationFragment);
}
