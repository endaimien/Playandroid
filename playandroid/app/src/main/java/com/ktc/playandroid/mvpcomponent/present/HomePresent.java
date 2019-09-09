package com.ktc.playandroid.mvpcomponent.present;

import com.ktc.playandroid.internet.bean.homepage.HomeBannerData;
import com.ktc.playandroid.internet.bean.homepage.HomeEssayData;
import com.ktc.playandroid.internet.bean.project.ProjectHeader;
import com.ktc.playandroid.internet.httpinterface.HttpNetInterface;
import com.ktc.playandroid.mvpcomponent.contract.HomeContract;
import com.ktc.playandroid.util.RxjarUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomePresent implements HomeContract.Present {
    public HomeContract.View mView;
    @Inject
    HttpNetInterface mHttpNet;
    @Inject
    public HomePresent(){}
    @Override
    public void getBanners() {
        mHttpNet.getHomeBannerList()
                .compose(RxjarUtils.rxScheduleTrans())
                .compose(RxjarUtils.handResuilt())
                .subscribe(new Observer<List<HomeBannerData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<HomeBannerData> homeBannerData) {
                            mView.showBanner(homeBannerData);
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
    public void getEssayList(int page) {
        mHttpNet.getHomeEssayList(page)
                .compose(RxjarUtils.rxScheduleTrans())
                .compose(RxjarUtils.handResuilt())
                .subscribe(new Observer<HomeEssayData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeEssayData homeEssayData) {
                        mView.showEssayList(homeEssayData.getDatas());
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
    public void getProjectList(int page, int classify) {
        mHttpNet.getProjectEssayList(page,classify).compose(RxjarUtils.rxScheduleTrans())
                .compose(RxjarUtils.handResuilt())
                .subscribe(new Observer<ProjectHeader>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProjectHeader projectHeader) {
                          mView.showProjectList(projectHeader.getDatas());
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
    public void attachView(HomeContract.View pView) {
        mView = pView;
    }

    @Override
    public void detachView() {

    }
}
