package com.roje.miao.music.utils;

import android.content.Context;

/**
 * dp、sp转换为px的工具类
 */

public class DisplayUtil {
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * @param context 上下文
     * @param pxValue px
     * @return dp
     */
    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    /**
     * 将dip或dp值转换为px值，保证尺寸不变
     * @param context 上细纹
     * @param dipValue dp
     * @return px
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
    /**
     * 将px值转换为sp值，保证文字大小不变
     * @param context 上下文
     * @param pxValue px
     * @return sp
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param context 上下文
     * @param spValue sp
     * @return px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}