package com.ktc.playandroid.internet.api;

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

//import javax.validation.constraints.Null;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {
    public static String HOST = "https://www.wanandroid.com/";

    /**
     * get homepage esssay list
     *
     * @param page
     * @return homepage essay list
     */
    @GET("article/list/{page}/json")
    Observable<PlayHeader<HomeEssayData>> getHomeEssayList(@Path("page") int page);

    /**
     * get homepage banner list
     *
     * @param
     * @return homepage banner list
     */
    @GET("banner/json")
    Observable<PlayHeader<List<HomeBannerData>>> getHomeBannerList();

    /**
     * get homepage favorite net list
     *
     * @param
     * @return homepage favorite net list
     */
    @GET("friend/json")
    Observable<PlayHeader<HomeFavnetData>> getHomeFavnetList();

    /**
     * get homepage favorite net list
     *
     * @param
     * @return homepage favorite net list
     */
    @GET("hotkey/json")
    Observable<PlayHeader<HomeHotkeyData>> getHomeHotkeyList();


    /**
     * get navigationpage  list
     *
     * @param
     * @return navigationpage  list
     */
    @GET("navi/json")
    Observable<PlayHeader<List<NavigationListData>>> getNavigationList();


    /**
     * get ProjectClassify list
     *
     * @param
     * @return ProjectClassify list
     */
    @GET("project/tree/json")
    Observable<PlayHeader<List<ProjectClassifyData>>> getProjectClassifyList();

    /**
     * get ProjectEssay list
     *
     * @param page,cid
     * @return ProjectEssay list
     */
    @GET("project/list/{page}/json")
    Observable<PlayHeader<ProjectHeader>> getProjectEssayList(@Path("page") int page, @Query("cid") int cid);


    /**
     * login
     *
     * @param name,password
     * @return user information
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<PlayHeader<MineLoginData>> loginforuser(@Field("username") String name, @Field("password") String password);

    /**
     * register
     *
     * @param name,password,repassword
     * @return user information
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<PlayHeader<MineLoginData>> registerforuser(@Field("username") String name, @Field("password") String password, @Field("repassword") String repassword);

    /**
     * LOGOUT
     *
     * @param
     * @return void
     */
    @GET("user/logout/json")
    Observable<PlayHeader> logoutforuser();


    @GET
    Observable<ResponseBody> getDrawable(@Url String fileurl);
}
