package com.huan.common.sdk.api.gankio.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by H_S on 2018/1/22.
 */

public class GankIoWelfareItem {

    @SerializedName("_id")
    private String _id;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("desc")
    private String desc;
    @SerializedName("publishedAt")
    private String publishedAt;
    @SerializedName("source")
    private String source;
    @SerializedName("type")
    private String type;
    @SerializedName("url")
    private String url;
    @SerializedName("used")
    private boolean used;
    @SerializedName("who")
    private String who;
    @SerializedName("images")
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getSource() {
        return source;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public boolean isUsed() {
        return used;
    }

    public String getWho() {
        return who;
    }

    public List<String> getImages() {
        return images;
    }

}
