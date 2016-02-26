package com.moneycontrol.handheld.api;

import com.moneycontrol.handheld.BaseActivity;
import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.constant.AppConstants;
import com.moneycontrol.handheld.custom.MyNetworkException;
import com.moneycontrol.handheld.fragments.StockDetailFragment;
import com.moneycontrol.handheld.login.InternalBrowser;
import com.moneycontrol.handheld.parser.ParseCall;
import com.moneycontrol.handheld.util.Utility;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.widget.Toast;

public class LinkMovementMethod extends android.text.method.LinkMovementMethod {

	private static Context movementContext;

	private static LinkMovementMethod linkMovementMethod = new LinkMovementMethod();

	public boolean onTouchEvent(android.widget.TextView widget,
			android.text.Spannable buffer, android.view.MotionEvent event) {
		int action = event.getAction();

		if (action == MotionEvent.ACTION_DOWN) {
			int x = (int) event.getX();
			int y = (int) event.getY();

			x -= widget.getTotalPaddingLeft();
			y -= widget.getTotalPaddingTop();

			x += widget.getScrollX();
			y += widget.getScrollY();

			Layout layout = widget.getLayout();
			int line = layout.getLineForVertical(y);
			int off = layout.getOffsetForHorizontal(line, x);

			URLSpan[] link = buffer.getSpans(off, off, URLSpan.class);
			if (link.length != 0) {
				String url = link[0].getURL();

				if (url.startsWith("https") || url.startsWith("http")) {
					try {
						if (ParseCall.getInstance().isOnline(movementContext)) {
							Bundle bundle = new Bundle();
							bundle.putString(AppConstants.SERVER_URL, url);
							InternalBrowser forgotpassowrdfragment = new InternalBrowser();
							forgotpassowrdfragment.setArguments(bundle);
							((BaseActivity) (movementContext)).launchFragement(
									forgotpassowrdfragment, true);
						}
						/*
						 * widget.getContext().startActivity( new
						 * Intent(Intent.ACTION_VIEW, Uri.parse(url)));
						 */
						/*
						 * String[] tempStr = null; if(url.contains("/")) {
						 * tempStr = url.split("/"); if(tempStr != null &&
						 * tempStr.length > 0) { Bundle bundle = new Bundle();
						 * bundle.putString(AppConstants.STOCK_ID,
						 * tempStr[tempStr.length-1]);
						 * 
						 * bundle.putString(AppConstants.STOCK_DEFAULT_EX, "N");
						 * 
						 * StockDetailFragment stockDetailFragment = new
						 * StockDetailFragment();
						 * stockDetailFragment.setArguments(bundle);
						 * 
						 * ((BaseActivity) movementContext).launchFragement(
						 * stockDetailFragment, true); return true;
						 * widget.getContext().startActivity( new
						 * Intent(Intent.ACTION_VIEW, Uri.parse(url))); } }
						 */
						return true;
					} catch (MyNetworkException e) {

						Utility.getInstance()
								.showToast(
										movementContext,
										movementContext
												.getString(R.string.alert_need_internet_connection),
										Toast.LENGTH_LONG);
						return true;

					} catch (Exception e) {
						// Toast.makeText(widget.getContext(), e.getMessage(),
						// Toast.LENGTH_SHORT).show();

						Utility.getInstance().showToast(movementContext,
								e.getMessage(), Toast.LENGTH_SHORT);
					}

				} else if (url.startsWith("tel")) {
					return super.onTouchEvent(widget, buffer, event);

				} else if (url.startsWith("mailto")) {
					return super.onTouchEvent(widget, buffer, event);
				} else {

					Utility.getInstance().showToast(movementContext,
							movementContext.getString(R.string.unable_open),
							Toast.LENGTH_SHORT);
					return true;

				}

			}
		}
		return true;
		// return super.onTouchEvent(widget, buffer, event);
	}

	public static android.text.method.MovementMethod getInstance(Context c) {
		movementContext = c;
		return linkMovementMethod;
	}
}