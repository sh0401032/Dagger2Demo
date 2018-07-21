package com.huan.common.sdk.api.wanandroid.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by H_S on 2018/3/24.
 */

public class Article {

    /**
     * apkLink :
     * author : lanzry
     * chapterId : 73
     * chapterName : 面试相关
     * collect : false
     * courseId : 13
     * desc :
     * envelopePic :
     * fresh : false
     * id : 2674
     * link : http://www.jianshu.com/p/5a272afb4c2e
     * niceDate : 2018-03-18
     * origin :
     * projectLink :
     * publishTime : 1521383343000
     * superChapterId : 195
     * superChapterName : 热门专题
     * tags : []
     * title : 一年经验-有赞面经
     * type : 0
     * visible : 1
     * zan : 0
     */
    @SerializedName("apkLink")
    private String apkLink;
    @SerializedName("author")
    private String author;
    @SerializedName("chapterId")
    private int chapterId;
    @SerializedName("chapterName")
    private String chapterName;
    @SerializedName("collect")
    private boolean collect;
    @SerializedName("courseId")
    private int courseId;
    @SerializedName("desc")
    private String desc;
    @SerializedName("envelopePic")
    private String envelopePic;
    @SerializedName("fresh")
    private boolean fresh;
    @SerializedName("id")
    private int id;
    @SerializedName("link")
    private String link;
    @SerializedName("niceDate")
    private String niceDate;
    @SerializedName("origin")
    private String origin;
    @SerializedName("projectLink")
    private String projectLink;
    @SerializedName("publishTime")
    private long publishTime;
    @SerializedName("superChapterId")
    private int superChapterId;
    @SerializedName("superChapterName")
    private String superChapterName;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private int type;
    @SerializedName("visible")
    private int visible;
    @SerializedName("zan")
    private int zan;
//    @SerializedName("")
//    private List<String> tags;

    public String getApkLink() {
        return apkLink;
    }

    public void setApkLink(String apkLink) {
        this.apkLink = apkLink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public boolean isFresh() {
        return fresh;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public int getSuperChapterId() {
        return superChapterId;
    }

    public void setSuperChapterId(int superChapterId) {
        this.superChapterId = superChapterId;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

//    public List<String> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<String> tags) {
//        this.tags = tags;
//    }
}
