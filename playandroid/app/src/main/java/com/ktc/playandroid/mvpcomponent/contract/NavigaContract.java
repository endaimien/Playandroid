package com.ktc.playandroid.mvpcomponent.contract;

import com.ktc.playandroid.internet.bean.navigation.NavigationListData;
import com.ktc.playandroid.mvpcomponent.basemvp.BaseMview;
import com.ktc.playandroid.mvpcomponent.basemvp.BasePresent;

import java.util.List;

public interface NavigaContract {
    interface View extends BaseMview<Presenter>{
         void showNavigattionData(List<NavigationListData> navigationListData);
    }
    interface Presenter extends BasePresent<NavigaContract.View>{
        void getNavigationData();
    }
}
