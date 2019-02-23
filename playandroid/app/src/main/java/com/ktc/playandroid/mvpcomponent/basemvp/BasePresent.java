package com.ktc.playandroid.mvpcomponent.basemvp;

import android.content.Context;

import com.ktc.playandroid.mvpcomponent.contract.LoginContract;

public interface BasePresent<T> {

    /*
    * 设置view*/
    void attachView(T pView);

    /*
    * 取消设置view*/
    void detachView();
}
