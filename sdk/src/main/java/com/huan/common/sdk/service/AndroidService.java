package com.huan.common.sdk.service;

import com.huan.common.sdk.api.HttpClientHelper;
import com.huan.common.sdk.api.wanandroid.AndroidApiService;
import com.huan.common.sdk.api.wanandroid.http.HttpResultFunc;
import com.huan.common.sdk.api.wanandroid.http.HttpResult;
import com.huan.common.sdk.api.wanandroid.bean.Banner;
import com.huan.common.sdk.api.wanandroid.bean.HotKey;
import com.huan.common.sdk.api.wanandroid.bean.Nav;
import com.huan.common.sdk.api.wanandroid.bean.Pager;
import com.huan.common.sdk.api.wanandroid.bean.PagerChapter;
import com.huan.common.sdk.api.wanandroid.bean.TreeType;
import com.huan.common.sdk.api.wanandroid.bean.WebSite;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by H_S on 2018/3/24.
 */

public class AndroidService {

    private final static String BASE_SERVICE_URL = "http://www.wanandroid.com/";
    private AndroidApiService baseApiService;
    private static AndroidService instance;

    private AndroidService() {
        baseApiService = HttpClientHelper.getClient(BASE_SERVICE_URL).create(AndroidApiService.class);
    }

    public static AndroidService getInstance() {
        if (instance == null) {
            synchronized (AndroidService.class) {
                if (instance == null) instance = new AndroidService();
            }
        }
        return instance;
    }

    /**
     * 首页数据
     * http://www.wanandroid.com/article/list/0/json
     *
     * @param page page
     */

    public Observable<Pager> getHomeList(int page) {
        return baseApiService.getHomeList(page)
                .map(new HttpResultFunc<Pager>())
                .subscribeOn(Schedulers.io());
    }

    /**
     * 首页Banner
     *
     * @return BannerResponse
     */
    public Observable<List<Banner>> getBannerList() {
        return baseApiService.getBannerList()
                .map(new HttpResultFunc<List<Banner>>())
                .subscribeOn(Schedulers.io());
    }

    /**
     * 常用网站
     * http://www.wanandroid.com/friend/json
     */
    public Observable<List<WebSite>> getFriendWebSiteList() {
        return baseApiService.getFriendWebSiteList()
                .map(new HttpResultFunc<List<WebSite>>())
                .subscribeOn(Schedulers.io());
    }

    /**
     * 搜索热词
     * http://www.wanandroid.com/hotkey/json
     */
    public Observable<List<HotKey>> getHotKeyList() {
        return baseApiService.getHotKeyList()
                .map(new HttpResultFunc<List<HotKey>>())
                .subscribeOn(Schedulers.io());
    }

    /**
     * 知识体系
     * http://www.wanandroid.com/tree/json
     */
    public Observable<List<TreeType>> getTreeTypeList() {
        return baseApiService.getTreeTypeList()
                .map(new HttpResultFunc<List<TreeType>>())
                .subscribeOn(Schedulers.io());
    }

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0/json?cid=168
     *
     * @param page page
     * @param cid  cid
     */
    public Observable<List<Pager>> getArticleList(int page, int cid) {
        return baseApiService.getArticleList(page, cid)
                .map(new HttpResultFunc<List<Pager>>())
                .subscribeOn(Schedulers.io());
    }


    /**
     * 导航数据
     * http://www.wanandroid.com/navi/json
     */
    public Observable<List<Nav>> getNavList() {
        return baseApiService.getNavList()
                .map(new HttpResultFunc<List<Nav>>())
                .subscribeOn(Schedulers.io());
    }

    /**
     * 项目分类
     * http://www.wanandroid.com/project/tree/json
     */
    public Observable<List<PagerChapter>> getProjectChapterList() {
        return baseApiService.getProjectChapterList()
                .map(new HttpResultFunc<List<PagerChapter>>())
                .subscribeOn(Schedulers.io());
    }

    /**
     * 项目列表数据
     * http://www.wanandroid.com/project/list/1/json?cid=294
     *
     * @return
     */
    public Observable<List<Pager>> getProjectList(int page, int cid) {
        return baseApiService.getProjectList(page, cid)
                .map(new HttpResultFunc<List<Pager>>())
                .subscribeOn(Schedulers.io());
    }

    /**
     * 登录
     *
     * @param userName username
     * @param password password
     * @return Deferred<LoginResponse>
     */
    public Observable<HttpResult> loginWanAndroid(String userName, String password) {
        return baseApiService.loginWanAndroid(userName, password)
                .map(new HttpResultFunc())
                .subscribeOn(Schedulers.io());
    }

    /**
     * 注册
     *
     * @param username   username
     * @param password   password
     * @param repassowrd repassword
     * @return Deferred<LoginResponse>
     */
    public Observable<HttpResult> registerWanAndroid(String username,
                                                     String password,
                                                     String repassowrd) {
        return baseApiService.registerWanAndroid(username, password, repassowrd)
                .map(new HttpResultFunc())
                .subscribeOn(Schedulers.io());
    }

    /**
     * 搜索
     * http://www.wanandroid.com/article/query/0/json
     *
     * @param page page
     * @param key  POST search key
     */
    public Observable<List<Pager>> getSearchList(int page, String key) {
        return baseApiService.getSearchList(page, key)
                .map(new HttpResultFunc<List<Pager>>())
                .subscribeOn(Schedulers.io());
    }


}
