package com.ktc.playandroid.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ktc.playandroid.R;
import com.ktc.playandroid.ui.adapter.MainPageAdapter;
import com.ktc.playandroid.ui.baseui.BaseActivity;
import com.ktc.playandroid.ui.fragment.HomeFragment;
import com.ktc.playandroid.ui.fragment.MineFragment;
import com.ktc.playandroid.ui.fragment.NavigationFragment;
import com.ktc.playandroid.ui.fragment.ProjectFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnPageChange;

public class MainActivity extends BaseActivity {

    @BindView(R.id.vp_main)
    ViewPager mViewPage;

    @BindViews({R.id.lin_hometab,R.id.lin_navtab,R.id.lin_protab,R.id.lin_minitab})
    List<LinearLayout> mMaintabList;
            //mHomlinTab,mNavlinTab,mProlinTab,mMinlinTab;

    @BindViews({R.id.tv_hometab_num,R.id.tv_navitab_num,R.id.tv_projecttab_num,R.id.tv_minetab_num})
    List<TextView> mBottomtabNum;
    //TextView mHomtvtabnum,mNavtvtabnum,mProtvtabnum,mMintvtabnum;;;
    public ArrayList<Fragment> mFragments;

    public MainPageAdapter mMainPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragList();
        initView();
        initStatus();
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
       });*/
    }

    public void initFragList(){
        mFragments = new ArrayList<Fragment>();
        mFragments.add(HomeFragment.getInstance(0,null));
        mFragments.add(MineFragment.getInstance(0,null));
        mFragments.add(ProjectFragment.getInstance(0,null));
        mFragments.add(NavigationFragment.getInstance(0,null));
    }
    public void initView(){
       mMainPageAdapter = new MainPageAdapter(getSupportFragmentManager(),mFragments);
       mViewPage.setAdapter(mMainPageAdapter);
       mViewPage.addOnPageChangeListener(new ViewPageChange());
       for(int i=0;i<4;i++){
           mMaintabList.get(i).setOnClickListener(new OnLinClickListener());
       }


    }
    public void initStatus(){
        mViewPage.setCurrentItem(0);
        mMaintabList.get(0).setSelected(true);
    }
    public class ViewPageChange implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.d("wy",state+"");
            if(state == 2){
                switch (mViewPage.getCurrentItem()){
                    case 0:
                        resetSelect(0);
                        break;
                    case 1:
                        resetSelect(1);
                        break;
                    case 2:
                        resetSelect(2);
                        break;
                    case 3:
                        resetSelect(3);
                        break;
                        default:
                            break;
                }
            }
        }
    }

    public void resetSelect(int positon){
        for(int i=0;i<4;i++){
            mMaintabList.get(i).setSelected(false);
        }
        for(int i=0;i<4;i++){
            mBottomtabNum.get(i).setVisibility(View.GONE);
        }
        mMaintabList.get(positon).setSelected(true);
        mBottomtabNum.get(positon).setVisibility(View.VISIBLE);
        /*mHomlinTab.setSelected(false);
        mNavlinTab.setSelected(false);
        mProlinTab.setSelected(false);
        mMinlinTab.setSelected(false);
        mHomtvtabnum.setVisibility(View.GONE);
        mNavtvtabnum.setVisibility(View.GONE);
        mProtvtabnum.setVisibility(View.GONE);
        mMintvtabnum.setVisibility(View.GONE);*/
    }
    public class OnLinClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.lin_hometab:
                    resetSelect(0);
                    mViewPage.setCurrentItem(0);
                    break;
                case R.id.lin_navtab:
                    resetSelect(1);
                    mViewPage.setCurrentItem(1);
                    break;
                case R.id.lin_protab:
                    resetSelect(2);
                    mViewPage.setCurrentItem(2);
                    break;
                case R.id.lin_minitab:
                    resetSelect(3);
                    mViewPage.setCurrentItem(3);
                    break;
                    default:
                        break;
            }
        }
    }

}
