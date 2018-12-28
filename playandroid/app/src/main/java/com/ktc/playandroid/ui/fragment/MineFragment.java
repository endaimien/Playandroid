package com.ktc.playandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ktc.playandroid.R;
import com.ktc.playandroid.ui.baseui.BaseFragment;
import com.ktc.playandroid.util.ConstantClass;

public class MineFragment extends BaseFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public static MineFragment getInstance(int paraint,String parastr){
        MineFragment mMineFragment = new MineFragment();
        Bundle message = new Bundle();
        message.putInt(ConstantClass.FRAGPARINT,paraint);
        message.putString(ConstantClass.FRAGPARSTR,parastr);
        mMineFragment.setArguments(message);
        return mMineFragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.mine_fragment;
    }
}
