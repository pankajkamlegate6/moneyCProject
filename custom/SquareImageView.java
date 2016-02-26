package com.moneycontrol.handheld.custom;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.divum.MoneyControl.R;

public class SquareImageView extends ImageView {
	int widthPercentage = 0;

	public SquareImageView(Context context) {
		super(context);
	}

	public SquareImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, 0);
	}

	public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs, defStyle);
	}

	void init(Context mContext, AttributeSet attrs, int style) {
		TypedArray a = mContext.obtainStyledAttributes(attrs,
				R.styleable.fractional_view, style, 0);

		widthPercentage = a.getInteger(R.styleable.fractional_view_percentage,
				0);
		// do something with str
//		widthPercentage = 25;

		a.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (widthPercentage != 0) {
			int width = Resources.getSystem().getDisplayMetrics().widthPixels;
			int totalWidth = width * widthPercentage / 100;
			setMeasuredDimension(totalWidth, totalWidth);
		} else {
			int width = getMeasuredWidth();
			setMeasuredDimension(width, width);
		}
	}
}