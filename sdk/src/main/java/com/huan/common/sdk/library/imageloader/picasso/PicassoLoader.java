package com.huan.common.sdk.library.imageloader.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.huan.common.sdk.library.imageloader.ILoader;
import com.huan.common.sdk.library.imageloader.LoaderConfig;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;

import java.io.File;


public class PicassoLoader implements ILoader {
    private volatile static Picasso sPicassoSingleton;
    private final String PICASSO_CACHE = "picasso-cache";
    private static LruCache sLruCache;
    private static Context mContext;

    @Override
    public void init(Context context) {
        mContext = context;
        sLruCache = new LruCache(context);
    }

    private static Picasso getPicasso() {
        if (sPicassoSingleton == null) {
            synchronized (PicassoLoader.class) {
                if (sPicassoSingleton == null) {
                    sPicassoSingleton = new Picasso.Builder(mContext).memoryCache(sLruCache).build();
                }
            }
        }
        return sPicassoSingleton;
    }

    @Override
    public void clearMemoryCache() {
        sLruCache.clear();
    }

    @Override
    public void clearDiskCache(String cachePath) {
        File diskFile = new File(cachePath, PICASSO_CACHE);
        if (diskFile.exists()) {
            //这边自行写删除代码
//	        FileUtil.deleteFile(diskFile);
        }
    }


    @Override
    public void loadImage(LoaderConfig config) {
        RequestCreator requestCreator = null;
        if (config.getUrl() != null) {
            requestCreator = getPicasso().load(config.getUrl());
        } else if (config.getFile() != null) {
            requestCreator = getPicasso().load(config.getFile());
        } else if (config.getDrawableResId() != 0) {
            requestCreator = getPicasso().load(config.getDrawableResId());
        } else if (config.getUri() != null) {
            requestCreator = getPicasso().load(config.getUri());
        }

        if (requestCreator == null) {
            throw new NullPointerException("requestCreator must not be null");
        }
        if (config.getTargetWidth() > 0 && config.getTargetWidth() > 0) {
            requestCreator.resize(config.getTargetWidth(), config.getTargetHeight());
        }
        if (config.isCenterCrop()) {
            requestCreator.centerInside();
        } else if (config.isCenterCrop()) {
            requestCreator.centerCrop();
        }
        if (config.getConfig() != null) {
            requestCreator.config(config.getConfig());
        }
        if (config.getErrorResId() != 0) {
            requestCreator.error(config.getErrorResId());
        }
        if (config.getPlaceholderResId() != 0) {
            requestCreator.placeholder(config.getPlaceholderResId());
        }
        if (config.getBitmapAngle() != 0) {
            requestCreator.transform(new PicassoTransformation(config.getBitmapAngle()));
        }

        if (config.getDegrees() != 0) {
            requestCreator.rotate(config.getDegrees());
        }

        if (config.getTargetView() instanceof ImageView) {
            requestCreator.into(((ImageView) config.getTargetView()));
        }
        if (config.getCallBack() != null) {
            requestCreator.into(new PicassoTarget(config.getCallBack()));
        }
    }

    class PicassoTarget implements Target {
        LoaderConfig.BitmapCallBack callBack;

        protected PicassoTarget(LoaderConfig.BitmapCallBack callBack) {
            this.callBack = callBack;
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            if (this.callBack != null) {
                this.callBack.onBitmapLoaded(bitmap);
            }
        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            if (this.callBack != null) {
                this.callBack.onBitmapFailed(e);
            }
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    }

    class PicassoTransformation implements Transformation {
        private float bitmapAngle;

        protected PicassoTransformation(float corner) {
            this.bitmapAngle = corner;
        }

        @Override
        public Bitmap transform(Bitmap source) {
            float roundPx = bitmapAngle;//圆角的横向半径和纵向半径
            Bitmap output = Bitmap.createBitmap(source.getWidth(),
                    source.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, source.getWidth(), source.getHeight());
            final RectF rectF = new RectF(rect);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(source, rect, rect, paint);
            source.recycle();
            return output;
        }

        @Override
        public String key() {
            return "bitmapAngle()";
        }
    }

}
