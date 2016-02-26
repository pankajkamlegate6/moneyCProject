package com.moneycontrol.handheld.currency.fragments;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.BaseActivity;
import com.moneycontrol.handheld.adapter.CurrencyConverterSpinnerAdapter;
import com.moneycontrol.handheld.anaylatics.AnalyticsTag;
import com.moneycontrol.handheld.currency.entity.CurrenciesData;
import com.moneycontrol.handheld.currency.entity.CurrenciesData.CurrencyCountry;
import com.moneycontrol.handheld.currency.entity.CurrenciesData.Refreshdata;
import com.moneycontrol.handheld.parser.ParseCall;
import com.moneycontrol.handheld.util.Constantlibs;
import com.neopixl.pixlui.components.edittext.EditText;
import com.neopixl.pixlui.components.textview.TextView;

public class CurrencyConverterFragment extends BaseCurrencyFragment implements
		OnItemSelectedListener, TextWatcher, OnFocusChangeListener {
	private Spinner sourceSpinner;
	Spinner compareSpinner;
	private CurrenciesData data;
	CurrencyConverterSpinnerAdapter adapter;
	ArrayList<CurrencyCountry> countrylist = new ArrayList<CurrenciesData.CurrencyCountry>();
	TextView tv_result;
	HashMap<String, Double> results = new HashMap<String, Double>();
	String resultString = "1 %s = %s";
	private com.neopixl.pixlui.components.edittext.EditText sourceEdittext,
			compareEdittext;
	private int source_selection = 0;
	private int compare_selection = 0;
	private String url;
	private RelativeLayout progressBarr;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainView = inflater.inflate(
				R.layout.layout_currency_converter_fragment, null);
		sourceSpinner = (Spinner) findViewById(R.id.sourceSpinner);
		compareSpinner = (Spinner) findViewById(R.id.compareSpinner);
		sourceEdittext = (EditText) findViewById(R.id.edittext_soucre);
		compareEdittext = (EditText) findViewById(R.id.edittext_compare);
		tv_result = (TextView) findViewById(R.id.tv_result);
		addGoogleAnaylaticsEvent(AnalyticsTag.MARKETS, AnalyticsTag.CURRENCIES,
				AnalyticsTag.CURRENCY_CONVERTOR);
		progressBarr = (RelativeLayout) mainView
				.findViewById(R.id.progressBarr);

		BaseActivity activity = (BaseActivity) getActivity();
		activity.hideBanneradV2();

		return mainView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		data = (CurrenciesData) getArguments().getSerializable("data");
		url = getArguments().getString("url");
		results = data.getConvertedData();
		countrylist.addAll(data.getCountrylist());

		adapter = new CurrencyConverterSpinnerAdapter(mContext, countrylist);
		sourceSpinner.setAdapter(adapter);
		compareSpinner.setAdapter(adapter);
		int i = findUS();
		sourceSpinner.setSelection(i);
		sourceSpinner.setOnItemSelectedListener(this);
		compareSpinner.setOnItemSelectedListener(this);
		sourceEdittext.setOnFocusChangeListener(this);
		compareEdittext.setOnFocusChangeListener(this);
		// source.showDropDown();
		sourceEdittext.getText().clear();
		compareEdittext.getText().clear();
		sourceEdittext.append("0");
		compareEdittext.append("0");
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		super.onRefresh();
		doGetCurrencies(false);
	}

	@Override
	void onUpdateData(CurrenciesData currencydata) {
		data = currencydata;
	}

	public void updateText(String sourceResult, String compare) {
		try {
			tv_result.setText(String
					.format(resultString, compare, sourceResult));
		} catch (Exception e) {

		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

		try {
			if (parent.getId() == sourceSpinner.getId()) {
				source_selection = position;
			} else {
				compare_selection = position;
			}
			String compareString = ((CurrencyCountry) compareSpinner
					.getSelectedItem()).getCountryName();
			String sourceString = ((CurrencyCountry) sourceSpinner
					.getSelectedItem()).getCountryName();
			String key = String.format(Constantlibs.CURRENT_CONVERTER_PATTERN,
					sourceString, compareString);

			updateText("" + formattedString(results.get(key)) + " "
					+ compareString, sourceString);
			updateEdittextV2();
		} catch (Exception e) {

		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

	@Override
	public void afterTextChanged(Editable s) {
		updateEdittextV2();
	}

	private void updateEdittextV2() {
		try {
			double sourceDouble = 0, compareDouble = 0;
			if (sourceEdittext.getText().toString().trim().length() == 0) {
				sourceDouble = 0;
			} else {
				sourceDouble = Double.valueOf(sourceEdittext.getText()
						.toString().trim());
			}

			if (compareEdittext.getText().toString().trim().length() == 0) {
				compareDouble = 0;
			} else {
				compareDouble = Double.valueOf(compareEdittext.getText()
						.toString().trim());
			}

			if (sourceEdittext.hasFocus()) {
				compareEdittext.removeTextChangedListener(this);
				String compareString = ((CurrencyCountry) compareSpinner
						.getSelectedItem()).getCountryName();
				String sourceString = ((CurrencyCountry) sourceSpinner
						.getSelectedItem()).getCountryName();
				String key = String.format(
						Constantlibs.CURRENT_CONVERTER_PATTERN, sourceString,
						compareString);
				Double rate = results.get(key);
				// if (rate <= 1) {
				// key = String.format(Constantlibs.CURRENT_CONVERTER_PATTERN,
				// sourceString, compareString);
				// rate = results.get(key);
				// }
				compareEdittext.setText("");
				compareEdittext.getText().clear();
				compareEdittext.append(""
						+ calculateCompareResult(sourceDouble, rate));
			} else {
				String compareString = ((CurrencyCountry) compareSpinner
						.getSelectedItem()).getCountryName();
				String sourceString = ((CurrencyCountry) sourceSpinner
						.getSelectedItem()).getCountryName();
				String key = String.format(
						Constantlibs.CURRENT_CONVERTER_PATTERN, compareString,
						sourceString);
				Double rate = results.get(key);
				// if (rate <= 1) {
				// key = String.format(Constantlibs.CURRENT_CONVERTER_PATTERN,
				// sourceString, compareString);
				// rate = results.get(key);
				// }
				sourceEdittext.removeTextChangedListener(this);
				sourceEdittext.getText().clear();
				sourceEdittext.append(""
						+ calculateCompareResult(compareDouble, rate));
			}
		} catch (Exception e) {

		}

	}

	private void updateEdittext() {
		try {
			double sourceDouble = 0, compareDouble = 0;
			if (sourceEdittext.getText().toString().trim().length() == 0) {
				sourceDouble = 0;
			} else {
				sourceDouble = Double.valueOf(sourceEdittext.getText()
						.toString().trim());
			}

			if (compareEdittext.getText().toString().trim().length() == 0) {
				compareDouble = 0;
			} else {
				compareDouble = Double.valueOf(compareEdittext.getText()
						.toString().trim());
			}

			String compareString = ((CurrencyCountry) compareSpinner
					.getSelectedItem()).getCountryName();
			String sourceString = ((CurrencyCountry) sourceSpinner
					.getSelectedItem()).getCountryName();
			String key = String.format(Constantlibs.CURRENT_CONVERTER_PATTERN,
					sourceString, compareString);
			Double rate = results.get(key);
			if (sourceEdittext.hasFocus()) {
				compareEdittext.removeTextChangedListener(this);

				compareEdittext.setText(""
						+ calculateResult(sourceDouble, rate));
			} else {
				sourceEdittext.removeTextChangedListener(this);
				sourceEdittext.setText(""
						+ calculateCompareResult(compareDouble, rate));
			}
		} catch (Exception e) {

		}

	}

	private String calculateCompareResult(double compareDouble, Double rate) {

		return formattedString(compareDouble * rate);
	}

	public String formattedString(Double value) {

		String COMMA_SEPERATED = "#.###";
		DecimalFormat decimalFormat = new DecimalFormat(COMMA_SEPERATED);

		if (android.os.Build.VERSION.SDK_INT > 8) {
			decimalFormat.setRoundingMode(RoundingMode.DOWN);
		}
		return "" + decimalFormat.format(value);
	}

	private String calculateResult(double sourceDouble, Double rate) {

		return formattedString(sourceDouble / rate);

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (v.getId() == sourceEdittext.getId()) {
			if (sourceEdittext.hasFocus()) {
				if (sourceEdittext.length() > 0) {
					String value = sourceEdittext.getText().toString().trim();
					if (value.equalsIgnoreCase("0")) {
						sourceEdittext.setText("");
					}
				}
				sourceEdittext.addTextChangedListener(this);
			}
		} else {
			if (v.getId() == compareEdittext.getId()) {

				if (compareEdittext.hasFocus()) {
					if (compareEdittext.length() > 0) {
						String value = compareEdittext.getText().toString()
								.trim();
						if (value.equalsIgnoreCase("0")) {
							compareEdittext.setText("");
						}
					}
					compareEdittext.addTextChangedListener(this);
				}
			}
		}

	}

	private void doGetCurrencies(boolean isShowLoading) {
		if (isShowLoading) {
			startProgressDialog();
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					data = ParseCall
							.getInstance()
							.getMarketCurrenciesCarouselData(getActivity(), url);

				} catch (Exception e) {
					// TODO Auto-generated catch block
				}
				uiHandler.sendEmptyMessage(0);
			}
		}).start();

	}

	@Override
	public void performAutoRefresh() {
		// TODO Auto-generated method stub
		super.performAutoRefresh();
		doGetCurrencies(false);
	}

	private Handler uiHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (isAdded()) {
				dismissProgressDialog();
				if (data != null) {
					results = data.getConvertedData();
					String compareString = ((CurrencyCountry) compareSpinner
							.getSelectedItem()).getCountryName();
					String sourceString = ((CurrencyCountry) sourceSpinner
							.getSelectedItem()).getCountryName();
					String key = String.format(
							Constantlibs.CURRENT_CONVERTER_PATTERN,
							compareString, sourceString);

					updateText("" + formattedString(results.get(key)) + " "
							+ sourceString, compareString);
					updateEdittextV2();
					if (data.getRefreshdata() != null) {
						Refreshdata refreshdata = data.getRefreshdata();
						setAutoRefresh(refreshdata.isFlag(),
								refreshdata.getRate());
					}
				}
			}

		}
	};

	private void startProgressDialog() {
		progressBarr.setVisibility(View.VISIBLE);
	}

	private void dismissProgressDialog() {
		progressBarr.setVisibility(View.GONE);
	}

	public int findUS() {
		boolean available = false;
		int i = 0;
		for (CurrencyCountry country : countrylist) {
			if (country.getCountryName().equalsIgnoreCase(
					getString(R.string.currency_us_dollar))) {
				available = true;
				break;
			}
			i++;
		}
		if (!available) {
			i = 1;
		}
		return i;
	}
}
