package com.moneycontrol.handheld.custom;

import java.util.HashMap;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.moneycontrol.handheld.AppData;
import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.fragments.HomeFragment;
import com.moneycontrol.handheld.parser.ParseCall;
import com.moneycontrol.handheld.util.Utility;
import com.moneycontrol.handheld.watchlist.customview.CustomProgressDialog;
import com.neopixl.pixlui.components.textview.TextView;

public class UserOptionFragment extends DialogFragment implements
		OnClickListener {

	OnOptionItemSelectedListener callbacklistener;
	private LinearLayout tvLogoutll = null;
	private LinearLayout tvPortfolioll = null;
	private LinearLayout tvMyPagell = null;
	private LinearLayout tvWatchListll = null;
	private RelativeLayout notification_count_rl = null;

	private TextView userName = null;
	private TextView newpage_count = null;
	private View inflatedView;
	private Activity activity;
	private ImageView img;
	private String serverUrl = "http://feeds.moneycontrol.com/app/notification_count.php?user_id=&token=&t_version=11";
	private int notificationCount = 0;
	private AppData MainController = null;
	public HashMap<String, String> extraLinks = new HashMap<String, String>();

	public UserOptionFragment() {
		// Empty constructor required for DialogFragment
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {

			callbacklistener = (OnOptionItemSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		inflatedView = inflater.inflate(R.layout.user_options, null);

		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));

		getDialog().getWindow().setGravity(
				Gravity.CENTER_HORIZONTAL | Gravity.TOP);
		WindowManager.LayoutParams p = getDialog().getWindow().getAttributes();
		p.width = LayoutParams.MATCH_PARENT;

		p.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE;
		getDialog().getWindow().setAttributes(p);

		p.gravity = Gravity.RIGHT | Gravity.TOP;
		// p.y = -300;
		initViews(inflatedView);

		getDialog().setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub

				callbacklistener.onItemPicked(14, 0);

			}
		});

		notificationCount = 0;
		MainController = AppData.getInstance();
		extraLinks = MainController.getExtra_url();
		serverUrl = extraLinks.get("my_page_count");

		return inflatedView;

	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();

	}

	@Override
	public void onCancel(DialogInterface dialog) {
		// TODO Auto-generated method stub
		super.onCancel(dialog);

		callbacklistener.onItemPicked(14, 0);

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

	}

	private void initViews(View rootView) {
		// TODO Auto-generated method stub

		userName = (TextView) rootView.findViewById(R.id.user_name);
		newpage_count = (TextView) rootView.findViewById(R.id.tvMyPage_count);
		newpage_count.setVisibility(View.GONE);
		notification_count_rl = (RelativeLayout) rootView
				.findViewById(R.id.notification_count);
		tvPortfolioll = (LinearLayout) rootView
				.findViewById(R.id.tvPortfolioll);
		tvWatchListll = (LinearLayout) rootView
				.findViewById(R.id.tvWatchListll);
		tvMyPagell = (LinearLayout) rootView.findViewById(R.id.tvMyPagell);
		tvLogoutll = (LinearLayout) rootView.findViewById(R.id.tvLogoutll);
		userName.setText("Hi  " + Utility.getInstance().getUSER_Name());

		tvPortfolioll.setOnClickListener(this);
		tvWatchListll.setOnClickListener(this);
		tvMyPagell.setOnClickListener(this);
		tvLogoutll.setOnClickListener(this);
		doRequest();
	}

	void doRequest() {
		if (isCompataible11()) {
			new NetworkTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} else {
			new NetworkTask().execute();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.tvLogoutll:

			ParseCall.getInstance().setLogout(getActivity());

			callbacklistener.onItemPicked(4, 0);

			dismiss();

			break;

		case R.id.tvPortfolioll:

			callbacklistener.onItemPicked(1, 0);

			dismiss();
			break;
		case R.id.tvWatchListll:

			callbacklistener.onItemPicked(2, 0);
			dismiss();

			break;

		case R.id.tvMyPagell:
			// notificationCount = 5;
			callbacklistener.onItemPicked(3, notificationCount);
			dismiss();

			break;

		default:
			break;
		}

	}

	public interface OnOptionItemSelectedListener {
		public void onItemPicked(int position, int value);
	}

	private class NetworkTask extends AsyncTask<Integer, Void, Bundle> {

		public NetworkTask() {
			// TODO Auto-generated constructor stub
		}

		public NetworkTask(boolean showProgressBar) {
			// TODO Auto-generated constructor stub

		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

		}

		@Override
		protected Bundle doInBackground(Integer... params) {

			// int pageNo = params[0];
			Bundle bundle = new Bundle();
			String data = "";

			final AppData MainController = AppData.getInstance();

			if (!MainController.hasConnection()) {
				((getActivity())).runOnUiThread(new Runnable() {
					@Override
					public void run() {

						Utility.getInstance().showAlertDialog(
								getActivity(),
								getActivity().getResources().getString(
										R.string.no_internet), null);

					}
				});
				return null;
			} else {

				try {
					data = ParseCall.getInstance().getNotificationCount(
							getActivity(), serverUrl);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			bundle.putString("result", data);
			return bundle;
		}

		@Override
		protected void onPostExecute(Bundle result) {
			super.onPostExecute(result);

			if (result != null) {
				try {
					notificationCount = Integer.parseInt(result
							.getString("result"));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					notificationCount = 0;
					e.printStackTrace();
				}
				if (notificationCount > 0) {
					notification_count_rl.setVisibility(View.VISIBLE);
					displayNewPageCount(notificationCount);

				}
			}

		}

	}

	public void displayNewPageCount(int count) {
		newpage_count.setText(count + "");
		newpage_count.setVisibility(View.VISIBLE);
	}

	public boolean isCompataible11() {
		if (android.os.Build.VERSION.SDK_INT < 11) {
			return false;
		} else {
			return true;
		}
	}
}
