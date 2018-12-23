package com.ktc.playandroid.mvpcomponent.contract;

import com.ktc.playandroid.mvpcomponent.basemvp.BaseMview;
import com.ktc.playandroid.mvpcomponent.basemvp.BasePresent;
import com.ktc.playandroid.mvpcomponent.present.LoginPresent;

public interface LoginContract {
    interface View extends BaseMview<LoginPresent> {

    }
    interface Presenter extends BasePresent{

    }
}
