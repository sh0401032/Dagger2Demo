package com.huan.common.sdk.api.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by H_S on 2018/1/21.
 */

public class ZhiHuDailyList {
    @SerializedName("date")
    private String date;
    @SerializedName("top_stories")
    private ArrayList<ZhiHuDailyItem> mZhihuDailyItems;
    @SerializedName("stories")
    private ArrayList<ZhiHuDailyItem> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<ZhiHuDailyItem> getZhihuDailyItems() {
        return mZhihuDailyItems;
    }

    public void setZhihuDailyItems(ArrayList<ZhiHuDailyItem> zhihuDailyItems) {
        this.mZhihuDailyItems = zhihuDailyItems;
    }

    public ArrayList<ZhiHuDailyItem> getStories() {
        return stories;
    }

    public void setStories(ArrayList<ZhiHuDailyItem> stories) {
        this.stories = stories;
    }

    @Override
    public String toString() {
        return "ZhihuDailyListBean{" +
                "date='" + date + '\'' +
                ", mZhihuDailyItems=" + mZhihuDailyItems +
                ", stories=" + stories +
                '}';
    }
}
