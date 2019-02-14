package com.roje.miao.music.view;

import android.content.Context;
import android.util.AttributeSet;

public class SquareImageView extends android.support.v7.widget.AppCompatImageView {
 
	public SquareImageView(Context context) {
		super(context);
	}
 
	public SquareImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
 
	public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
 
	}
 
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
 
		int childWidthSize = getMeasuredWidth();
		int childHeightSize = getMeasuredHeight();

		if (childWidthSize >= childHeightSize) {
			widthMeasureSpec = heightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeightSize,MeasureSpec.EXACTLY);
			super.onMeasure(widthMeasureSpec,heightMeasureSpec);
		} else {
			//高度和宽度一样
			heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}
}