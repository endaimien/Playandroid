package com.ktc.playandroid.ui.baseui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ktc.playandroid.R;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment{
    protected View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(getLayoutId(),container,false);
        ButterKnife.bind(this,view);
        mView = view;
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initResponse();
    }

    public abstract int getLayoutId();
    public abstract BaseFragment getContex();

    public abstract void  initResponse();
}
