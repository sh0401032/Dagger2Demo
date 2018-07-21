package com.huan.common.sdk.api.gankio.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by H_S on 2018/1/22.
 */

public class GankIoDay {
    @SerializedName("error")
    private boolean error;
    @SerializedName("results")
    private ResultsBean results;
    @SerializedName("category")
    private List<String> category;

    public static class ResultsBean {
        /**
         * _id : 56cc6d23421aa95caa707a69
         * createdAt : 2015-08-06T07:15:52.65Z
         * desc : 类似Link Bubble的悬浮式操作设计
         * images : ["http://img.gank.io/2f0b6c5f-6de7-4ba3-94ad-98bf721ee447"]
         * source : web
         * publishedAt : 2015-08-07T03:57:48.45Z
         * type : Android
         * url : https://github.com/recruit-lifestyle/FloatingView
         * used : true
         * who : mthli
         */

        @SerializedName("Android")
        private List<GankIoDayItem> Android;

        @SerializedName("iOS")
        private List<GankIoDayItem> iOS;

        @SerializedName("前端")
        private List<GankIoDayItem> front;

        @SerializedName("App")
        private List<GankIoDayItem> app;

        @SerializedName("休息视频")
        private List<GankIoDayItem> restMovie;

        @SerializedName("拓展资源")
        private List<GankIoDayItem> resource;

        @SerializedName("瞎推荐")
        private List<GankIoDayItem> recommend;

        @SerializedName("福利")
        private List<GankIoDayItem> welfare;


        public List<GankIoDayItem> getAndroid() {
            return Android;
        }

        public List<GankIoDayItem> getiOS() {
            return iOS;
        }

        public List<GankIoDayItem> getRestMovie() {
            return restMovie;
        }

        public List<GankIoDayItem> getResource() {
            return resource;
        }

        public List<GankIoDayItem> getRecommend() {
            return recommend;
        }

        public List<GankIoDayItem> getWelfare() {
            return welfare;
        }

        public List<GankIoDayItem> getFront() {
            return front;
        }

        public List<GankIoDayItem> getApp() {
            return app;
        }
    }

    public boolean isError() {
        return error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public List<String> getCategory() {
        return category;
    }
}
