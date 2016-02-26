package com.moneycontrol.handheld.chart.activity;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.adapter.ChartDropdownAdapter;
import com.moneycontrol.handheld.chart.data.GraphDayEntity;
import com.moneycontrol.handheld.chart.entity.StickGraphBean;
import com.moneycontrol.handheld.chart.fragment.FnOGraphFragment;
import com.moneycontrol.handheld.entity.market.GraphData;
import com.moneycontrol.handheld.entity.messages.DropDownBean;
import com.moneycontrol.handheld.util.UrlUtils;
import com.neopixl.pixlui.components.textview.TextView;

public class FutureChartActivty extends ChartBaseActivity implements
		OnItemSelectedListener {

	String Id;
	String category, stockName;
	LayoutInflater mInflater;
	Spinner times;
	// //1d,//5d//,

	int i = 0;
	String timeUrl = "";
	FrameLayout volumecontainer, chartcontainer;
	TextView chartTitle;
	ArrayList<GraphDayEntity> dayEntiy = new ArrayList<GraphDayEntity>();
	Bundle newBundle;
	private LinearLayout chart_value_layout, chart_name_layout;
	private TextView tv_open, tv_close, tv_low, tv_high, tv_date, tv_volume;
	private boolean setupActionBar = false;
	private String date;
	private int color;
	private boolean firstLoad = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(null);
		if (savedInstanceState != null) {
			finish();
			return;
		}
		setContentView(R.layout.layout_chat_acitivity);

		mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		volumecontainer = (FrameLayout) findViewById(R.id.volume_container);
		chartcontainer = (FrameLayout) findViewById(R.id.chartcontainer);
		hideVolume();
		Id = getIntent().getStringExtra("id");
		timeUrl = getIntent().getStringExtra("graphUrl");
		color = getIntent().getIntExtra("color", 0);
		stockName = getIntent().getStringExtra("name");
		date = getIntent().getStringExtra("date");
		newBundle = new Bundle();
		newBundle.putInt("color", color);
		String url = UrlUtils.getVariable(timeUrl, "expdt=", date);
		newBundle.putString("Id", url);

		FnOGraphFragment fragment = new FnOGraphFragment();
		fragment.setArguments(newBundle);
		replaceFragment(fragment);
	}

	public void initialActionBarSetUp(StickGraphBean bean) {
		if (!setupActionBar) {
			ActionBar actionBar = getSupportActionBar();
			actionBar.setTitle("");
			actionBar.setDisplayHomeAsUpEnabled(false);
			actionBar.setDisplayShowCustomEnabled(true);
			actionBar.setDisplayShowTitleEnabled(false);
			actionBar.setHomeButtonEnabled(false);
			// actionBar.setDisplayUseLogoEnabled(false);
			actionBar.setDisplayShowHomeEnabled(false);
			actionBar.setCustomView(R.layout.header_action_bar_chart_mf);
			View v = actionBar.getCustomView();
			chartTitle = (TextView) v.findViewById(R.id.chart_title);
			LayoutParams lp = v.getLayoutParams();
			lp.width = LayoutParams.FILL_PARENT;
			v.setLayoutParams(lp);
			chartTitle.setText(stockName);
			times = (Spinner) v.findViewById(R.id.timespinner);

			ArrayAdapter<DropDownBean> timeAdapter = new ChartDropdownAdapter(
					mContext, bean.getTabs());
			timeAdapter.setDropDownViewResource(R.layout.dropdown_item);
			times.setAdapter(timeAdapter);
			times.setOnItemSelectedListener(this);
			chart_name_layout = (LinearLayout) v
					.findViewById(R.id.chart_name_layout);

			chart_value_layout = (LinearLayout) v
					.findViewById(R.id.chart_value_layout);
			tv_close = (TextView) v.findViewById(R.id.tv_close);
			tv_open = (TextView) v.findViewById(R.id.tv_open);
			tv_high = (TextView) v.findViewById(R.id.tv_high);
			tv_low = (TextView) v.findViewById(R.id.tv_low);
			tv_date = (TextView) v.findViewById(R.id.tv_date);
			tv_volume = (TextView) v.findViewById(R.id.tv_volume);
			setupActionBar = true;
		}
		// compare.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// compareActionBar();
		// }
		// });
	}

	public void replaceFragment(Fragment fragment) {
		FragmentManager fragmentManger = getSupportFragmentManager();
		FragmentTransaction transcation = fragmentManger.beginTransaction();
		transcation.replace(R.id.chartcontainer, fragment);

		transcation.commitAllowingStateLoss();
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long Id) {
		if (!firstLoad) {
			DropDownBean dropDownBean = (DropDownBean) times.getAdapter()
					.getItem(position);
			String url = dropDownBean.getUrl();
			updateCharts(url, dropDownBean.getUniqueId());
		} else {
			firstLoad = false;
		}

	}

	public void updateCharts(String url, String uniqueId) {
		Fragment fragment = getSupportFragmentManager().findFragmentById(
				R.id.chartcontainer);
		if (fragment instanceof FnOGraphFragment) {
			((FnOGraphFragment) fragment).updateChart(url, uniqueId);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

	public void hideVolume() {
		LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) chartcontainer
				.getLayoutParams();
		params.weight = 2;
		chartcontainer.setLayoutParams(params);

		LinearLayout.LayoutParams volumeParams = (android.widget.LinearLayout.LayoutParams) volumecontainer
				.getLayoutParams();
		volumeParams.weight = 0;
		volumecontainer.setLayoutParams(volumeParams);
		volumecontainer.setVisibility(View.GONE);
	}

	public void showValue(String open, String close, String high, String low,
			String date, String volume) {
		chart_name_layout.setVisibility(View.GONE);
		chart_value_layout.setVisibility(View.VISIBLE);
		if (!TextUtils.isEmpty(close)) {
			tv_close.setVisibility(View.VISIBLE);
			tv_close.setText(Html.fromHtml(close));
		} else {
			tv_close.setVisibility(View.GONE);
		}
		if (!TextUtils.isEmpty(open)) {
			tv_open.setText(Html.fromHtml(open));
			tv_open.setVisibility(View.VISIBLE);
		} else {
			tv_open.setVisibility(View.GONE);
		}
		if (!TextUtils.isEmpty(high)) {
			tv_high.setText(Html.fromHtml(high));
			tv_high.setVisibility(View.VISIBLE);
		} else {
			tv_high.setVisibility(View.GONE);
		}
		if (!TextUtils.isEmpty(low)) {
			tv_low.setText(Html.fromHtml(low));
			tv_low.setVisibility(View.VISIBLE);
		} else {
			tv_low.setVisibility(View.GONE);
		}
		if (!TextUtils.isEmpty(date)) {
			tv_date.setText(Html.fromHtml(date));
			tv_date.setVisibility(View.VISIBLE);
		} else {
			tv_date.setVisibility(View.GONE);
		}
		if (!TextUtils.isEmpty(volume)) {
			tv_volume.setText(Html.fromHtml(volume));
			tv_volume.setVisibility(View.VISIBLE);
		} else {
			tv_volume.setVisibility(View.GONE);
		}

	}

	public void hideValue() {
		chart_value_layout.setVisibility(View.GONE);
		chart_name_layout.setVisibility(View.VISIBLE);
	}
}