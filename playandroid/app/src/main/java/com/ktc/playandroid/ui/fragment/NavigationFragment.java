package com.ktc.playandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ktc.playandroid.R;
import com.ktc.playandroid.ui.baseui.BaseFragment;
import com.ktc.playandroid.util.ConstantClass;

public class NavigationFragment extends BaseFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public static NavigationFragment getInstance(int paraint,String parastr){
        NavigationFragment mNavigationFragment = new NavigationFragment();
        Bundle message = new Bundle();
        message.putInt(ConstantClass.FRAGPARINT,paraint);
        message.putString(ConstantClass.FRAGPARSTR,parastr);
        mNavigationFragment.setArguments(message);
        return mNavigationFragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.navigat_fragment;
    }
}
