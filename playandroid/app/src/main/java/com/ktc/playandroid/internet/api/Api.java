package com.ktc.playandroid.internet.api;

import com.ktc.playandroid.internet.bean.PlayHeader;
import com.ktc.playandroid.internet.bean.homepage.HomeBannerData;
import com.ktc.playandroid.internet.bean.homepage.HomeEssayData;

import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    public  static String HOST= "http://www.wanandroid.com/";

    /**
    * get homepage esssay list
    * @param page
    * @return homepage essay list
    * */
    @POST("article/list/{page}/json")
    public PlayHeader<HomeEssayData> getHomeEssayList(@Path("page") int page);

    /**
     * get homepage esssay list
     * @param NULL
     * @return homepage essay list
     * */
    @POST("article/list/{page}/json")
    public PlayHeader<HomeBannerData> getHomeBannerList();


}
