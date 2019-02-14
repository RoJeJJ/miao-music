package com.roje.miao.music.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.roje.miao.music.R;
import com.roje.miao.music.utils.DisplayUtil;


/**
 * Created by leo on 17/3/14.
 */

public class RoundImageView extends AppCompatImageView {

    @IntDef({MODE_NONE,MODE_CIRCLE,MODE_ROUND})
    @interface ImageMode {}
    /**
     * 圆形模式
     */
    public static final int MODE_CIRCLE = 1;
    /**
     * 普通模式
     */
    public static final int MODE_NONE = 0;
    /**
     * 圆角模式
     */
    public static final int MODE_ROUND = 2;
    private Paint mPaint;
    @ImageMode
    private int currMode = 0;

    /**
     * 圆角半径
     */
    private int currRound;

    public RoundImageView(Context context) {
        this(context,null,0);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttrs(context, attrs, defStyleAttr);
        init(context);
    }

    private void obtainStyledAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView, defStyleAttr, 0);
        currMode = a.hasValue(R.styleable.RoundImageView_type) ? a.getInt(R.styleable.RoundImageView_type, MODE_NONE) : MODE_NONE;
        currRound = a.hasValue(R.styleable.RoundImageView_radius) ? a.getDimensionPixelSize(R.styleable.RoundImageView_radius, currRound) : currRound;
        a.recycle();
    }

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        currRound = DisplayUtil.dip2px(context,8);
    }

    public void setImageMode(@ImageMode int mode) {
        this.currMode = mode;
        invalidate();
    }

    public void setRadius(int radius) {
        currRound = radius;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //当模式为圆形模式的时候，我们强制让宽高一致
        if (currMode == MODE_CIRCLE) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int result = Math.min(getMeasuredHeight(), getMeasuredWidth());
            setMeasuredDimension(result, result);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable mDrawable = getDrawable();
        Matrix mDrawMatrix = getImageMatrix();
        if (mDrawable == null) {
            return; // couldn't resolve the URI
        }

        if (mDrawable.getIntrinsicWidth() == 0 || mDrawable.getIntrinsicHeight() == 0) {
            return;     // nothing to draw (empty bounds)
        }

        if (mDrawMatrix == null && getPaddingTop() == 0 && getPaddingLeft() == 0) {
            mDrawable.draw(canvas);
        } else {
            final int saveCount = canvas.getSaveCount();
            canvas.save();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                if (getCropToPadding()) {
                    final int scrollX = getScrollX();
                    final int scrollY = getScrollY();
                    canvas.clipRect(scrollX + getPaddingLeft(), scrollY + getPaddingTop(),
                            scrollX + getRight() - getLeft() - getPaddingRight(),
                            scrollY + getBottom() - getTop() - getPaddingBottom());
                }
            }
            canvas.translate(getPaddingLeft(), getPaddingTop());
            if (currMode == MODE_CIRCLE) {//当为圆形模式的时候
                Bitmap bitmap = drawable2Bitmap(mDrawable);
                mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                int side = bitmap.getWidth() >= bitmap.getHeight() ? bitmap.getHeight() : bitmap.getWidth();
                canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, side / 2f, mPaint);
            } else if (currMode == MODE_ROUND) {//当为圆角模式的时候
                Bitmap bitmap = drawable2Bitmap(mDrawable);
                mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                canvas.drawRoundRect(new RectF(getPaddingLeft(), getPaddingTop(),
                                bitmap.getWidth() - getPaddingRight(),
                                bitmap.getHeight() - getPaddingBottom()),
                        currRound, currRound, mPaint);
            } else {
                if (mDrawMatrix != null) {
                    canvas.concat(mDrawMatrix);
                }
                mDrawable.draw(canvas);
            }
            canvas.restoreToCount(saveCount);
        }
    }

    /**
     * drawable转换成bitmap
     */
    private Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(getWidth() - getPaddingLeft() - getPaddingRight()
                , getHeight()-getPaddingTop()-getPaddingBottom(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        //根据传递的scaletype获取matrix对象，设置给bitmap
        Matrix matrix = getImageMatrix();
        if (matrix != null) {
            canvas.concat(matrix);
        }
        drawable.draw(canvas);
        return bitmap;
    }
}