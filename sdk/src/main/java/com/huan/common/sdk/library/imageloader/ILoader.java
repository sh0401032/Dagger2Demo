package com.huan.common.sdk.library.imageloader;

import android.content.Context;

public interface ILoader {

    void init(Context context);

    void loadImage(LoaderConfig options);

    /**
     * 清理内存缓存
     */
    void clearMemoryCache();

    /**
     * 清理磁盘缓存
     */
    void clearDiskCache(String cachePath);

}
