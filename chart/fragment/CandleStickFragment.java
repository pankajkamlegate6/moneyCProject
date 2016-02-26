package com.moneycontrol.handheld.chart.fragment;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.api.AppBeanParacable;
import com.moneycontrol.handheld.api.RequestType;
import com.moneycontrol.handheld.chart.BarLineChartBase.BorderPosition;
import com.moneycontrol.handheld.chart.CandleStickChart;
import com.moneycontrol.handheld.chart.activity.ChartActivity;
import com.moneycontrol.handheld.chart.data.CandleData;
import com.moneycontrol.handheld.chart.data.CandleDataSet;
import com.moneycontrol.handheld.chart.data.CandleEntry;
import com.moneycontrol.handheld.chart.data.Entry;
import com.moneycontrol.handheld.chart.entity.StickChartEntity.Values;
import com.moneycontrol.handheld.chart.entity.StickGraphBean;
import com.moneycontrol.handheld.chart.interfaces.OnChartGestureListener;
import com.moneycontrol.handheld.chart.interfaces.OnChartValueSelectedListener;
import com.moneycontrol.handheld.chart.utils.LargeValueFormatter;
import com.moneycontrol.handheld.chart.utils.NumberFormatter;
import com.moneycontrol.handheld.chart.utils.XLabels.XLabelPosition;
import com.moneycontrol.handheld.chart.utils.YLabels.YLabelPosition;
import com.moneycontrol.handheld.massages.fragments.BaseFragement;
import com.moneycontrol.handheld.parser.ParseCall;
import com.moneycontrol.handheld.util.Constantlibs;
import com.moneycontrol.handheld.util.Utility;
import com.moneycontrol.handheld.watchlist.customview.CustomProgressDialog;

public class CandleStickFragment extends BaseFragement implements
		OnChartGestureListener, OnChartValueSelectedListener {
	private CandleStickChart mChart;
	private StickGraphBean graphData;
	private String url;
	private ArrayList<String> volumelist = new ArrayList<String>();
	ArrayList<Values> datalist = new ArrayList<Values>();
	private RelativeLayout progressBarr;

	public CandleStickChart getChart() {
		return mChart;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.url = getArguments().getString("url");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mainView = inflater.inflate(R.layout.fragment_candlestick, null);
		progressBarr = (RelativeLayout) mainView
				.findViewById(R.id.progressBarr);
		return mainView;

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		mChart = (CandleStickChart) findViewById(R.id.candlechart);

		mChart.setOnChartGestureListener(this);
		mChart.setOnChartValueSelectedListener(this);

		// if enabled, the chart will always start at zero on the y-axis
		mChart.setStartAtZero(false);

		// disable the drawing of values into the chart
		mChart.setDrawYValues(false);
		mChart.setGridWidth(2f);

		mChart.setDrawBorder(true);
		mChart.setBorderColor((getActivity().getResources()
				.getColor(R.color.color_chart_grey_line)));

		mChart.setBorderPositions(new BorderPosition[] { BorderPosition.BOTTOM,
				BorderPosition.LEFT, BorderPosition.RIGHT, BorderPosition.TOP });

		// no description text

		mChart.setHighlightEnabled(true);
		mChart.setDescription("");

		mChart.setHighlightEnabled(true);
		mChart.setTouchEnabled(true);
		mChart.setDragEnabled(false);
		mChart.setScaleEnabled(true);
		mChart.setPinchZoom(false);
		mChart.setHighlightIndicatorEnabled(true);
		mChart.getYLabels().setLabelCount(6);

		mChart.getXLabels().setTextColor(getColor(R.color.grey));
		// add data
		mChart.setDrawGridBackground(false);
		mChart.setGridColor(getColor(R.color.color_chart_grey_line));
		mChart.getYLabels().setPosition(YLabelPosition.RIGHT);
		mChart.getYLabels().setTextColor(
				getColor(R.color.color_chart_grey_line));
		mChart.getXLabels().setPosition(XLabelPosition.BOTTOM);
		// new NetworkTask().execute(0);

		setLabelColor();
		setLabelSize();
		setBorderColor();

		doBackgroundTask(RequestType.REQ_GET_GRAPH_DATA, url, progressBarr,
				false);

	}

	void setLabelColor() {
		mChart.getXLabels().setTextColor(getColor(R.color.grey));
		mChart.getYLabels().setTextColor(getColor(R.color.grey));
	}

	void setLabelSize() {
		mChart.getXLabels().setTextSize(9.99f);
		mChart.getYLabels().setTextSize(9.99f);
		mChart.getYLabels().setTypeface(
				Typeface.createFromAsset(getActivity().getAssets(),
						"fonts/Roboto-Regular.ttf"));
		mChart.getXLabels().setTypeface(
				Typeface.createFromAsset(getActivity().getAssets(),
						"fonts/Roboto-Regular.ttf"));
	}

	void setBorderColor() {
		mChart.setGridColor(getColor(R.color.color_chart_grid_line));
	}

	void setdata() {
		if (graphData != null && graphData.getGraph() != null) {
			volumelist.clear();
			ArrayList<CandleEntry> yVals1 = new ArrayList<CandleEntry>();
			datalist = graphData.getGraph().getList();
			ArrayList<String> time = new ArrayList<String>();
			for (int i = 0; i < datalist.size(); i++) {
				Values values = datalist.get(i);
				time.add(datalist.get(i).getTime());
				yVals1.add(new CandleEntry(i, values.getHigh(),
						values.getLow(), values.getOpen(), values.getClose()));
				volumelist.add("" + datalist.get(i).getVolume());
			}

			CandleDataSet set1 = new CandleDataSet(yVals1, "Data Set");
			// set1.setColors(new int[] { Color.RED, Color.GREEN });

			CandleData data = new CandleData(time, set1);
			mChart.animateX(1000);
			mChart.getYLabels().setFormatter(new NumberFormatter());
			mChart.setData(data);
			mChart.invalidate();
			VolumeFragment volumeFragment = (VolumeFragment) ((FragmentActivity) mContext)
					.getSupportFragmentManager().findFragmentById(
							R.id.volumefragment);
			volumeFragment.setDataUI(time, volumelist);

		} else {
			if (graphData != null && graphData.getMessage() != null) {
				mChart.setNoDataTextDescription(graphData.getMessage());
			}
			mChart.clear();
		}
	}

	@Override
	public void onValueSelected(Entry e, int dataSetIndex) {
		if (e instanceof CandleEntry) {
			if (getActivity() instanceof ChartActivity) {
				ChartActivity activity = (ChartActivity) getActivity();
				activity.hideShowMenu(false);
			}
			Values value = datalist.get(e.getXIndex());
			float open, close, high, low;
			open = ((CandleEntry) e).getOpen();
			close = ((CandleEntry) e).getClose();
			high = ((CandleEntry) e).getHigh();
			low = ((CandleEntry) e).getLow();
			if (getActivity() instanceof ChartActivity) {
				LargeValueFormatter formatter = new LargeValueFormatter();
				ChartActivity activity = (ChartActivity) getActivity();
				activity.showValue(
						Utility.getHtmlStringGraph("O : ", "" + open),
						Utility.getHtmlStringGraph("C : ", "" + close),
						Utility.getHtmlStringGraph("H : ", "" + high),
						Utility.getHtmlStringGraph("L : ", "" + low),
						Utility.getHtmlStringGraph(value.getTime(), ""),
						Utility.getHtmlStringGraph(
								"V : ",
								""
										+ formatter.getFormattedValue(value
												.getVolume())));
			}
		}

	}

	@Override
	public void onTaskComplete(int requestType, AppBeanParacable appBean) {
		// TODO Auto-generated method stub
		super.onTaskComplete(requestType, appBean);
		if (isAdded()) {
			if (appBean instanceof StickGraphBean) {
				graphData = (StickGraphBean) appBean;
				setdata();
			}
		}
	}

	@Override
	public void onNothingSelected() {
		if (getActivity() instanceof ChartActivity) {
			ChartActivity activity = (ChartActivity) getActivity();
			activity.hideValue();
			activity.hideShowMenu(true);
		}

	}

	@Override
	public void onChartLongPressed(MotionEvent me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChartDoubleTapped(MotionEvent me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub

	}

	public void updateChart(String time_tag) {
		if (time_tag != null) {
			String url = time_tag + "&type=stick&flag=1&t_version="
					+ Constantlibs.T_VERSION;

			doBackgroundTask(RequestType.REQ_GET_GRAPH_DATA, url, progressBarr,
					false);

		}

	}

	@Override
	public void onChartSingleTapped(MotionEvent me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

	}

}
