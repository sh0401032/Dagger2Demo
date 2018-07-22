package com.huan.dagger2demo.wanandroid.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by H_S on 2018/7/21.
 */

public class AutoHeightViewPager extends ViewPager {

    private Context context;
    private int height;
    private int matchSize;
    private boolean reset;
    private int resetHeight;

    public AutoHeightViewPager(Context context) {
        this(context, null);
    }

    public AutoHeightViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        //下面遍历所有child的高度
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int childHeight = measureHeight(heightMeasureSpec, child);
            if (childHeight > height) //采用最大的view的高度。
                height = childHeight;
            Log.d("huan", "child view " + child + " childHeight : " + childHeight + " height : " + height);
        }

        if (reset) {
            height = resetHeight;
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void resetHeight(int height) {
        resetHeight = height;
        reset = true;
        requestLayout();
    }

    public int getMatchSize() {
        return matchSize;
    }

    private int measureHeight(int measureSpec, View view) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            Log.d("huan", "EXACTLY : " + specSize);
            result = specSize;
            matchSize = specSize;
        } else {
            // set the height from the base view if available
            if (view != null) {
                result = view.getMeasuredHeight();
            }
            if (specMode == MeasureSpec.AT_MOST) {
                Log.d("huan", "AT_MOST : " + specSize + " measure: " + result);
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
