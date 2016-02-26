package com.moneycontrol.handheld.currency.fragments;

import java.util.ArrayList;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.moneycontrol.handheld.BaseActivity;
import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.constant.AppConstants;
import com.moneycontrol.handheld.currency.entity.CurrenciesData;
import com.moneycontrol.handheld.custom.PagerSlidingTabStrip;
import com.moneycontrol.handheld.entity.messages.DropDownBean;
import com.moneycontrol.handheld.parser.ParseCall;
import com.moneycontrol.handheld.util.Utility;
import com.neopixl.pixlui.components.textview.TextView;

public class CurrenciesParentFragment extends BaseCurrencyFragment {
	private CurrenciesData currenciesList;
	private ViewPager pager;
	private PagerSlidingTabStrip tabStrip;
	private String[] tabs = new String[] { "Exchange Rate",
			"Currency Convertor" };
	// getResources().getString(R.string.exchange_rate),
	// getResources().getString(R.string.currency_convertor) };

	ArrayList<DropDownBean> dropDownlist = new ArrayList<DropDownBean>();
	SparseArray<Fragment> fragmentlist = new SparseArray<Fragment>();
	private TextView tvCurrenciesTitle;
	private CurrencyPagerAdapter pagerAdapter;
	private boolean onRefresh = false;
	private String Url = "";
	private RelativeLayout progressBarr;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		if (getActivity() instanceof BaseActivity) {
			BaseActivity activity = (BaseActivity) getActivity();
			activity.showBannerad();
			// activity.hideNshowTicker();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		mainView = inflater.inflate(R.layout.layout_currency_parent, null);
		tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		pager = (ViewPager) findViewById(R.id.pager);
		tvCurrenciesTitle = (TextView) findViewById(R.id.tvHeaderTitle);
		tvCurrenciesTitle.setText(getString(R.string.currencies).toUpperCase());
		tabs = getActivity().getResources().getStringArray(
				R.array.settings_array);

		progressBarr = (RelativeLayout) mainView
				.findViewById(R.id.progressBarr);

		Utility.getInstance().setAdMobiData(this);
		return mainView;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		showTicker();
	}

	private void startProgressDialog() {
		progressBarr.setVisibility(View.VISIBLE);
	}

	private void dismissProgressDialog() {
		progressBarr.setVisibility(View.GONE);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		try {
			Url = getArguments().getString(AppConstants.SERVER_URL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (isAdded()) {
			doGetCurrencies(true);
		}
	}

	@Override
	public void onRefresh() {
		/*
		 * // * BaseCurrencyFragment fragment = (BaseCurrencyFragment)
		 * fragmentlist // * .get(pager.getCurrentItem()); fragment.onRefresh();
		 * //
		 */
		// if (currenciesList != null) {
		// if (currenciesList.getRefreshdata() != null) {
		// if (currenciesList.getRefreshdata().isFlag()) {
		// onRefresh = true;
		// } else {
		// onRefresh = false;
		// }
		// }
		// }
		//
		// if (onRefresh) {
		// if (fragmentlist.get(pager.getCurrentItem()) instanceof
		// CurrencyConverterFragment) {
		// CurrencyConverterFragment fragment = (CurrencyConverterFragment)
		// fragmentlist
		// .get(pager.getCurrentItem());
		// fragment.onRefresh();
		// } else if (fragmentlist.get(pager.getCurrentItem()) instanceof
		// ExchangeRateCurrencyFragment) {
		// ExchangeRateCurrencyFragment fragment =
		// (ExchangeRateCurrencyFragment) fragmentlist
		// .get(pager.getCurrentItem());
		// fragment.onRefresh();
		// }
		// }
	}

	private void doGetCurrencies(boolean isShowLoading) {
		if (isShowLoading) {
			// ((BaseActivity) getActivity()).showProgressDialog();
			if (saveBundle == null) {
				startProgressDialog();
			}
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					currenciesList = ParseCall
							.getInstance()
							.getMarketCurrenciesCarouselData(getActivity(), Url);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.e("milind", "exception in parsing " + e);
				}
				uiHandler.sendEmptyMessage(0);
			}
		}).start();

	}

	private Handler uiHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (isAdded()) {
				// ((BaseActivity) getActivity()).dissMissProgressDialog();
				dismissProgressDialog();
				if (currenciesList != null) {
					dropDownlist = currenciesList.getTabs();

					pagerAdapter = new CurrencyPagerAdapter(
							getChildFragmentManager());
					pager.setAdapter(pagerAdapter);
					tabStrip.setViewPager(pager);
					tabStrip.setIndicatorColor(getResources().getColor(
							R.color.orange));
					tabStrip.setTextColor(getResources()
							.getColor(R.color.white));
					tabStrip.setTypeface(Typeface.createFromAsset(getActivity()
							.getAssets(), "fonts/Roboto-Regular.ttf"),
							Typeface.NORMAL);
					tabStrip.setTextSize((int) getActivity().getResources()
							.getDimension(R.dimen.view_pager_tab_text_size));
					tabStrip.setOnPageChangeListener(new OnPageChangeListener() {

						@Override
						public void onPageSelected(int position) {
							Utility.getInstance().hideKeyBoard(getActivity());
							if (pagerAdapter.getItem(position) instanceof CurrencyConverterFragment) {
								if (getActivity() instanceof BaseActivity) {
									BaseActivity activity = (BaseActivity) getActivity();
									activity.hideBannerad();
									// activity.hideNshowTicker();
								}
							} else {
								if (getActivity() instanceof BaseActivity) {
									BaseActivity activity = (BaseActivity) getActivity();
									activity.showBannerad();

								}
							}

						}

						@Override
						public void onPageScrolled(int arg0, float arg1,
								int arg2) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onPageScrollStateChanged(int arg0) {
							// TODO Auto-generated method stub

						}
					});
				} else {
					showToast(getActivity().getResources().getString(
							R.string.unable_to_connect));
				}
			}
		}
	};

	class CurrencyPagerAdapter extends FragmentStatePagerAdapter {
		private FragmentManager fragmentManager;

		public CurrencyPagerAdapter(FragmentManager fm) {
			super(fm);
			fragmentManager = fm;
		}

		@Override
		public Fragment getItem(int position) {

			Fragment fragment;
			Bundle bundle = new Bundle();
			bundle.putSerializable("data", currenciesList);
			bundle.putSerializable("url", dropDownlist.get(position).getUrl());
			if (dropDownlist.get(position).getUniqueId().equalsIgnoreCase("1")) {
				fragment = new ExchangeRateCurrencyFragment();
				fragmentlist.put(position, fragment);
			} else {
				fragment = new CurrencyConverterFragment();
				fragmentlist.put(position, fragment);
			}
			fragment.setArguments(bundle);

			return fragment;
		}

		@Override
		public int getCount() {
			return dropDownlist.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return dropDownlist.get(position).getName();
		}

	}

	@Override
	void onUpdateData(CurrenciesData currencydata) {
		// TODO Auto-generated method stub

	}
}
