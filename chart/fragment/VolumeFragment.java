package com.moneycontrol.handheld.chart.fragment;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.chart.BarChart;
import com.moneycontrol.handheld.chart.Chart.CheckTouchPosition;
import com.moneycontrol.handheld.chart.activity.ChartActivity;
import com.moneycontrol.handheld.chart.data.BarData;
import com.moneycontrol.handheld.chart.data.BarDataSet;
import com.moneycontrol.handheld.chart.data.BarEntry;
import com.moneycontrol.handheld.chart.data.Entry;
import com.moneycontrol.handheld.chart.interfaces.OnChartGestureListener;
import com.moneycontrol.handheld.chart.interfaces.OnChartValueSelectedListener;
import com.moneycontrol.handheld.chart.utils.Highlight;
import com.moneycontrol.handheld.chart.utils.NumberFormatter;
import com.moneycontrol.handheld.chart.utils.XLabels.XLabelPosition;
import com.moneycontrol.handheld.chart.utils.YLabels.YLabelPosition;
import com.moneycontrol.handheld.massages.fragments.BaseFragement;
import com.moneycontrol.handheld.util.Utility;

public class VolumeFragment extends BaseFragement implements
		OnChartGestureListener, OnChartValueSelectedListener,
		CheckTouchPosition {

	private BarChart mChart;
	LinearLayout crosslayout;

	public android.support.v4.app.Fragment getCurrentChartFragment() {
		return getActivity().getSupportFragmentManager().findFragmentById(
				R.id.chartcontainer);
	}

	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			mainBundle = savedInstanceState.getBundle("data");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mainView = inflater.inflate(R.layout.volume_fragment, container, false);
		mChart = (BarChart) findViewById(R.id.barChart);
		crosslayout = (LinearLayout) findViewById(R.id.volume_cross);
		crosslayout.setOnClickListener(this);
		initComponent();
		return mainView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		// if enabled, the chart will always start at zero on the y-axis
		mChart.setStartAtZero(false);

		// disable the drawing of values into the chart
		mChart.setDrawYValues(false);
		mChart.setGridWidth(2f);

		mChart.setDrawBorder(false);

		// no description text

		mChart.setHighlightEnabled(true);
		mChart.setDescription("");
		mChart.setHighlightEnabled(true);
		mChart.setTouchEnabled(true);
		mChart.setDragEnabled(true);
		mChart.setScaleEnabled(true);
		mChart.setPinchZoom(true);
		mChart.setHighlightIndicatorEnabled(true);
		mChart.getYLabels().setLabelCount(2);

		// add data
		mChart.setDrawGridBackground(false);
		mChart.setGridColor(Color.TRANSPARENT);
		mChart.setDrawBorder(false);
		mChart.getYLabels().setPosition(YLabelPosition.RIGHT);
		mChart.setOnChartGestureListener(this);
		mChart.setOnChartValueSelectedListener(this);
		if (mainBundle == null) {
		} else {
			// setDataUI(mainBundle);
		}
		setLabelColor();
		setLabelSize();
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
		mChart.setDrawHorizontalGrid(false);
		mChart.setDrawBorder(false);
		mChart.getXLabels().setPosition(XLabelPosition.BOTTOM);
		mChart.setOnTouchPosition(this);
	}

	public void setDataUI(String[] times, ArrayList<String> values,
			double highvalue) {
		if (times != null) {
			ArrayList<String> xVals = new ArrayList<String>();
			for (int i = 0; i < times.length; i++) {
				String timesFormatted = ChartActivity.getFormattedDate(
						"dd MMM yyy hh:mm", times[i]);
				xVals.add(timesFormatted);
			}

			ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();

			for (int i = 0; i < values.size(); i++) {
				// float mult = (range + 1);
				// float val = (float) (Math.random() * mult) + 3;// + (float)
				// ((mult *
				double val = Double.valueOf(values.get(i)); // 0.1) / 10);
				yVals.add(new BarEntry((float) val, i));
			}

			// create a dataset and give it a type
			BarDataSet set1 = new BarDataSet(yVals, "DataSet 1");
			// set1.setFillAlpha(110);
			// set1.setFillColor(Color.RED);

			// set the line to be drawn like this "- - - - - -"
			// set1.enableDashedLine(10f, 5f, 0f);

			set1.setColor(getColor(R.color.color_chart_bar));
			//
			BarData data = new BarData(xVals, set1);
			mChart.setDrawXLabels(true);
			mChart.getYLabels().setFormatter(new NumberFormatter());
			mChart.setDrawGridBackground(false);
			mChart.setData(data);
			mChart.invalidate();
			mChart.animateX(1000);
		} else {
			mChart.clear();
		}
	}

	public void setDataUI(String[] times) {
		mChart.clear();
	}

	public void setDataUI(ArrayList<String> times, ArrayList<String> values) {
		if (times != null) {
			ArrayList<String> xVals = new ArrayList<String>();
			for (int i = 0; i < times.size(); i++) {
				xVals.add(times.get(i));
			}

			ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();

			for (int i = 0; i < values.size(); i++) {
				// float mult = (range + 1);
				// float val = (float) (Math.random() * mult) + 3;// + (float)
				// ((mult *
				double val = Double.valueOf(values.get(i)); // 0.1) / 10);
				yVals.add(new BarEntry((float) val, i));
			}

			// create a dataset and give it a type
			BarDataSet set1 = new BarDataSet(yVals, "DataSet 1");
			// set1.setFillAlpha(110);
			// set1.setFillColor(Color.RED);

			// set the line to be drawn like this "- - - - - -"
			// set1.enableDashedLine(10f, 5f, 0f);

			set1.setColor(getColor(R.color.color_chart_bar));
			//
			BarData data = new BarData(xVals, set1);
			mChart.setDrawXLabels(true);
			mChart.getYLabels().setFormatter(new NumberFormatter());
			mChart.setDrawGridBackground(false);
			mChart.setData(data);
			mChart.invalidate();
			mChart.animateXY(3000, 3000);
		} else {
			mChart.clear();
		}
	}

	private void initComponent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initVOL();

	}

	private void initVOL() {

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		if (v.getId() == crosslayout.getId()) {
			hideVolume();
		}

	}

	private void hideVolume() {

		if (getActivity() instanceof ChartActivity) {
			ChartActivity chartActivity = (ChartActivity) getActivity();
			chartActivity.hideVolume();
			Utility.setVolumeIndicator(getActivity(), false);
		}
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onValueSelected(Entry e, int dataSetIndex) {
		if (getCurrentChartFragment() instanceof LineChartFragment) {
			LineChartFragment fragment = (LineChartFragment) getCurrentChartFragment();

			fragment.onValueSelected(e, dataSetIndex);
		} else if (getCurrentChartFragment() instanceof OHLCChartFragment) {
			OHLCChartFragment fragment = (OHLCChartFragment) getCurrentChartFragment();
			fragment.onValueSelected(e, dataSetIndex);
		} else if (getCurrentChartFragment() instanceof CandleStickFragment) {
			CandleStickFragment fragment = (CandleStickFragment) getCurrentChartFragment();
			fragment.onValueSelected(e, dataSetIndex);
		}
	}

	@Override
	public void onNothingSelected() {
		if (getCurrentChartFragment() instanceof LineChartFragment) {
			LineChartFragment fragment = (LineChartFragment) getCurrentChartFragment();
			fragment.onNothingSelected();
			fragment.getLineChart().highlightTouch(null);
		} else if (getCurrentChartFragment() instanceof OHLCChartFragment) {
			OHLCChartFragment fragment = (OHLCChartFragment) getCurrentChartFragment();
			fragment.onNothingSelected();
			fragment.getChart().highlightTouch(null);
		} else if (getCurrentChartFragment() instanceof CandleStickFragment) {
			CandleStickFragment fragment = (CandleStickFragment) getCurrentChartFragment();
			fragment.onNothingSelected();
			fragment.getChart().highlightTouch(null);
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
	public void onChartSingleTapped(MotionEvent me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getTouchPositionEvent(MotionEvent e) {
		if (getCurrentChartFragment() instanceof LineChartFragment) {
			LineChartFragment fragment = (LineChartFragment) getCurrentChartFragment();
			Highlight h = fragment.getLineChart().getHighlightByTouchPoint(
					e.getX(), e.getY());

			fragment.getLineChart().highlightTouch(h);
			// mChart.touchPosition(e);

		} else if (getCurrentChartFragment() instanceof OHLCChartFragment) {
			OHLCChartFragment fragment = (OHLCChartFragment) getCurrentChartFragment();
			Highlight h = fragment.getChart().getHighlightByTouchPoint(
					e.getX(), e.getY());

			fragment.getChart().highlightTouch(h);
		} else if (getCurrentChartFragment() instanceof CandleStickFragment) {
			CandleStickFragment fragment = (CandleStickFragment) getCurrentChartFragment();
			Highlight h = fragment.getChart().getHighlightByTouchPoint(
					e.getX(), e.getY());

			fragment.getChart().highlightTouch(h);
		}

	}
}
