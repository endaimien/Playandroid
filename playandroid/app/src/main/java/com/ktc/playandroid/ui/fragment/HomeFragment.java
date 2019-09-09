package com.ktc.playandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ktc.playandroid.R;
import com.ktc.playandroid.baseview.PlayAndroidApp;
import com.ktc.playandroid.internet.bean.homepage.HomeBannerData;
import com.ktc.playandroid.internet.bean.homepage.HomeEssaypageDate;
import com.ktc.playandroid.internet.bean.project.ProjectEssayData;
import com.ktc.playandroid.mvpcomponent.contract.HomeContract;
import com.ktc.playandroid.mvpcomponent.contract.LoginContract;
import com.ktc.playandroid.mvpcomponent.present.HomePresent;
import com.ktc.playandroid.ui.adapter.HomePagerAdapter;
import com.ktc.playandroid.ui.adapter.HomeProjectAdapter;
import com.ktc.playandroid.ui.baseui.BaseFragment;
import com.ktc.playandroid.ui.baseui.BaseResponeFragment;
import com.ktc.playandroid.util.ConstantClass;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeFragment extends BaseResponeFragment implements HomeContract.View {
    private static final String TAG = "HomeFragment";
    private HomePagerAdapter mHomePagerAdapter;
    private HomeProjectAdapter mHomeProjectAdapter;

    @Inject
    HomePresent mHomePresent;
    @BindView(R.id.vp_home_banner)
    ViewPager mViewPagerBanner;
    @BindView(R.id.scv_home_seach)
    SearchView mHomeSerch;
    @BindView(R.id.lv_home_list)
    ListView mHomeList;
    @BindView(R.id.tab_home_kind)
    TabLayout mHomeTab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        PlayAndroidApp.getInstance().getAppComponent().getHomeComonent()
                .inject(this);
        super.onCreate(savedInstanceState);
        if(mHomePresent!=null){
            mHomePresent.attachView(this);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHomePresent.getBanners();
        initViewData();
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

    @Override
    public BaseFragment getContex() {
        return this;
    }

    @Override
    public void showBanner(List<HomeBannerData> homeBannerDataList) {
        ArrayList<String> title = new ArrayList<>();
        ArrayList<String> background = new ArrayList<>();
        for(HomeBannerData homeBannerData : homeBannerDataList){
            title.add(homeBannerData.getTitle());
            background.add(homeBannerData.getImagePath());
        }
        mHomePagerAdapter = new HomePagerAdapter(getContext(),title,background);
        mViewPagerBanner.setAdapter(mHomePagerAdapter);
    }

    @Override
    public void setPresent(HomePresent present) {
    }
    public void initViewData(){
        mHomeTab.addTab(mHomeTab.newTab().setText(R.string.home_tab_essay));
        mHomeTab.addTab(mHomeTab.newTab().setText(R.string.home_tab_project));
        mHomeTab.addTab(mHomeTab.newTab().setText(R.string.home_tab_hot));
        switch (mHomeTab.getSelectedTabPosition()){
            case 0:
                mHomePresent.getEssayList(0);
                break;
            case 1:
                mHomePresent.getProjectList(0,294);
                break;
            case 2:
                break;
            default:
                break;
        }
        mHomeTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        mHomePresent.getEssayList(0);
                        break;
                    case 1:
                        mHomePresent.getProjectList(0,294);
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void showProjectList(List<ProjectEssayData> projectEssayData) {
        mHomeProjectAdapter = new HomeProjectAdapter(getContext(),
                null,projectEssayData,true);
        mHomeList.setAdapter(mHomeProjectAdapter);
    }

    @Override
    public void showEssayList(List<HomeEssaypageDate> homeEssayData) {
        mHomeProjectAdapter = new HomeProjectAdapter(getContext(),
                homeEssayData,null,false);
        mHomeList.setAdapter(mHomeProjectAdapter);
    }
}
