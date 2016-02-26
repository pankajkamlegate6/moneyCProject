package com.moneycontrol.handheld.chart.fragment;

import java.lang.Character.UnicodeBlock;
import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.api.AppBeanParacable;
import com.moneycontrol.handheld.api.RequestType;
import com.moneycontrol.handheld.chart.BarLineChartBase.BorderPosition;
import com.moneycontrol.handheld.chart.Chart.CheckTouchPosition;
import com.moneycontrol.handheld.chart.LineChart;
import com.moneycontrol.handheld.chart.activity.ChartActivity;
import com.moneycontrol.handheld.chart.activity.FutureChartActivty;
import com.moneycontrol.handheld.chart.data.Entry;
import com.moneycontrol.handheld.chart.data.LineData;
import com.moneycontrol.handheld.chart.data.LineDataSet;
import com.moneycontrol.handheld.chart.entity.StickChartEntity.Values;
import com.moneycontrol.handheld.chart.entity.StickChartEntity;
import com.moneycontrol.handheld.chart.entity.StickGraphBean;
import com.moneycontrol.handheld.chart.interfaces.OnChartGestureListener;
import com.moneycontrol.handheld.chart.interfaces.OnChartValueSelectedListener;
import com.moneycontrol.handheld.chart.utils.LargeValueFormatter;
import com.moneycontrol.handheld.chart.utils.XLabels.XLabelPosition;
import com.moneycontrol.handheld.chart.utils.YLabels.YLabelPosition;
import com.moneycontrol.handheld.massages.fragments.BaseFragement;
import com.moneycontrol.handheld.parser.ParseCall;
import com.moneycontrol.handheld.util.Utility;
import com.moneycontrol.handheld.watchlist.customview.CustomProgressDialog;
import com.neopixl.pixlui.components.textview.TextView;

public class FnOGraphFragment extends BaseFragement implements
		OnChartGestureListener, OnChartValueSelectedListener,
		CheckTouchPosition {
	private LineChart mChart;
	String[] times;
	String[] values;
	ArrayList<String> volumeArray = new ArrayList<String>();
	StickGraphBean graphData;
	Bundle bundle;
	private int color;
	String url;
	private RelativeLayout dialogLayout;
	float highValue, lowValue;
	int highPosition, lowPosition;
	private RelativeLayout progressBarr;
	private ArrayList<Values> graphValueDataList;
	private String uniqueId = "1";

	@Override
	public void onTaskComplete(int requestType, AppBeanParacable appBean) {
		// TODO Auto-generated method stub
		super.onTaskComplete(requestType, appBean);
		if (appBean instanceof StickGraphBean) {
			graphData = (StickGraphBean) appBean;
			if (graphData != null) {
				getGraphView(graphData);
			}
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mainView = inflater.inflate(R.layout.fragment_linechart, null);
		bundle = getArguments();
		if (bundle != null) {
			url = bundle.getString("Id");
			color = bundle.getInt("color");
			if (url == null) {

			}
		}
		return mainView;

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		mChart = (LineChart) findViewById(R.id.candlechart);
		dialogLayout = (RelativeLayout) findViewById(R.id.dialog_layout_value);
		progressBarr = (RelativeLayout) findViewById(R.id.progressBarr);

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
		mChart.setOnTouchPosition(this);
		mChart.getXLabels().setTextColor(getColor(R.color.grey));
		// add data
		mChart.setDrawGridBackground(false);
		mChart.setGridColor(getColor(R.color.color_chart_grey_line));
		mChart.getYLabels().setPosition(YLabelPosition.RIGHT);
		mChart.getYLabels().setTextColor(
				getColor(R.color.color_chart_grey_line));
		mChart.getXLabels().setPosition(XLabelPosition.BOTTOM);
		doBackgroundTask(RequestType.REQ_GET_GRAPH_DATA, url, progressBarr,
				false);
		setLabelColor();
		setLabelSize();
		setBorderColor();
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

	public void getGraphView(StickGraphBean gd) {
		highValue = 0;
		lowValue = 0;
		if (gd != null) {
			if (getActivity() instanceof FutureChartActivty) {
				FutureChartActivty activity = (FutureChartActivty) getActivity();
				activity.initialActionBarSetUp(gd);
			}
			graphValueDataList = gd.getGraph().getList();
			if (graphValueDataList != null && graphValueDataList.size() != 0) {
				float[] graphViewDataArray = new float[graphValueDataList
						.size()];
				times = new String[graphValueDataList.size()];
				values = new String[graphValueDataList.size()];
				volumeArray.clear();
				int i = 0;
				for (Values graphValueData : graphValueDataList) {
					graphViewDataArray[i] = graphValueData.getValue();
					times[i] = graphValueData.getTime();
					values[i] = "" + graphValueData.getValue();
					volumeArray.add("" + graphValueData.getVolume());
					if (i == 0) {
						lowValue = graphValueData.getLow();
						highValue = graphValueData.getHigh();
					}
					if (graphValueData.getHigh() > highValue) {
						highValue = graphValueData.getHigh();
						highPosition = i;

					} else if (graphValueData.getLow() < lowValue) {
						lowValue = graphValueData.getLow();
						lowPosition = i;
					}
					++i;
				}

				setData(true);
			}
		}

	}

	private void setData(boolean filled) {
		if (isAdded()) {
			if (times != null) {
				ArrayList<String> xVals = new ArrayList<String>();
				for (int i = 0; i < times.length; i++) {
					xVals.add(times[i]);
				}

				ArrayList<Entry> yVals = new ArrayList<Entry>();

				for (int i = 0; i < values.length; i++) {
					// float mult = (range + 1);
					// float val = (float) (Math.random() * mult) + 3;// +
					// (float)
					// ((mult *
					double val = Double.valueOf(values[i]); // 0.1) / 10);
					yVals.add(new Entry((float) val, i));
				}
				// create a dataset and give it a type
				LineDataSet lineSet = new LineDataSet(yVals, "DataSet 1");
				// set1.setFillAlpha(110);
				// set1.setFillColor(Color.RED);

				// set the line to be drawn like this "- - - - - -"
				if (graphData.getGraph() != null) {
					StickChartEntity entity = graphData.getGraph();
					String direction = entity.getDirection();
					if (direction != null) {
						if (direction.equalsIgnoreCase("-1")) {
							color = R.color.red;
						} else {
							color = R.color.green;
						}
					}
				}
				lineSet.setColor(getColor(color));
				lineSet.setLineWidth(5f);
				// set1.setCircleSize(4f);
				lineSet.setDrawFilled(true);
				lineSet.setFillAlpha(100);
				lineSet.disableDashedLine();
				lineSet.setFillColor(getColor(R.color.color_area));
				// set1.setShader(new LinearGradient(0, 0, 0,
				// mChart.getHeight(),
				// Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));

				// add the datasets

				// create a data object with the datasets
				LineData data = new LineData(xVals, lineSet);

				mChart.setData(data);
				mChart.invalidate();
				mChart.animateX(1000);
				// set data

				if (getActivity() instanceof FutureChartActivty) {
					FutureChartActivty activity = (FutureChartActivty) getActivity();
					// activity.hideVolume();
				}
			}

		}
	}

	@Override
	public void onValueSelected(Entry e, int dataSetIndex) {
		// TODO Auto-generated method stub
		String date = times[e.getXIndex()];
		date = ChartActivity.getFormattedDate("dd MMM yyy", date);
		// if (mChart.getHighImagePosition() == e.getXIndex()) {
		//
		// showCustomAlert(
		// mContext,
		// "" + date,
		// getActivity().getResources().getString(R.string.HighChart)
		// + Utility.twoDecimalFormat(mChart
		// .getHighImageValue()));
		// } else if (mChart.getLowImagePosition() == e.getXIndex()) {
		// showCustomAlert(
		// mContext,
		// "" + date,
		// getActivity().getResources().getString(R.string.LowChart)
		// + Utility.twoDecimalFormat(mChart
		// .getLowImageValue()));
		// } else {
		dialogLayout.setVisibility(View.GONE);
		//
		// }
		if (uniqueId.equalsIgnoreCase("1")) {
			if (e instanceof Entry) {
				float val;
				val = ((Entry) e).getVal();

				if (getActivity() instanceof FutureChartActivty) {
					FutureChartActivty activity = (FutureChartActivty) getActivity();
					String tag;
					if (date.length() <= 5) {
						tag = getActivity().getResources().getString(
								R.string.Timechart);
					} else {
						tag = getString(R.string.Datechart);
					}
					String vol = null;
					if (volumeArray.size() > 0) {
						vol = volumeArray.get(e.getXIndex());
						if (vol != null) {
							LargeValueFormatter formattter = new LargeValueFormatter();
							vol = Utility.getHtmlStringGraph(getActivity()
									.getResources()
									.getString(R.string.VolChart), formattter
									.getFormattedValue(Float.valueOf(vol)));
						}
					}
					activity.showValue(Utility.getHtmlStringGraph(
							getActivity().getResources().getString(
									R.string.txt_chart_price),
							Utility.twoDecimalFormat(val)), vol, Utility
							.getHtmlStringGraph(tag, date), null, null, null);
				}
			}
		} else {

			int indexx = 0;
			ArrayList<Values> list = graphValueDataList;
			if (list != null) {
				indexx = Utility.getIndexofDate(list, date);
			}
			Values vales = list.get(indexx);

			if (getActivity() instanceof FutureChartActivty) {
				FutureChartActivty activity = (FutureChartActivty) getActivity();
				if (date.length() <= 5) {
					tag = getActivity().getResources().getString(
							R.string.Timechart);
				} else {
					tag = getActivity().getResources().getString(
							R.string.Datechart);
				}
				float open, close, high, low;
				open = vales.getOpen();
				close = vales.getClose();
				high = vales.getHigh();
				low = vales.getLow();
				String vol = null;
				if (volumeArray.size() > 0) {
					vol = volumeArray.get(e.getXIndex());
					if (vol != null) {
						vol = Utility.getHtmlStringGraph("",
								LargeValueFormatter
										.makePrettyMoneyControlV2((Float
												.valueOf(vol))));
					}
				}
				activity.showValue(
						Utility.getHtmlStringGraph("O : ", "" + open),
						Utility.getHtmlStringGraph("C : ", "" + close),
						Utility.getHtmlStringGraph("H : ", "" + high),
						Utility.getHtmlStringGraph("L : ", "" + low),
						Utility.getHtmlStringGraph(date, ""),
						Utility.getHtmlStringGraph("V : ", "" + vol));
			}

		}

	}

	@Override
	public void onNothingSelected() {
		if (getActivity() instanceof FutureChartActivty) {
			FutureChartActivty activity = (FutureChartActivty) getActivity();
			activity.hideValue();

		}
		dialogLayout.setVisibility(View.GONE);
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

	}

	@Override
	public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub

	}

	public void updateChart(String tag, String uniqueId) {
		if (times != null) {
			this.uniqueId = uniqueId;
			url = tag;
			doBackgroundTask(RequestType.REQ_GET_GRAPH_DATA, tag, progressBarr,
					false);
		}
	}

	public void showCustomAlert(Context mContext, String date, String value) {

		dialogLayout.setVisibility(View.VISIBLE);
		TextView date_textView = (TextView) findViewById(R.id.date);
		TextView vaTextView_textView = (TextView) findViewById(R.id.value);
		date_textView.setText(date);
		vaTextView_textView.setText(value);

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public void onRefresh() {
		doBackgroundTask(RequestType.REQ_GET_GRAPH_DATA, url, progressBarr,
				false);

	}

	@Override
	public void getTouchPositionEvent(MotionEvent event) {
		if (dialogLayout != null
				&& dialogLayout.getVisibility() == View.VISIBLE) {

			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			if (dialogLayout.getWidth() <= event.getRawX() - Utility.pxtodp(20)) {
				params.leftMargin = (int) event.getRawX()
						- dialogLayout.getWidth() - Utility.pxtodp(20);
			} else {
				params.leftMargin = (int) event.getRawX() + Utility.pxtodp(20);

			}
			Log.e("response", "" + event.getRawY());
			// params.topMargin = (int) event.getRawY();
			dialogLayout.setLayoutParams(params);

		}
	}
}