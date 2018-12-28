package com.ktc.playandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ktc.playandroid.R;
import com.ktc.playandroid.mvpcomponent.contract.LoginContract;
import com.ktc.playandroid.ui.baseui.BaseFragment;
import com.ktc.playandroid.util.ConstantClass;

public class HomeFragment extends BaseFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public static HomeFragment getInstance(int paraint, String parastr){
        HomeFragment mHomeFrament = new HomeFragment();
        Bundle message = new Bundle();
        message.putInt(ConstantClass.FRAGPARINT,paraint);
        message.putString(ConstantClass.FRAGPARSTR,parastr);
        mHomeFrament.setArguments(message);
        return mHomeFrament;
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment;
    }
}
