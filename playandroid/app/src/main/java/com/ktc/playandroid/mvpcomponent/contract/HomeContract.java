package com.ktc.playandroid.mvpcomponent.contract;

import com.ktc.playandroid.internet.bean.homepage.HomeBannerData;
import com.ktc.playandroid.internet.bean.homepage.HomeEssayData;
import com.ktc.playandroid.internet.bean.homepage.HomeEssaypageDate;
import com.ktc.playandroid.internet.bean.project.ProjectEssayData;
import com.ktc.playandroid.mvpcomponent.basemvp.BaseMview;
import com.ktc.playandroid.mvpcomponent.basemvp.BasePresent;
import com.ktc.playandroid.mvpcomponent.present.HomePresent;

import java.util.List;

public interface HomeContract {
    public interface View extends BaseMview<HomePresent>{
        void showBanner(List<HomeBannerData> homeBannerDataList);
        void showProjectList(List<ProjectEssayData> projectEssayData);
        void showEssayList(List<HomeEssaypageDate> homeEssayData);
    };
    public interface Present extends BasePresent<HomeContract.View>{
        void getBanners();
        void getEssayList(int page);
        void getProjectList(int page,int classify);
    }
}
