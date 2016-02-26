package com.moneycontrol.handheld.chart.activity;

import java.util.Locale;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.flurry.android.FlurryAgent;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.moneycontrol.handheld.AppData;
import com.moneycontrol.handheld.AppData.TrackerName;
import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.anaylatics.AnalyticsTrack;
import com.moneycontrol.handheld.constant.AppConstants;
import com.moneycontrol.handheld.massages.fragments.BaseFragement;
import com.moneycontrol.handheld.util.Utility;

public class ChartBaseActivity extends ActionBarActivity {
	Handler handler = new Handler();

	public Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);

		setLanguage();
		mContext = this;

		// AppsFlyerLib.setAppsFlyerKey("eUjViEftK8MRWusihnDdtW");
		// AppsFlyerLib.sendTracking(getApplicationContext());

	}

	Runnable runnbale = new Runnable() {

		@Override
		public void run() {
			BaseFragement fragment = (BaseFragement) getSupportFragmentManager()
					.findFragmentById(R.id.chartcontainer);
			fragment.onRefresh();
			handler.postDelayed(runnbale, AppConstants.REFRESH_RATE * 1000);
		}
	};

	public android.support.v4.app.Fragment getCurrentChartFragment() {
		return getSupportFragmentManager()
				.findFragmentById(R.id.chartcontainer);
	}

	public void setLanguage() {

		String languageToLoad = ""; // your language
		// String languageToLoad = "gu"; // your language

		SharedPreferences splanguage = getSharedPreferences(
				"language_selection", MODE_PRIVATE);

		if (splanguage.getString("language", "English").equalsIgnoreCase(
				"English")) {
			languageToLoad = "";
		} else if (splanguage.getString("language", "Gujrati")
				.equalsIgnoreCase("Gujrati")) {
			languageToLoad = "gu";
		} else if (splanguage.getString("language", "Hindi").equalsIgnoreCase(
				"Hindi")) {
			languageToLoad = "hi";
			// Utility.getInstance().setLocale(getApplicationContext(), "hi");
		}

		// MyCacheManager.clear();
		Locale locale = new Locale(languageToLoad);
		Locale.setDefault(locale);
		Configuration configg = new Configuration();

		configg.locale = locale;
		getBaseContext().getResources().updateConfiguration(configg,
				getBaseContext().getResources().getDisplayMetrics());

	}

	public void addGoogleAnaylaticsEvent(String... tag) {
		StringBuilder builder = new StringBuilder();
		builder.append(AnalyticsTrack.PREFIX);
		SharedPreferences splanguage = getSharedPreferences(
				"language_selection", MODE_PRIVATE);
		String languange = splanguage.getString("language", "English");
		builder.append(languange.toLowerCase());
		for (int i = 0; i < tag.length; i++) {
			builder.append("/" + tag[i]);
		}
		if (AppData.getInstance().getTracker(TrackerName.APP_TRACKER) != null) {
			Tracker t = AppData.getInstance().getTracker(
					TrackerName.APP_TRACKER);

			t.setScreenName(builder.toString());

			t.send(new HitBuilders.AppViewBuilder().build());

			AnalyticsTrack.analyticsSetPageView(ChartBaseActivity.this,
					builder.toString());
		}
	}

	@Override
	protected void onStart() {
		super.onStart();

		GoogleAnalytics.getInstance(this).reportActivityStart(this);
		if (Utility.getKeysPrefrence(getApplicationContext(),
				AppConstants.KEY_FLURRY_ID) != null) {
			FlurryAgent.init(this, Utility.getKeysPrefrence(
					getApplicationContext(), AppConstants.KEY_FLURRY_ID));
			FlurryAgent.onStartSession(this);
		}
	}

	@Override
	protected void onStop() {
		super.onStop();

		GoogleAnalytics.getInstance(this).reportActivityStop(this);
		if (Utility.getKeysPrefrence(getApplicationContext(),
				AppConstants.KEY_FLURRY_ID) != null) {
			FlurryAgent.onEndSession(this);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.e("response", "savestance");
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int pointerIndex = event.getActionIndex();

		// get pointer ID
		int pointerId = event.getPointerId(pointerIndex);

		// get masked (not specific to a pointer) action
		int maskedAction = event.getActionMasked();

		switch (maskedAction) {

		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN: {
			if (Utility.getCurrentFragment(mContext) instanceof BaseFragement) {
				BaseFragement fragement = (BaseFragement) Utility
						.getCurrentFragment(mContext);
				fragement.pauseTimer();
			}
			break;
		}
		case MotionEvent.ACTION_MOVE: { // a pointer was moved
			// TODO use data
			break;
		}
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
		case MotionEvent.ACTION_CANCEL: {
			if (Utility.getCurrentFragment(mContext) instanceof BaseFragement) {
				BaseFragement fragement = (BaseFragement) Utility
						.getCurrentFragment(mContext);
				fragement.resumeTimer();
			}
			break;
		}
		}

		return super.dispatchTouchEvent(event);

	}

}
