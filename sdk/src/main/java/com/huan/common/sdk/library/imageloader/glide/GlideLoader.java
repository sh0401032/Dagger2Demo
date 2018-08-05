package com.huan.common.sdk.library.imageloader.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.huan.common.sdk.library.imageloader.GlobalConfig;
import com.huan.common.sdk.library.imageloader.ILoader;
import com.huan.common.sdk.library.imageloader.LoaderConfig;


/**
 * Created by H_S on 2018/8/5.
 */

public class GlideLoader implements ILoader {

    private GlideBuilder builder;

    @Override
    public void init(Context context) {
        builder = new GlideBuilder(context);
    }

    @Override
    public void loadImage(final LoaderConfig config) {
        RequestManager requestManager = Glide.with(config.getContext());
        DrawableTypeRequest request = getDrawableTypeRequest(config, requestManager);
        if (request == null) return;
        if (config.getCallBack() != null) {
            SimpleTarget target = new SimpleTarget<Bitmap>(config.getWidth(), config.getHeight()) {
                @Override
                public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                    config.getCallBack().onBitmapLoaded(bitmap);
                }
            };
            request.asBitmap().into(target);
        } else {
            if (config.getPlaceholderResId() >= 0) {
                request.placeholder(config.getPlaceholderResId());
            }
            if (config.getErrorResId() > 0) {
                request.error(config.getErrorResId());
            }

            if (config.getTargetView() instanceof ImageView) {
                request.into((ImageView) config.getTargetView());
            }

            if (config.isCenterCrop()) {
                request.centerCrop();
            } else if (config.isCenterInside()) {
                request.fitCenter();
            }

        }
    }

    @Nullable
    private DrawableTypeRequest getDrawableTypeRequest(LoaderConfig config, RequestManager requestManager) {
        DrawableTypeRequest request = null;
        if (!TextUtils.isEmpty(config.getUrl())) {
            request = requestManager.load(config.getUrl());
        } else if (config.getDrawableResId() > 0) {
            request = requestManager.load(config.getDrawableResId());
        } else if (config.getFile() != null) {
            request = requestManager.load(config.getFile());
        }
        return request;
    }

    @Override
    public void clearMemoryCache() {
        Glide.get(GlobalConfig.context).clearMemory();
    }

    @Override
    public void clearDiskCache(String cachePath) {
        Glide.get(GlobalConfig.context).clearDiskCache();
    }
}
