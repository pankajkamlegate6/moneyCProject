package com.moneycontrol.handheld.currency.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshObserverListView;
import com.handmark.pulltorefresh.observablescrollview.ObservableListView;
import com.moneycontrol.handheld.BaseActivity;
import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.adapter.CurrenciesAdapter;
import com.moneycontrol.handheld.anaylatics.AnalyticsTag;
import com.moneycontrol.handheld.currency.entity.CurrenciesData;
import com.moneycontrol.handheld.currency.entity.CurrenciesData.Refreshdata;
import com.moneycontrol.handheld.parser.ParseCall;

//import com.github.ksoichiro.android.observablescrollview.ObservableListView;

public class ExchangeRateCurrencyFragment extends BaseCurrencyFragment {

	CurrenciesAdapter adapter;
	private CurrenciesData currenciesList;
	private PullToRefreshObserverListView lvCurrencies;
	private TextView tvCurrenciesTitle;
	private String url;
	private RelativeLayout progressBarr;
	boolean noLoaderwhilePull;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		currenciesList = (CurrenciesData) getArguments()
				.getSerializable("data");
		url = getArguments().getString("url");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View inflateView = inflater.inflate(
				R.layout.layout_currency_fragment, null);

		lvCurrencies = (PullToRefreshObserverListView) inflateView
				.findViewById(R.id.lvContent);

		progressBarr = (RelativeLayout) inflateView
				.findViewById(R.id.progressBarr);
		lvCurrencies
				.setOnRefreshListener(new OnRefreshListener<ObservableListView>() {

					@Override
					public void onRefresh(
							PullToRefreshBase<ObservableListView> refreshView) {
						noLoaderwhilePull = true;
						doGetCurrencies(false);
					}
				});

		addGoogleAnaylaticsEvent(AnalyticsTag.MARKETS, AnalyticsTag.CURRENCIES,
				AnalyticsTag.EXCHANGE_RATE);
		// tvCurrenciesTitle = (TextView) inflateView
		// .findViewById(R.id.tvHeaderTitle);
		// tvCurrenciesTitle.setText(getString(R.string.currencies).toUpperCase());
		return inflateView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		if (currenciesList != null) {
			uiHandler.sendEmptyMessage(0);
		} else {
			doGetCurrencies(true);
		}
	}

	private void doGetCurrencies(boolean isShowLoading) {
		if (isShowLoading) {
			// ((BaseActivity) getActivity()).showProgressDialog();
			if (noLoaderwhilePull == false) {
				startProgressDialog();
			}
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					currenciesList = ParseCall
							.getInstance()
							.getMarketCurrenciesCarouselData(getActivity(), url);

					uiHandler.sendEmptyMessage(0);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.e("milind", "exception in parsing " + e);
				}

			}
		}).start();

	}

	Handler uiHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			if (isAdded()) {
				adapter = new CurrenciesAdapter(currenciesList.getList(),
						getActivity());
				lvCurrencies.setAdapter(adapter);

				lvCurrencies.getRefreshableView().setAdapter(adapter);
				lvCurrencies.onRefreshComplete();
				// ((BaseActivity) getActivity()).dissMissProgressDialog();
				noLoaderwhilePull = false;
				dismissProgressDialog();
				if (currenciesList.getRefreshdata() != null) {
					Refreshdata refreshdata = currenciesList.getRefreshdata();
					setAutoRefresh(refreshdata.isFlag(), refreshdata.getRate());
				}
			}
		}

	};

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

		super.onRefresh();
		doGetCurrencies(false);

	}

	@Override
	void onUpdateData(CurrenciesData currencydata) {
		// TODO Auto-generated method stub

	}

	@Override
	public void performAutoRefresh() {
		// TODO Auto-generated method stub
		super.performAutoRefresh();
		onRefresh();
	}

	private void startProgressDialog() {
		progressBarr.setVisibility(View.VISIBLE);
	}

	private void dismissProgressDialog() {
		progressBarr.setVisibility(View.GONE);
	}
}
