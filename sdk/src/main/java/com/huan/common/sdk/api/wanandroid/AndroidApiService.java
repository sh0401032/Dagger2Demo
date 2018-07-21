package com.huan.common.sdk.api.wanandroid;

import com.huan.common.sdk.api.wanandroid.bean.Banner;
import com.huan.common.sdk.api.wanandroid.bean.HotKey;
import com.huan.common.sdk.api.wanandroid.bean.Nav;
import com.huan.common.sdk.api.wanandroid.bean.Pager;
import com.huan.common.sdk.api.wanandroid.bean.PagerChapter;
import com.huan.common.sdk.api.wanandroid.bean.TreeType;
import com.huan.common.sdk.api.wanandroid.bean.WebSite;
import com.huan.common.sdk.api.wanandroid.http.HttpResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by H_S on 2018/3/24.
 * wanandroid公开api
 */

public interface AndroidApiService {

    /**
     * 首页数据
     * http://www.wanandroid.com/article/list/0/json
     *
     * @param page page
     */

    @GET("/article/list/{page}/json")
    Observable<HttpResult<Pager>> getHomeList(@Path("page") int page);

    /**
     * 首页Banner
     *
     * @return BannerResponse
     */
    @GET("/banner/json")
    Observable<HttpResult<List<Banner>>> getBannerList();

    /**
     * 常用网站
     * http://www.wanandroid.com/friend/json
     */
    @GET("/friend/json")
    Observable<HttpResult<List<WebSite>>> getFriendWebSiteList();

    /**
     * 搜索热词
     * http://www.wanandroid.com/hotkey/json
     */
    @GET("/hotkey/json")
    Observable<HttpResult<List<HotKey>>> getHotKeyList();

    /**
     * 知识体系
     * http://www.wanandroid.com/tree/json
     */
    @GET("/tree/json")
    Observable<HttpResult<List<TreeType>>> getTreeTypeList();

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0/json?cid=168
     *
     * @param page page
     * @param cid  cid
     */
    @GET("/article/list/{page}/json")
    Observable<HttpResult<List<Pager>>> getArticleList(@Path("page") int page,
                                                       @Query("cid") int cid);


    /**
     * 导航数据
     * http://www.wanandroid.com/navi/json
     */
    @GET("/navi/json")
    Observable<HttpResult<List<Nav>>> getNavList();

    /**
     * 项目分类
     * http://www.wanandroid.com/project/tree/json
     */
    Observable<HttpResult<List<PagerChapter>>> getProjectChapterList();

    /**
     * 项目列表数据
     * http://www.wanandroid.com/project/list/1/json?cid=294
     *
     * @return
     */
    @GET("/project/list/{page}/json")
    Observable<HttpResult<List<Pager>>> getProjectList(@Path("page") int page,
                                                       @Query("cid") int cid);

    /**
     * 登录
     *
     * @param userName username
     * @param password password
     * @return Deferred<LoginResponse>
     */
    @POST("/user/login")
    @FormUrlEncoded
    Observable<HttpResult> loginWanAndroid(@Field("username") String userName,
                                           @Field("password") String password);

    /**
     * 注册
     *
     * @param username   username
     * @param password   password
     * @param repassowrd repassword
     * @return Deferred<LoginResponse>
     */
    @POST("/user/register")
    @FormUrlEncoded
    Observable<HttpResult> registerWanAndroid(@Field("username") String username,
                                              @Field("password") String password,
                                              @Field("repassword") String repassowrd);

    /**
     * 搜索
     * http://www.wanandroid.com/article/query/0/json
     *
     * @param page page
     * @param key  POST search key
     */
    @POST("/article/query/{page}/json")
    @FormUrlEncoded
    Observable<HttpResult<List<Pager>>> getSearchList(@Path("page") int page,
                                                      @Field("k") String key);

}
