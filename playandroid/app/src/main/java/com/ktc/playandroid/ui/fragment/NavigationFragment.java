package com.ktc.playandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ktc.playandroid.R;
import com.ktc.playandroid.baseview.PlayAndroidApp;
import com.ktc.playandroid.internet.bean.navigation.NavigationArticlesData;
import com.ktc.playandroid.internet.bean.navigation.NavigationListData;
import com.ktc.playandroid.mvpcomponent.contract.NavigaContract;
import com.ktc.playandroid.mvpcomponent.present.NavigationPresenter;
import com.ktc.playandroid.ui.adapter.NaviAdapter;
import com.ktc.playandroid.ui.adapter.NaviContentRecyAdapter;
import com.ktc.playandroid.ui.adapter.NavigationTitleAdapter;
import com.ktc.playandroid.ui.baseui.BaseFragment;
import com.ktc.playandroid.util.ConstantClass;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class NavigationFragment extends BaseFragment implements NavigaContract.View {
    public final static String TAG = "NavigationFragment";
    public ArrayList<String> mListTitle = new ArrayList<>();
    public ArrayList<String> mRecyContent = new ArrayList<>();

    public List<NavigationListData> mNavigationListData;

    public NavigationTitleAdapter mNaviAdapter;

    public NaviContentRecyAdapter mNaviContentAdapter;

    @BindView(R.id.lv_navi_tile)
    ListView mListViewTle;
    @BindView(R.id.rcv_navi_content)
    RecyclerView mRecycleContent;
    @BindView(R.id.ttv_navi_contentti)
    TextView mTtvnaviconten;
    @Inject
    NavigationPresenter mNavigationPresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlayAndroidApp.getInstance().getAppComponent().getNaviComonent().inject(this);
        if(mNavigationPresenter!=null){
            mNavigationPresenter.attachView(this);
        }
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
    @Override
    public BaseFragment getContex() {
        return this;
    }
    @Override
    public void initResponse() {
    }

    @Override
    public void showNavigattionData(List<NavigationListData> navigationListData) {
        Log.d(TAG,"SHOWNAVI"+mListTitle);
        mNavigationListData = navigationListData;
        for(NavigationListData navigation:navigationListData){
            Log.d(TAG,"SHOWNAVI"+navigation.getName());
            mListTitle.add(navigation.getName());
        }
        Log.d(TAG,"SHOWNAVI"+mListTitle);
        List<NavigationArticlesData> navigationArticlesDataList = navigationListData.get(0).getArticles();
        for(NavigationArticlesData navigationArticlesData:navigationArticlesDataList){
            mRecyContent.add(navigationArticlesData.getTitle());
        }
        Log.d(TAG,"SHOWNAVI"+mListTitle);
        mNaviAdapter = new NavigationTitleAdapter(this.getContext(),mListTitle);
        mNaviContentAdapter = new NaviContentRecyAdapter(mRecyContent);
        mListViewTle.setAdapter(mNaviAdapter);
        mRecycleContent.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mRecycleContent.setAdapter(mNaviContentAdapter);
    }

    @Override
    public void setPresent(NavigaContract.Presenter present) {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavigationPresenter.getNavigationData();
    }
}
