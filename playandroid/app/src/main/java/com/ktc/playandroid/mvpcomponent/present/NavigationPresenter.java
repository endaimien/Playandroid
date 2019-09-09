package com.ktc.playandroid.mvpcomponent.present;

import android.util.Log;

import com.ktc.playandroid.internet.bean.PlayHeader;
import com.ktc.playandroid.internet.bean.navigation.NavigationListData;
import com.ktc.playandroid.internet.httpinterface.HttpNetInterface;
import com.ktc.playandroid.mvpcomponent.contract.HomeContract;
import com.ktc.playandroid.mvpcomponent.contract.NavigaContract;
import com.ktc.playandroid.util.RxjarUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class NavigationPresenter implements NavigaContract.Presenter {
    public NavigaContract.View mView;
    @Inject
    HttpNetInterface mHttpnet;
    @Inject
    public NavigationPresenter(){}
    @Override
    public void getNavigationData() {
        mHttpnet.getNavigationList()
                .compose(RxjarUtils.rxScheduleTrans())
                .compose(RxjarUtils.handResuilt())
                .subscribe(new Observer<List<NavigationListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<NavigationListData> navigationListData) {
                        Log.d("wy","showNavigattionData"+navigationListData.get(0).getName()+navigationListData.get(0).getArticles().get(0).getTitle());
                      mView.showNavigattionData(navigationListData);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void attachView(NavigaContract.View pView) {
       mView = pView;
    }

    @Override
    public void detachView() {

    }
}
