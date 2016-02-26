package com.moneycontrol.handheld.api;

import com.moneycontrol.handheld.BaseActivity;
import com.moneycontrol.handheld.constant.AppConstants;
import com.moneycontrol.handheld.login.InternalBrowser;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BudgetPageWebViewClient extends WebViewClient {

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		Bundle bundle = new Bundle();
		bundle.putString(AppConstants.SERVER_URL, url);
		InternalBrowser forgotpassowrdfragment = new InternalBrowser();
		forgotpassowrdfragment.setArguments(bundle);
		if (view.getContext() instanceof BaseActivity) {
			((BaseActivity) view.getContext()).launchFragement(
					forgotpassowrdfragment, true);
		}
		return true;
	}
}
