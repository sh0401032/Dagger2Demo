package com.huan.common.sdk.library.imageloader;

import android.content.Context;
import android.content.res.Configuration;

import com.huan.common.sdk.library.imageloader.glide.GlideLoader;
import com.huan.common.sdk.library.imageloader.picasso.PicassoLoader;


/**
 * Created by doudou on 2017/4/10.
 */

public class GlobalConfig {

    public static Context context;
    private static ILoader loader;

    /**
     * 屏幕高度
     */
    private static int winHeight;

    /**
     * 屏幕宽度
     */
    private static int winWidth;


    public static int cacheMaxSize;

    public static void init(Context context) {
        GlobalConfig.context = context;
        getLoader().init(context);
    }

    public static ILoader getLoader() {
        if (loader == null) {
            loader = new PicassoLoader();
        }
        return loader;
    }

    public static int getWinHeight() {
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return winHeight < winWidth ? winHeight : winWidth;
        } else if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return winHeight > winWidth ? winHeight : winWidth;
        }
        return winHeight;
    }

    public static int getWinWidth() {
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return winHeight > winWidth ? winHeight : winWidth;
        } else if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return winHeight < winWidth ? winHeight : winWidth;
        }
        return winWidth;
    }

}
