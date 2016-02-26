package com.moneycontrol.handheld.currency.entity;

import com.divum.MoneyControl.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class CurrencyFooterView extends LinearLayout {

	public CurrencyFooterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CurrencyFooterView(Context context) {
		super(context);
		init();
		// TODO Auto-generated constructor stub
	}

	private void init() {
		// TODO Auto-generated method stub
		inflate(getContext(), R.layout.footer_watchlist, null);
	}

}
