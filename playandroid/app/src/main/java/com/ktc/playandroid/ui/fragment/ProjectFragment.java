package com.ktc.playandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ktc.playandroid.R;
import com.ktc.playandroid.ui.baseui.BaseFragment;
import com.ktc.playandroid.util.ConstantClass;

public class ProjectFragment extends BaseFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public static ProjectFragment getInstance(int paraint,String parastr){
        ProjectFragment mProjectFragment = new ProjectFragment();
        Bundle message = new Bundle();
        message.putInt(ConstantClass.FRAGPARINT,paraint);
        message.putString(ConstantClass.FRAGPARSTR,parastr);
        mProjectFragment.setArguments(message);
        return mProjectFragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.project_fragment;
    }
}
