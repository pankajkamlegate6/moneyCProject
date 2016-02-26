package com.moneycontrol.handheld.anaylatics;

import java.util.HashMap;

import android.app.Activity;
import android.os.Build;

import com.comscore.analytics.comScore;
import com.flurry.android.FlurryAgent;
import com.moneycontrol.handheld.constant.AppConstants;
import com.moneycontrol.handheld.util.Utility;

public class AnalyticsTrack {
	public static String PREFIX = "/V3.0/";

	private static void setComscore(String value) {
		try {
			HashMap<String, String> labels = new HashMap<String, String>();
			labels.put("name", value);
			com.comscore.analytics.comScore.start(labels);
			// comScore.setAutoStartLabels(labels);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void analyticsSetPageView(Activity activity,
			final String pageName) {
		if (Build.VERSION.SDK_INT >= 9) {

			setComscore(pageName);
			comScore.onEnterForeground();
			comScore.onUxActive();
		}
		if (Utility.getKeysPrefrence(activity, AppConstants.KEY_FLURRY_ID) != null) {
			FlurryAgent.logEvent(pageName);
		}

	}

	public static void pauseComscore() {
		comScore.onExitForeground();
		comScore.onUxInactive();
	}

}
