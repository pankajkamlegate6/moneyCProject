package com.moneycontrol.handheld.custom;

import android.content.Context;
import android.util.AttributeSet;

import com.neopixl.pixlui.components.textview.TextView;

public class CapitalizedTextView extends TextView {

	public CapitalizedTextView(Context context, AttributeSet attrs,
			boolean canBeEllipsized) {
		super(context, attrs, canBeEllipsized);
		// TODO Auto-generated constructor stub
	}

	public CapitalizedTextView(Context context, AttributeSet attrs,
			int defStyle, boolean canBeEllipsized) {
		super(context, attrs, defStyle, canBeEllipsized);
		// TODO Auto-generated constructor stub
	}

	public CapitalizedTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public CapitalizedTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CapitalizedTextView(Context context, boolean canBeEllipsized) {
		super(context, canBeEllipsized);
		// TODO Auto-generated constructor stub
	}

	public CapitalizedTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setText(CharSequence text, BufferType type) {
		super.setText(text.toString().toUpperCase(), type);
	}
}
