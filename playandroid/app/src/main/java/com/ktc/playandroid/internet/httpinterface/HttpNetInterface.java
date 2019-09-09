package com.ktc.playandroid.internet.httpinterface;

import com.ktc.playandroid.internet.api.Api;
import com.ktc.playandroid.internet.bean.PlayHeader;
import com.ktc.playandroid.internet.bean.homepage.HomeBannerData;
import com.ktc.playandroid.internet.bean.homepage.HomeEssayData;
import com.ktc.playandroid.internet.bean.homepage.HomeFavnetData;
import com.ktc.playandroid.internet.bean.homepage.HomeHotkeyData;
import com.ktc.playandroid.internet.bean.mine.MineLoginData;
import com.ktc.playandroid.internet.bean.navigation.NavigationListData;
import com.ktc.playandroid.internet.bean.project.ProjectClassifyData;
import com.ktc.playandroid.internet.bean.project.ProjectEssayData;
import com.ktc.playandroid.internet.bean.project.ProjectHeader;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class HttpNetInterface {
    public Api mApi;

    @Inject
    HttpNetInterface(@Named("cach") Api pApi) {
        mApi = pApi;
    }

    /**
     * get homepage esssay list
     *
     * @param page
     * @return homepage essay list
     */
    public Observable<PlayHeader<HomeEssayData>> getHomeEssayList(int page) {
        return mApi.getHomeEssayList(page);
    }

    /**
     * get homepage banner list
     *
     * @param
     * @return homepage banner list
     */
    public Observable<PlayHeader<List<HomeBannerData>>> getHomeBannerList() {
        return mApi.getHomeBannerList();
    }

    /**
     * get homepage favorite net list
     *
     * @param
     * @return homepage favorite net list
     */
    public Observable<PlayHeader<HomeFavnetData>> getHomeFavnetList() {
        return mApi.getHomeFavnetList();
    }

    /**
     * get homepage favorite net list
     *
     * @param
     * @return homepage favorite net list
     */

    public Observable<PlayHeader<HomeHotkeyData>> getHomeHotkeyList() {
        return mApi.getHomeHotkeyList();
    }


    /**
     * get navigationpage  list
     *
     * @param
     * @return navigationpage  list
     */
    public Observable<PlayHeader<List<NavigationListData>>> getNavigationList() {
        return mApi.getNavigationList();
    }


    /**
     * get ProjectClassify list
     *
     * @param
     * @return ProjectClassify list
     */

    public Observable<PlayHeader<List<ProjectClassifyData>>> getProjectClassifyList() {
        return mApi.getProjectClassifyList();
    }

    /**
     * get ProjectEssay list
     *
     * @param page,cid
     * @return ProjectEssay list
     */

    public Observable<PlayHeader<ProjectHeader>> getProjectEssayList(int page, int cid) {
        return mApi.getProjectEssayList(page, cid);
    }


    /**
     * login
     *
     * @param name,password
     * @return user information
     */

    public Observable<PlayHeader<MineLoginData>> loginforuser(String name, String password) {
        return mApi.loginforuser(name, password);
    }

    /**
     * register
     *
     * @param name,password,repassword
     * @return user information
     */
    public Observable<PlayHeader<MineLoginData>> registerforuser(String name, String password, String repassword) {
        return mApi.registerforuser(name, password, repassword);
    }

    /**
     * LOGOUT
     *
     * @param
     * @return void
     */
    public Observable<PlayHeader> logoutforuser() {
        return mApi.logoutforuser();
    }
    /**
     * getDrawable
     *
     * @param fileurl
     * @return void
     */
    public Observable<ResponseBody> getDrawable(String fileurl){
        return mApi.getDrawable(fileurl);
    }
}
