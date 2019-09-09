package com.ktc.playandroid.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ktc.playandroid.R;
import com.ktc.playandroid.baseview.PlayAndroidApp;
import com.ktc.playandroid.ui.adapter.MineChoiceAdapter;
import com.ktc.playandroid.ui.baseui.BaseFragment;
import com.ktc.playandroid.util.ConstantClass;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineFragment extends BaseFragment {
    @BindView(R.id.imv_main_head)
    ImageView mImgHead;
    @BindView(R.id.imv_main_usename)
    TextView mTextUserName;
    @BindView(R.id.lv_main_content)
    ListView mListView;

    private Context mContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /* // ButterKnife.bind(this,mView);
        mContext = getContext();
        initViewData();*/
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

    private void initViewData(){
        mImgHead.setImageResource(R.drawable.mine_head_default);
        SharedPreferences sharedPreferences = PlayAndroidApp.getInstance().getAppComponent().getSharePrefer();
        mTextUserName.setText(sharedPreferences.getString("username",null));
        MineChoiceAdapter mineChoiceAdapter = new MineChoiceAdapter(mContext);
        mListView.setAdapter(mineChoiceAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        //ButterKnife.bind(this,mView);
        mContext = getContext();
        initViewData();
        return mView;
    }
    @Override
    public BaseFragment getContex() {
        return this;
    }

    @Override
    public void initResponse() {

    }
}
