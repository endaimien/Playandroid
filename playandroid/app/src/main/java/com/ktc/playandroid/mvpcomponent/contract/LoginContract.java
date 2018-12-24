package com.ktc.playandroid.mvpcomponent.contract;

import com.ktc.playandroid.mvpcomponent.basemvp.BaseMview;
import com.ktc.playandroid.mvpcomponent.basemvp.BasePresent;
import com.ktc.playandroid.mvpcomponent.present.LoginPresent;

public interface LoginContract {
    interface View extends BaseMview<LoginPresent> {
        /*
         * 登陆成功回调函数*/
        void loginSuccess();

        /*
        登陆失败调用函数*/
        void loginFail();

        /*
         * 注册成功*/
        void registerSuccess();

        /*
        注册失败*/
        void registFail();

        /*
         * 登出调用*/
        void loginOut();


    }

    interface Presenter extends BasePresent {
        /*
         * 登录*/
        void loginIn(String name, String password);

        /*注册*/
        void register(String name, String password, String repassword);

        /*
         * 登出*/
        void loginOut();
    }
}
