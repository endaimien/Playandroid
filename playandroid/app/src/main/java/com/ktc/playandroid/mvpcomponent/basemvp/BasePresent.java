package com.ktc.playandroid.mvpcomponent.basemvp;

import com.ktc.playandroid.mvpcomponent.contract.LoginContract;

public interface BasePresent {
    void start();
    void stop();

    /*
    * 设置view*/
    void attachView();

    /*
    * 取消设置view*/
    void detachView();
}
