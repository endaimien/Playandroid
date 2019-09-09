package com.ktc.playandroid.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.widget.ListView;

import com.ktc.playandroid.R;
import com.ktc.playandroid.baseview.PlayAndroidApp;
import com.ktc.playandroid.drawview.LoadingCircleDialog;
import com.ktc.playandroid.mvpcomponent.contract.ProjectContract;
import com.ktc.playandroid.mvpcomponent.present.ProjectPresent;
import com.ktc.playandroid.ui.adapter.ProjectListAdapter;
import com.ktc.playandroid.ui.baseui.BaseFragment;
import com.ktc.playandroid.ui.baseui.BaseResponeFragment;
import com.ktc.playandroid.util.ConstantClass;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectFragment extends BaseResponeFragment implements ProjectContract.View {
    private static final String TAG = "ProjectFragment";
    public ProjectListAdapter mProjectListAdapter;

    @BindView(R.id.scv_project_seach)
    SearchView mSearchView;
    @BindView(R.id.tab_project_kind)
    TabLayout mTabLayout;
    @BindView(R.id.lv_project_list)
    ListView mProjectList;

    @Inject
    ProjectPresent mProjectPresent;

    public  LoadingCircleDialog mLoadingCircleDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlayAndroidApp.getInstance().getAppComponent().getProjectComponent().inject(this);
        if(mProjectPresent!=null){
            mProjectPresent.attachView(this);
        }
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
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ButterKnife.bind(this,view);
       // initLoadDialog();
       // showLoading();
        initView();
        showLoading();
    }
    @Override
    public BaseFragment getContex() {
        return this;
    }

    @Override
    public void showClassify(ArrayList<String> classsfyname,ArrayList<Integer> cid) {
        for(String name: classsfyname){
            mTabLayout.addTab(mTabLayout.newTab().setText(name));
        }
        mProjectPresent.getProject(1,cid.get(0));
        showNormal();
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mProjectPresent.getProject(1,cid.get(tab.getPosition()));
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
    public void showProject(ArrayList<String> classsfyname, ArrayList<String> projectContent, ArrayList<String> projectBackground) {
        Log.d(TAG,"fragment"+"showProject()");
        mProjectListAdapter = new ProjectListAdapter(this.getContext(),classsfyname,projectContent,projectBackground);
        mProjectList.setAdapter(mProjectListAdapter);
    }

    @Override
    public void setPresent(ProjectPresent present) {

    }

    public void initLoadDialog(){
        LoadingCircleDialog.Builder dialogBuilder = new LoadingCircleDialog.Builder(getContext())
                .setMessage("加载中...")
                .setCancelable(true)
                .setCancelOutside(false);
        mLoadingCircleDialog = dialogBuilder.create();
        mLoadingCircleDialog.show();


    }

    public void initView(){
        initSearView();
        initTabPage();
    }

    public void initSearView(){
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setImeOptions(R.string.project_search_key);
        mSearchView.setQueryHint(getString(R.string.project_search_hint));
    }
    public void initTabPage(){
        mProjectPresent.getClassify();
        /*mTabLayout.addTab(mTabLayout.newTab().setText("tab 1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("tab 2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("tab 3"));
        mTabLayout.addTab(mTabLayout.newTab().setText("tab 4"));
        mTabLayout.addTab(mTabLayout.newTab().setText("tab 5"));
        mTabLayout.addTab(mTabLayout.newTab().setText("tab 6"));
        mTabLayout.addTab(mTabLayout.newTab().setText("tab 7"));*/

    }
}
