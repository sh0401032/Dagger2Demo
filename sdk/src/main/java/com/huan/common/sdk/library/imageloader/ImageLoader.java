package com.huan.common.sdk.library.imageloader;

import android.content.Context;


/**
 * 图片加载类
 * 策略或者静态代理模式，开发者只需要关心ImageLoader + LoaderConfig
 */

public class ImageLoader {

    public static void init(Context context) {
        GlobalConfig.init(context);
    }

    public static LoaderConfig with(Context context) {
        return new LoaderConfig(context);
    }

    public ILoader getImageLoader() {
        return GlobalConfig.getLoader();
    }

    /**
     * 清理内存缓存
     */
    public void clearMemoryCache() {
        getImageLoader().clearMemoryCache();
    }

    /**
     * 清理磁盘缓存
     */
    public void clearDiskCache(String cachePath) {
        getImageLoader().clearDiskCache(cachePath);
    }

}
