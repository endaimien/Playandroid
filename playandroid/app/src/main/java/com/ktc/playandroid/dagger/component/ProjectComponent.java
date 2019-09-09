package com.ktc.playandroid.dagger.component;

import com.ktc.playandroid.ui.fragment.ProjectFragment;

import dagger.Subcomponent;

@Subcomponent
public interface ProjectComponent {
    void inject(ProjectFragment fragment);
}
