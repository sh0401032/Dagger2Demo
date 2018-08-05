package com.huan.common.sdk.library.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.view.View;

import java.io.File;

/**
 * 该类为图片加载框架的通用属性封装，不能耦合任何一方的框架
 */
public class LoaderConfig {
    private int placeholderResId;// 占位图
    private int errorResId;
    private boolean isCenterCrop;
    private boolean isCenterInside;
    private boolean skipLocalCache; //是否缓存到本地
    private boolean skipNetCache;
    private Bitmap.Config config = Bitmap.Config.RGB_565;
    private int targetWidth;
    private int targetHeight;
    private float bitmapAngle; //圆角角度
    private float degrees; //旋转角度.注意:picasso针对三星等本地图片，默认旋转回0度，即正常位置。此时不需要自己rotate
    private Drawable placeholder;
    private View targetView;//targetView展示图片
    private BitmapCallBack callBack;
    private String url;
    private File file;
    private int drawableResId;
    private Uri uri;
    private ILoader loader;//实时切换图片加载库
    private final Context mContext;
    private int width; // 图片显示宽度
    private int height; // 图片显示高度


    public LoaderConfig(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    public void load(Uri uri) {
        this.uri = uri;
    }

    public LoaderConfig load(String url) {
        this.url = url;
        return this;
    }

    public LoaderConfig load(File file) {
        this.file = file;
        return this;
    }

    public LoaderConfig load(int drawableResId) {
        this.drawableResId = drawableResId;
        return this;
    }


    public void into(View targetView) {
        this.targetView = targetView;
        GlobalConfig.getLoader().loadImage(this);
    }


    public interface BitmapCallBack {

        void onBitmapLoaded(Bitmap bitmap);

        void onBitmapFailed(Exception e);
    }

    public void setBitmapCallBack(BitmapCallBack callBack) {
        this.callBack = callBack;
    }

    public int getWidth() {
        if (width <= 0) {
            //先去imageview里取,如果为0,则赋值成matchparent
            if (targetView != null) {
                width = targetView.getMeasuredWidth();
            }
            if (width <= 0) {
                width = GlobalConfig.getWinWidth();
            }
        }

        return width;
    }

    public int getHeight() {
        if (height <= 0) {
            //先去imageview里取,如果为0,则赋值成matchparent
            if (targetView != null) {
                height = targetView.getMeasuredWidth();
            }
            if (height <= 0) {
                height = GlobalConfig.getWinHeight();
            }
        }
        return height;
    }

    /*******************************************配置信息*******************************************/
    public LoaderConfig loader(ILoader imageLoader) {
        this.loader = imageLoader;
        return this;
    }

    public LoaderConfig placeholder(@DrawableRes int placeholderResId) {
        this.placeholderResId = placeholderResId;
        return this;
    }

    public LoaderConfig placeholder(Drawable placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public LoaderConfig error(@DrawableRes int errorResId) {
        this.errorResId = errorResId;
        return this;
    }

    public LoaderConfig centerCrop() {
        isCenterCrop = true;
        return this;
    }

    public LoaderConfig centerInside() {
        isCenterInside = true;
        return this;
    }

    public LoaderConfig config(Bitmap.Config config) {
        this.config = config;
        return this;
    }

    public LoaderConfig resize(int targetWidth, int targetHeight) {
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
        return this;
    }

    public LoaderConfig angle(float bitmapAngle) {
        this.bitmapAngle = bitmapAngle;
        return this;
    }

    public LoaderConfig skipLocalCache(boolean skipLocalCache) {
        this.skipLocalCache = skipLocalCache;
        return this;
    }

    public LoaderConfig skipNetCache(boolean skipNetCache) {
        this.skipNetCache = skipNetCache;
        return this;
    }

    public LoaderConfig rotate(float degrees) {
        this.degrees = degrees;
        return this;
    }


    public int getPlaceholderResId() {
        return placeholderResId;
    }

    public int getErrorResId() {
        return errorResId;
    }

    public boolean isCenterCrop() {
        return isCenterCrop;
    }

    public boolean isCenterInside() {
        return isCenterInside;
    }

    public boolean isSkipLocalCache() {
        return skipLocalCache;
    }

    public boolean isSkipNetCache() {
        return skipNetCache;
    }

    public Bitmap.Config getConfig() {
        return config;
    }

    public int getTargetWidth() {
        return targetWidth;
    }

    public int getTargetHeight() {
        return targetHeight;
    }

    public float getBitmapAngle() {
        return bitmapAngle;
    }

    public float getDegrees() {
        return degrees;
    }

    public Drawable getPlaceholder() {
        return placeholder;
    }

    public View getTargetView() {
        return targetView;
    }

    public BitmapCallBack getCallBack() {
        return callBack;
    }

    public String getUrl() {
        return url;
    }

    public File getFile() {
        return file;
    }

    public int getDrawableResId() {
        return drawableResId;
    }

    public Uri getUri() {
        return uri;
    }

    public ILoader getLoader() {
        return loader;
    }


}


