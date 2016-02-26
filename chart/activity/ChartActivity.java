package com.moneycontrol.handheld.chart.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.moneycontrol.handheld.AppData;
import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.adapter.ChartDropdownAdapter;
import com.moneycontrol.handheld.api.AppBeanParacable;
import com.moneycontrol.handheld.chart.data.GraphDayEntity;
import com.moneycontrol.handheld.chart.dialog.IndicatorDialogFragment;
import com.moneycontrol.handheld.chart.entity.CompareBean;
import com.moneycontrol.handheld.chart.entity.CompareEntity;
import com.moneycontrol.handheld.chart.entity.StickGraphBean;
import com.moneycontrol.handheld.chart.fragment.CandleStickFragment;
import com.moneycontrol.handheld.chart.fragment.LineChartFragment;
import com.moneycontrol.handheld.chart.fragment.OHLCChartFragment;
import com.moneycontrol.handheld.constant.AppConstants;
import com.moneycontrol.handheld.entity.messages.DropDownBean;
import com.moneycontrol.handheld.parser.ParseCall;
import com.moneycontrol.handheld.util.Constantlibs;
import com.moneycontrol.handheld.util.UrlUtils;
import com.moneycontrol.handheld.util.Utility;
import com.neopixl.pixlui.components.edittext.AutoCompleteEditText;
import com.neopixl.pixlui.components.textview.TextView;

public class ChartActivity extends ChartBaseActivity implements
		OnItemSelectedListener, TextWatcher, OnItemClickListener,
		OnClickListener, OnFocusChangeListener, OnTouchListener {
	public final static String GLOBALDATEFORMAT = "dd-MM-yyyy";
	public final static String DATEFORMAT_5DAYS = "dd MMM yyy hh:mm";
	Bundle bundle;
	String Id;
	String category, stockName;
	LayoutInflater mInflater;
	Spinner charts, times;
	// //1d,//5d//,

	int i = 0;
	String timeUrl;
	FrameLayout volumecontainer, chartcontainer;
	TextView chartTitle;
	ArrayList<GraphDayEntity> dayEntiy = new ArrayList<GraphDayEntity>();
	Bundle newBundle;
	AutoCompleteEditText addtoCompare, addtoCompare2;
	public boolean actionBarSetup = false;
	private Timer timer = new Timer();
	private final long DELAY = 500;
	private ArrayList<CompareEntity> searchlist = new ArrayList<CompareEntity>();
	private LinearLayout compare_with_layout, chartNameLayout,
			chartValueLayout;
	private RelativeLayout backlayout, compare3layout;
	private TextView tv_open, tv_close, tv_low, tv_high, tv_date, tv_volume;
	private TreeMap<Integer, String> stockslist = new TreeMap<Integer, String>();
	ArrayList<DropDownBean> typelist = new ArrayList<DropDownBean>();
	ArrayList<DropDownBean> tabs = new ArrayList<DropDownBean>();
	int color;
	private AsyncTask<String, Void, AppBeanParacable> searchKeyword;
	private Menu mMenu;
	RelativeLayout actionBarLayout, compareBarLayout;
	LinearLayout dynamicCompareLayout;
	int[] colors = new int[] { R.color.color_green, R.color.color_chart_blue,
			R.color.color_chart_orange };
	public static StickGraphBean movingArea;
	public boolean type_first = false, time = false;
	public String urlString;

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
		bundle = getIntent().getBundleExtra("data");
		Id = bundle.getString("Id");
		category = bundle.getString("Category");
		stockName = bundle.getString("stock");
		color = bundle.getInt("color");
		newBundle = new Bundle();
		newBundle.putString("url", bundle.getString("url"));
		urlString = bundle.getString("url");
		newBundle.putString("movingurl", bundle.getString("movingurl"));
		newBundle.putString("ex", bundle.getString("ex"));
		newBundle.putInt("color", color);
		newBundle.putString("Id", Id);
		newBundle.putString("Cat", category);
		newBundle.putBoolean("fill", true);
		colors = new int[] { color, R.color.color_chart_blue,
				R.color.color_chart_orange };
		stockslist.put(1, stockName);
		initIndicator();
		LineChartFragment fragment = new LineChartFragment();
		fragment.setArguments(newBundle);
		replaceFragment(fragment);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_bar_menu, menu);
		mMenu = menu;
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		showIndicator();
		return super.onOptionsItemSelected(item);

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		movingArea = null;
	}

	public void initIndicator() {
		if (Utility.enabledayAverage1(mContext)
				|| Utility.enabledayAverage2(mContext)) {

			if (getCurrentChartFragment() instanceof LineChartFragment) {
				LineChartFragment fragment = (LineChartFragment) getCurrentChartFragment();
				fragment.redrawSMA();
			}
		} else {
			if (getCurrentChartFragment() instanceof LineChartFragment) {
				LineChartFragment fragment = (LineChartFragment) getCurrentChartFragment();
				if (fragment.getSmalist().size() > 1) {
					fragment.redrawSMA();
				}
			}
		}
		if (Utility.getVolumeIndicator(ChartActivity.this)) {
			showVolume();
		} else {
			hideVolume();
		}
	}

	public void resetInitialActionBarSetup() {
		addtoCompare.setVisibility(View.VISIBLE);
		addtoCompare2.setVisibility(View.GONE);
		hideShowMenu(true);
	}

	public void initialActionBarSetUp(ArrayList<DropDownBean> typelist,
			ArrayList<DropDownBean> tabs) {
		if (!actionBarSetup) {
			this.typelist = typelist;
			this.tabs = tabs;
			ActionBar actionBar = getSupportActionBar();
			actionBar.setTitle("");
			actionBar.setDisplayHomeAsUpEnabled(false);
			actionBar.setDisplayShowCustomEnabled(true);
			actionBar.setDisplayShowTitleEnabled(false);
			actionBar.setHomeButtonEnabled(false);
			// actionBar.setDisplayUseLogoEnabled(false);
			actionBar.setDisplayShowHomeEnabled(false);
			actionBar.setCustomView(R.layout.header_action_bar);
			View v = actionBar.getCustomView();
			chartTitle = (TextView) v.findViewById(R.id.chart_title);
			LayoutParams lp = v.getLayoutParams();
			lp.width = LayoutParams.FILL_PARENT;
			v.setLayoutParams(lp);
			chartTitle.setText(stockName);
			charts = (Spinner) v.findViewById(R.id.chartspinner);
			times = (Spinner) v.findViewById(R.id.timespinner);
			compare_with_layout = (LinearLayout) v
					.findViewById(R.id.compare_with_layout);
			chartNameLayout = (LinearLayout) v
					.findViewById(R.id.chart_name_layout);
			chartValueLayout = (LinearLayout) v
					.findViewById(R.id.chart_value_layout);
			actionBarLayout = (RelativeLayout) v
					.findViewById(R.id.actionlayout);
			compareBarLayout = (RelativeLayout) v
					.findViewById(R.id.comparelayout);
			actionBarLayout.setVisibility(View.VISIBLE);
			compareBarLayout.setVisibility(View.GONE);
			dynamicCompareLayout = (LinearLayout) findViewById(R.id.comparemainlayout);
			ArrayAdapter<DropDownBean> timeAdapter = new ChartDropdownAdapter(
					mContext, tabs);
			ArrayAdapter<DropDownBean> diffAdapter = new ChartDropdownAdapter(
					mContext, typelist);
			diffAdapter.setDropDownViewResource(R.layout.dropdown_item);
			charts.setAdapter(diffAdapter);
			tv_close = (TextView) v.findViewById(R.id.tv_close);
			tv_open = (TextView) v.findViewById(R.id.tv_open);
			tv_high = (TextView) v.findViewById(R.id.tv_high);
			tv_low = (TextView) v.findViewById(R.id.tv_low);
			tv_date = (TextView) v.findViewById(R.id.tv_date);
			tv_volume = (TextView) v.findViewById(R.id.tv_volume);
			times.setAdapter(timeAdapter);
			times.setOnItemSelectedListener(this);
			charts.setOnItemSelectedListener(this);
			addtoCompare = (AutoCompleteEditText) v.findViewById(R.id.compare);
			addtoCompare2 = (AutoCompleteEditText) v
					.findViewById(R.id.add_compare);
			actionBarSetup = true;
			backlayout = (RelativeLayout) v.findViewById(R.id.tvback);
			compare3layout = (RelativeLayout) v
					.findViewById(R.id.compare3layout);
			compare3layout.setOnClickListener(this);

			backlayout.setOnClickListener(this);
			addtoCompare.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
			addtoCompare2.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
			addtoCompare2.addTextChangedListener(this);
			addtoCompare.addTextChangedListener(this);
			addtoCompare2.setOnItemClickListener(this);
			addtoCompare.setOnItemClickListener(this);
			addtoCompare.setVisibility(View.VISIBLE);
			addtoCompare2.setVisibility(View.GONE);
			addtoCompare2.setOnFocusChangeListener(this);
			addtoCompare.setOnFocusChangeListener(this);
			addtoCompare.setOnTouchListener(this);
			addtoCompare2.setOnTouchListener(this);
		}

	}

	public void showValue(String open, String close, String high, String low,
			String date, String volume) {

		chartValueLayout.setVisibility(View.VISIBLE);
		chartNameLayout.setVisibility(View.GONE);
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
		chartValueLayout.setVisibility(View.GONE);
		chartNameLayout.setVisibility(View.VISIBLE);
	}

	public void showCompare(int requestPosition, String stockName) {
		stockslist.put(requestPosition, stockName);
		resetCompareActionBar();
	}

	public void resetCompareActionBar() {
		if (stockslist.size() == 1) {
			compareBarLayout.setVisibility(View.GONE);
			actionBarLayout.setVisibility(View.VISIBLE);
			resetInitialActionBarSetup();
		} else {
			hideShowMenu(false);
			addtoCompare.setVisibility(View.GONE);
			addtoCompare2.setVisibility(View.VISIBLE);
			compareBarLayout.setVisibility(View.VISIBLE);
			actionBarLayout.setVisibility(View.GONE);
			dynamicCompareLayout.removeAllViews();
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			int i = 0;
			for (Integer key : stockslist.keySet()) {
				View view = inflater.inflate(R.layout.inflate_compare_layout,
						null);
				dynamicCompareLayout.addView(view);
				ImageView cross = (ImageView) view
						.findViewById(R.id.imagecross2);
				cross.setTag(key);
				cross.setOnClickListener(this);
				LinearLayout colorLayout = (LinearLayout) view
						.findViewById(R.id.color_layout);
				TextView stockNameTextView = (TextView) view
						.findViewById(R.id.compare2);
				stockNameTextView.setText(stockslist.get(key));
				colorLayout.setBackgroundColor(getResources().getColor(
						colors[i]));
				if (key == 2) {
					addtoCompare2.setVisibility(View.VISIBLE);
					cross.setVisibility(View.VISIBLE);
					LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) dynamicCompareLayout
							.getLayoutParams();
					params.weight = 2.0f;
					dynamicCompareLayout.invalidate();

				} else if (key == 3) {
					LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) dynamicCompareLayout
							.getLayoutParams();
					params.weight = 3.0f;
					addtoCompare2.setVisibility(View.GONE);
					cross.setVisibility(View.VISIBLE);
					dynamicCompareLayout.invalidate();
					// compare3innerlayout.setVisibility(View.VISIBLE);
				} else {
					cross.setVisibility(View.GONE);
				}
				++i;

			}
			if (addtoCompare != null) {
				addtoCompare.dismissDropDown();
			}
		}
	}

	public void hideCompare() {

	}

	void compareActionBar() {

		hideShowMenu(false);

	}

	public void updateActionBar() {

	}

	public void hideShowMenu(boolean value) {
		if (mMenu != null) {
			mMenu.findItem(R.id.menu_overflow).setVisible(value);
		}
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

		if (parent.getId() == charts.getId()) {
			if (type_first || position != 0) {
				updateCharts(Constantlibs.CHANGE_CHART, (DropDownBean) parent
						.getAdapter().getItem(position));
				type_first = true;

			}
		} else {
			if (time || position != 0) {

				updateCharts(Constantlibs.CHANGE_TIME, (DropDownBean) parent
						.getAdapter().getItem(position));
				time = true;
			}

		}
	}

	public void updateCharts(int changes, DropDownBean data) {
		Fragment fragment = getSupportFragmentManager().findFragmentById(
				R.id.chartcontainer);
		if (changes == Constantlibs.CHANGE_CHART) {
			if (data.getUniqueId().equalsIgnoreCase(AppConstants.UNIQUE_ID_1)
					|| data.getUniqueId().equalsIgnoreCase(
							AppConstants.UNIQUE_ID_2)) {
				showControl();
				if (fragment instanceof LineChartFragment) {
					if (data.getUniqueId().equalsIgnoreCase(
							AppConstants.UNIQUE_ID_2)) {
						((LineChartFragment) fragment).fillChart(false);
					} else {
						((LineChartFragment) fragment).fillChart(true);
					}
				} else {
					newBundle.putBoolean("fill", data.getUniqueId()
							.equalsIgnoreCase(AppConstants.UNIQUE_ID_1) ? true
							: false);
					DropDownBean dropDown = (DropDownBean) times
							.getSelectedItem();
					String dataUrl = data.getUrl();
					String range = UrlUtils.getVariable(dropDown.getUrl(),
							"range=");
					dataUrl = UrlUtils.getVariable(dataUrl, "range=", range);
					dataUrl = dataUrl + Utility.getCurrentUrlVersion(mContext);
					newBundle.putString("url", dataUrl + "&flag=1");
					newBundle.putString("uniqueId", dropDown.getUniqueId());

					LineChartFragment lineChartFragment = new LineChartFragment();
					lineChartFragment.setArguments(newBundle);
					replaceFragment(lineChartFragment);
				}
			} else if ((data.getName().equalsIgnoreCase(getResources()
					.getString(R.string.candle_stick)))
					|| (data.getUniqueId()
							.equalsIgnoreCase(AppConstants.UNIQUE_ID_3))) {

				String dataUrl = data.getUrl();
				if (charts.getSelectedItemPosition() == 0) {
					newBundle.putString("url", dataUrl + "&t_version="
							+ Constantlibs.T_VERSION + "&flag=1");

				} else {
					String url = ((DropDownBean) times.getSelectedItem())
							.getUrl();
					String range = UrlUtils.getVariable(url, "range=");
					dataUrl = UrlUtils.getVariable(dataUrl, "range=", range);
					dataUrl = dataUrl + Utility.getCurrentUrlVersion(mContext);
					newBundle.putString("url", dataUrl);
				}
				hideControl();
				CandleStickFragment candleStickFragment = new CandleStickFragment();
				candleStickFragment.setArguments(newBundle);
				replaceFragment(candleStickFragment);
			} else {
				hideControl();
				String dataUrl = data.getUrl();
				if (charts.getSelectedItemPosition() == 0) {
					newBundle.putString("url", dataUrl + "&t_version="
							+ Constantlibs.T_VERSION + "&flag=1");

				} else {
					String url = ((DropDownBean) times.getSelectedItem())
							.getUrl();
					String range = UrlUtils.getVariable(url, "range=");
					dataUrl = UrlUtils.getVariable(dataUrl, "range=", range);
					dataUrl = dataUrl + Utility.getCurrentUrlVersion(mContext);
					newBundle.putString("url", dataUrl);
				}

				OHLCChartFragment candleStickFragment = new OHLCChartFragment();
				candleStickFragment.setArguments(newBundle);
				replaceFragment(candleStickFragment);
			}
		} else {
			if (fragment instanceof LineChartFragment) {
				((LineChartFragment) fragment).updateChart(data.getUrl(),
						data.getUniqueId());
			} else if (fragment instanceof CandleStickFragment) {
				((CandleStickFragment) fragment).updateChart(data.getUrl());
			} else if (fragment instanceof OHLCChartFragment) {
				((OHLCChartFragment) fragment).updateChart(data.getUrl());
			}
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

	public void hideControl() {
		if (compare_with_layout != null) {
			compare_with_layout.setVisibility(View.GONE);
		}
	}

	public void showControl() {
		if (compare_with_layout != null) {
			compare_with_layout.setVisibility(View.VISIBLE);
		}
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

	public void showVolume() {
		LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) chartcontainer
				.getLayoutParams();
		params.weight = 1.4f;
		chartcontainer.setLayoutParams(params);

		LinearLayout.LayoutParams volumeParams = (android.widget.LinearLayout.LayoutParams) volumecontainer
				.getLayoutParams();
		volumeParams.weight = .6f;
		volumecontainer.setLayoutParams(volumeParams);
		volumecontainer.setVisibility(View.VISIBLE);
	}

	public void showIndicator() {
		// showVolume();
		FragmentManager fm = getSupportFragmentManager();
		IndicatorDialogFragment sortDialog = new IndicatorDialogFragment();
		sortDialog.setStyle(R.style.AlertDialogCustom, 0);
		sortDialog.setRetainInstance(true);
		sortDialog.show(fm, "sortDialog");
		sortDialog
				.setOndialogCallUpdate(new IndicatorDialogFragment.onDialogUpdateCallback() {

					@Override
					public void onDialogUpdate() {
						initIndicator();

					}
				});
	}

	class SearchWatchlistView extends ArrayAdapter<CompareEntity> {
		private LayoutInflater inflate = null;
		private ArrayList<CompareEntity> items = null;
		private final Context context;
		private boolean isSettings = false;

		// private Context context = null;
		public String keyword;

		public SearchWatchlistView(ArrayList<CompareEntity> item, Context con,
				String keyword) {
			super(con, R.layout.search_watchlist_item_layout, item);
			inflate = (LayoutInflater) con
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// context = con;
			items = item;
			context = con;
			this.keyword = keyword;

		}

		@Override
		public CompareEntity getItem(int position) {
			// TODO Auto-generated method stub
			return super.getItem(position);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			final int pos = position;

			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflate.inflate(
						R.layout.search_watchlist_item_layout, null);
				holder.heading = (TextView) convertView
						.findViewById(R.id.search_item_name);
				holder.marketmovers_item_name_ll = (LinearLayout) convertView
						.findViewById(R.id.search_item_name_ll);
				holder.expiry_date = (TextView) convertView
						.findViewById(R.id.expiry_date);
				Utility.getInstance().setTypeface(holder.heading,
						context.getApplicationContext());
				convertView.setBackgroundColor(getResources().getColor(
						R.color.color_area));
				convertView.setTag(holder);

			} else {

				holder = (ViewHolder) convertView.getTag();
			}
			Spanned htmlSpan;

			holder.heading.setText("" + items.get(pos).getShortname());
			htmlSpan = Html.fromHtml(holder.heading.getText().toString());

			holder.expiry_date.setVisibility(View.GONE);

			holder.heading
					.setText(addClickablePart(mContext, htmlSpan, keyword));
			holder.heading.setLinkTextColor(mContext.getResources().getColor(
					R.color.orange));

			return convertView;
		}

		class ViewHolder {
			public TextView heading, expiry_date;
			public LinearLayout marketmovers_item_name_ll;
		}

	}

	protected void checkForEditBoxValues(String keyword, int withOutSuggestions) {
		if (keyword.length() > 2) {
			if (ParseCall.getInstance().isOnlineWithoutException(mContext)) {

				fetchSearchData(keyword);

			} else {

			}
		} else {

		}

	}

	private void fetchSearchData(String keyword) {
		searchKeyword = new SearchKeyword(keyword);
		searchKeyword.execute(keyword);
	}

	private class SearchKeyword extends
			AsyncTask<String, Void, AppBeanParacable> {
		private String keyword;
		private SearchWatchlistView adapter;

		public SearchKeyword(String keyword) {
			this.keyword = keyword;
		}

		@Override
		protected AppBeanParacable doInBackground(String... params) {
			AppBeanParacable bean = null;
			if (params.length > 0) {
				String keyword = params[0].trim();
				if (!TextUtils.isEmpty(keyword)) {
					AppData MainController = AppData.getInstance();
					String path = MainController.getExtra_url().get(
							"stock_compare");
					String url;
					try {
						url = path + URLEncoder.encode(keyword, "UTF-8")
								+ "&t_version=11";
						url = url + "&" + ParseCall._deviceData;
						bean = ParseCall.getInstance().getCompareSearch(
								mContext, url);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} else {

			}
			return bean;
		}

		@Override
		protected void onPostExecute(AppBeanParacable result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (!isCancelled()) {
				if (result != null) {

					if (result instanceof CompareBean) {
						searchlist = ((CompareBean) result).getList();
						if (searchlist.size() > 0) {
							adapter = new SearchWatchlistView(searchlist,
									mContext, keyword);

							if (addtoCompare.getVisibility() == View.VISIBLE) {
								addtoCompare.setAdapter(adapter);
								if (addtoCompare.getText().toString().trim()
										.length() > 2) {
									addtoCompare.showDropDown();
								} else {
									addtoCompare.dismissDropDown();
								}
							} else if (addtoCompare2.getVisibility() == View.VISIBLE) {
								addtoCompare2.setAdapter(adapter);
								addtoCompare2.showDropDown();
								if (addtoCompare2.getText().toString().trim()
										.length() > 2) {
									addtoCompare2.showDropDown();
								} else {
									addtoCompare2.dismissDropDown();
								}
							} else if (addtoCompare.getVisibility() == View.GONE
									&& addtoCompare.getVisibility() == View.VISIBLE) {
								addtoCompare.dismissDropDown();
								addtoCompare2.dismissDropDown();

							}

						} else {
							addtoCompare.dismissDropDown();
						}

					} else {
					}
				}

			}
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (timer != null) {
			timer.cancel();
		}
	}

	private SpannableStringBuilder addClickablePart(final Context mContext,
			Spanned charSequence, String trailing) {
		SpannableStringBuilder ssb = new SpannableStringBuilder(charSequence);

		int idx1 = charSequence.toString().toLowerCase()
				.indexOf(trailing.toLowerCase().trim());
		int idx2 = 0;
		if (idx1 != -1) {
			idx2 = idx1 + trailing.length();

			ssb.setSpan(new ClickSpan(false), idx1, idx2,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		}

		return ssb;
	}

	private class ClickSpan extends ClickableSpan {

		public ClickSpan(boolean usertag) {
			// TODO Auto-generated constructor stub
			this.usertag = usertag;

		}

		boolean usertag = false;

		@Override
		public void onClick(View widget) {

		}

		@Override
		public void updateDrawState(TextPaint ds) {
			// TODO Auto-generated method stub
			super.updateDrawState(ds);
			ds.setUnderlineText(false);
		}

	}

	@Override
	public void afterTextChanged(Editable s) {
		if (addtoCompare2.getVisibility() == View.VISIBLE) {
			if (addtoCompare2.getText().toString().length() == 0) {
				addtoCompare2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0,
						0);

			} else {
				addtoCompare2.setCompoundDrawablesWithIntrinsicBounds(0, 0,
						R.drawable.dialog_ic_close_normal_holo_light, 0);
			}
		}
		if (addtoCompare.getVisibility() == View.VISIBLE) {
			if (addtoCompare.getText().toString().length() == 0) {
				addtoCompare
						.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

			} else {
				addtoCompare.setCompoundDrawablesWithIntrinsicBounds(0, 0,
						R.drawable.dialog_ic_close_normal_holo_light, 0);
			}
		}
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO: do what you need here (refresh list)
				// you will probably need to use
				// runOnUiThread(Runnable action) for some specific
				// actions
				if (addtoCompare2.getVisibility() == View.VISIBLE) {
					if (addtoCompare2.getText().toString().trim().length() > 2) {
						fetchSearchData(addtoCompare2.getText().toString()
								.trim());
					} else {

					}
				} else if (addtoCompare.getVisibility() == View.VISIBLE) {
					if (addtoCompare.getText().toString().trim().length() > 2) {
						fetchSearchData(addtoCompare.getText().toString()
								.trim());
					}
				}

			}

		}, DELAY);

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (timer != null)
			timer.cancel();

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View arg1, int position,
			long arg3) {
		if (searchKeyword != null) {
			searchKeyword.cancel(true);
		}

		Utility.getInstance().hideKeyBoard(this);
		CompareEntity entity = (CompareEntity) parent.getAdapter().getItem(
				position);
		String type = entity.getType();
		addtoCompare2.setText("");
		if (hasStocks(entity.getId())) {

			addtoCompare.setText("");
			addtoCompare2.setText("");
			showToast(getResources().getString(R.string.stocks_already_added));
			return;
		} else {

			String finalUrl = null;
			addStocks(entity.getId());
			// addtoCompare.removeTextChangedListener(this);
			addtoCompare.setText("");
			if (type.equalsIgnoreCase("stock")) {

				String url = ((DropDownBean) times.getSelectedItem()).getUrl();

				finalUrl = UrlUtils.getVariable(url, "sc_id=", entity.getId());
				finalUrl = finalUrl + Utility.getCurrentUrlVersion(mContext);

			} else if (type.equalsIgnoreCase("indice")) {
				String url = ((DropDownBean) times.getSelectedItem()).getUrl();

				finalUrl = UrlUtils.indicesChartAPI(mContext, entity.getId(),
						UrlUtils.getVariable(url, "range="));
			}
			Fragment fragment = getSupportFragmentManager().findFragmentById(
					R.id.chartcontainer);
			if (fragment instanceof LineChartFragment) {
				// ((LineChartFragment) fragment).fillChart(false);
				((LineChartFragment) fragment).compareIds(finalUrl);

			}
		}

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.imagecross2) {
			int key = (Integer) v.getTag();
			if (key == 2) {
				String value = stockslist.get(3);
				if (value != null) {
					stockslist.remove(key);
					stockslist.put(key, value);
					stockslist.remove(3);
				} else {
					stockslist.remove(key);
				}
			} else {
				stockslist.remove(key);
			}
			if (getCurrentChartFragment() instanceof LineChartFragment) {
				LineChartFragment fragment = (LineChartFragment) getCurrentChartFragment();
				fragment.removeDatasetOnPosition(key);
			}
		}
		if (v.getId() == backlayout.getId()) {

			for (int i = 3; i > 1; i--) {
				String value = stockslist.get(i);
				if (value != null) {
					stockslist.remove(i);
					if (getCurrentChartFragment() instanceof LineChartFragment) {
						LineChartFragment fragment = (LineChartFragment) getCurrentChartFragment();
						fragment.removeDatasetOnPosition(i);
					}
				}

			}
		}

	}

	public void updateCompareValue(int j) {
		resetCompareActionBar();

	}

	public boolean hasStocks(String id) {
		boolean avaiable = false;
		if (getCurrentChartFragment() instanceof LineChartFragment) {
			LineChartFragment fragment = (LineChartFragment) getCurrentChartFragment();
			int key = 0;
			for (int i = 0; i < fragment.getStockIds().size(); i++) {
				key = fragment.getStockIds().keyAt(i);
				String values = fragment.getStockIds().get(key);

				if (values.equalsIgnoreCase(id)) {
					avaiable = true;
					break;

				}
			}
		}
		return avaiable;
	}

	void showToast(String msg) {
		// Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();

		Utility.getInstance().showToast(mContext, msg, Toast.LENGTH_SHORT);
	}

	public void addStocks(String id) {
		if (getCurrentChartFragment() instanceof LineChartFragment) {
			LineChartFragment fragment = (LineChartFragment) getCurrentChartFragment();
			fragment.getStockIds().put(fragment.getStockIds().size(), id);
		}
	}

	public static String getFormattedDate(String currentFormat,
			String dateString) {

		SimpleDateFormat originalFormat = new SimpleDateFormat(currentFormat);
		SimpleDateFormat targetFormat = new SimpleDateFormat(GLOBALDATEFORMAT);
		Date date = null;
		try {
			date = originalFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (date != null) {
			return targetFormat.format(date);
		} else {
			return dateString;
		}
	}

	@Override
	public void onFocusChange(View arg0, boolean arg1) {

		if (addtoCompare.hasFocus()) {
			addtoCompare.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		} else if (addtoCompare2.hasFocus()) {
			addtoCompare2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		} else {
			addtoCompare.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.icon_search, 0, 0, 0);
			addtoCompare2.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.icon_search, 0, 0, 0);
			// addtoCompare.set
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		final int DRAWABLE_RIGHT = 2;

		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (v instanceof AutoCompleteEditText) {
				AutoCompleteEditText edittext = (AutoCompleteEditText) v;

				if (edittext.getCompoundDrawables()[DRAWABLE_RIGHT] != null) {
					if (event.getRawX() >= (edittext.getRight() - edittext
							.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds()
							.width())) {
						edittext.setText("");
						return true;
					}
				}
			}
		}
		return false;
	}

}
