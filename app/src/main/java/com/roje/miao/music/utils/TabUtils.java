package com.roje.miao.music.utils;

import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

public class TabUtils {


    public static void setIndicator(TabLayout tabLayout,int tabCount) {
        try{
            //拿到tabLayout的mTabStrip属性
            LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
            for(int i = 0; i < mTabStrip.getChildCount(); i++){
                View tabView = mTabStrip.getChildAt(i);
                //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                mTextViewField.setAccessible(true);
                TextView mTextView = (TextView) mTextViewField.get(tabView);
                tabView.setPadding(0, 0, 0, 0);
                //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                int width;
                width = mTextView.getWidth();
                if(width == 0){
                    mTextView.measure(0, 0);
                    width = mTextView.getMeasuredWidth();
                }
                //设置tab左右间距
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                int margin = (tabLayout.getContext().getResources().getDisplayMetrics().widthPixels / tabCount - width) / 2;
                params.width = width;
                params.leftMargin = margin;
                params.rightMargin = margin;
                tabView.setLayoutParams(params);
                tabView.invalidate();
            }
        }catch(NoSuchFieldException e){
            e.printStackTrace();
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }
    }
}
