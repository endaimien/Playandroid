package com.ktc.playandroid.dagger.component;

import com.ktc.playandroid.ui.fragment.HomeFragment;

import dagger.Subcomponent;

@Subcomponent
public interface HomeComponent {
     void inject(HomeFragment homeFragment);
}
