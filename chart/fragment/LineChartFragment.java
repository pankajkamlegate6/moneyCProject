package com.moneycontrol.handheld.chart.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.api.AppBeanParacable;
import com.moneycontrol.handheld.api.RequestType;
import com.moneycontrol.handheld.chart.BarLineChartBase.BorderPosition;
import com.moneycontrol.handheld.chart.Chart.CheckTouchPosition;
import com.moneycontrol.handheld.chart.LineChart;
import com.moneycontrol.handheld.chart.activity.ChartActivity;
import com.moneycontrol.handheld.chart.data.Entry;
import com.moneycontrol.handheld.chart.data.LineData;
import com.moneycontrol.handheld.chart.data.LineDataSet;
import com.moneycontrol.handheld.chart.entity.StickChartEntity;
import com.moneycontrol.handheld.chart.entity.StickChartEntity.Values;
import com.moneycontrol.handheld.chart.entity.StickGraphBean;
import com.moneycontrol.handheld.chart.interfaces.OnChartGestureListener;
import com.moneycontrol.handheld.chart.interfaces.OnChartValueSelectedListener;
import com.moneycontrol.handheld.chart.utils.LargeValueFormatter;
import com.moneycontrol.handheld.chart.utils.MovingAverage;
import com.moneycontrol.handheld.chart.utils.NumberFormatter;
import com.moneycontrol.handheld.chart.utils.XLabels.XLabelPosition;
import com.moneycontrol.handheld.chart.utils.YLabels.YLabelPosition;
import com.moneycontrol.handheld.currency.entity.CurrenciesData.Refreshdata;
import com.moneycontrol.handheld.massages.fragments.BaseFragement;
import com.moneycontrol.handheld.parser.ParseCall;
import com.moneycontrol.handheld.util.UrlUtils;
import com.moneycontrol.handheld.util.Utility;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class LineChartFragment extends BaseFragement implements
        OnChartGestureListener, OnChartValueSelectedListener,
        CheckTouchPosition {
    private LineChart mChart;
    String[] times;
    String[] compareTime;
    String[] values;
    ArrayList<String> volumeArray;
    StickGraphBean graphData;
    Bundle bundle;

    String Id, Category;
    String url = null;
    private boolean filled = true;
    ;
    int[] colors = new int[]{R.color.color_green, R.color.color_chart_blue,
            R.color.color_chart_orange};
    boolean isCompare = false;
    private int color;
    ArrayList<ArrayList<Entry>> smalist = new ArrayList<ArrayList<Entry>>();
    ArrayList<ArrayList<Entry>> yValues = new ArrayList<ArrayList<Entry>>();
    SparseArray<ArrayList<Values>> fullValues = new SparseArray<ArrayList<Values>>();
    SparseArray<String> stockIds = new SparseArray<String>();
    private LinearLayout dialog_layout;
    private TextView name1, name2, name3, volume1, volume2, volume3,
            dialogHeader;
    private LinearLayout linearLayout2, linearLayout3, lllayout;
    private StickGraphBean gd;
    private String movingAverageurl;
    private Thread movingThread, movingfetch;
    private RelativeLayout dialog_value;
    String ex;
    String uniqueId = "1";
    float highValue, lowValue;
    int highPosition, lowPosition;
    private RelativeLayout progressBarr;
    int minSize = 0, minPosition = 0;

    public LineChart getLineChart() {
        return mChart;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

    }

    public ArrayList<ArrayList<Entry>> getSmalist() {
        return smalist;
    }

    public SparseArray<String> getStockIds() {
        return stockIds;
    }

    void setchartDialog() {
        linearLayout3 = (LinearLayout) findViewById(R.id.headinglayout3);
        linearLayout2 = (LinearLayout) findViewById(R.id.headinglayout2);
        lllayout = (LinearLayout) findViewById(R.id.lllayout);
        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);
        name3 = (TextView) findViewById(R.id.name3);
        volume1 = (TextView) findViewById(R.id.name1volume);
        volume2 = (TextView) findViewById(R.id.name2volume);
        volume3 = (TextView) findViewById(R.id.name3volume);
        dialogHeader = (TextView) findViewById(R.id.sort_dialog_header_text);
        lllayout.setBackgroundColor(getColor(color));
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
            Id = bundle.getString("Id");
            Category = bundle.getString("Cat");
            filled = bundle.getBoolean("fill");
            ex = bundle.getString("ex");
            uniqueId = bundle.getString("uniqueId") == null ? "1" : bundle
                    .getString("uniqueId");
            url = bundle.getString("url");
            stockIds.put(0, "" + Id);

            movingAverageurl = bundle.getString("movingurl");
            color = bundle.getInt("color");
            colors = new int[]{color, R.color.color_chart_blue,
                    R.color.color_chart_orange};
        }
        return mainView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        mChart = (LineChart) findViewById(R.id.candlechart);

        progressBarr = (RelativeLayout) findViewById(R.id.progressBarr);

        dialog_layout = (LinearLayout) findViewById(R.id.dialog_layout);
        dialog_value = (RelativeLayout) findViewById(R.id.dialog_layout_value);
        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
        setchartDialog();
        // if enabled, the chart will always start at zero on the y-axis
        mChart.setStartAtZero(false);

        // disable the drawing of values into the chart
        mChart.setDrawYValues(false);
        mChart.setGridWidth(2f);

        mChart.setDrawBorder(true);
        mChart.setBorderColor((getActivity().getResources()
                .getColor(R.color.color_chart_grey_line)));

        mChart.setBorderPositions(new BorderPosition[]{BorderPosition.BOTTOM,
                BorderPosition.LEFT, BorderPosition.RIGHT, BorderPosition.TOP});

        // no description text

        mChart.setHighlightEnabled(true);
        mChart.setDescription("");

        mChart.setHighlightEnabled(true);
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(true);
        mChart.setHighlightIndicatorEnabled(true);
        mChart.getYLabels().setLabelCount(6);
        // mChart.setYRange(315, 325, true); // add data
        mChart.setDrawGridBackground(false);

        mChart.getYLabels().setPosition(YLabelPosition.RIGHT);

        mChart.getXLabels().setPosition(XLabelPosition.BOTTOM);

        doRequest();
        mChart.setOnTouchPosition(this);
        setLabelColor();
        setLabelSize();
        setBorderColor();
        setZoom();

    }

    public void setZoom() {

    }

    void doRefresh() {
        doRequest();
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
        // get data in array
        highValue = 0;
        lowValue = 0;
        try {
            if (isAdded()) {
                ChartActivity activity = (ChartActivity) getActivity();
                activity.initialActionBarSetUp(gd.getDropdown(), gd.getTabs());
            }
            fullValues.clear();
            final ArrayList<Values> graphValueDataList = gd.getGraph()
                    .getList();
            if (graphValueDataList != null && graphValueDataList.size() != 0) {
                float[] graphViewDataArray = new float[graphValueDataList
                        .size()];
                this.gd = gd;
                times = new String[graphValueDataList.size()];
                values = new String[graphValueDataList.size()];

                volumeArray = new ArrayList<String>();
                volumeArray.clear();
                int i = 0;
                ArrayList<Values> fullValues = new ArrayList<StickChartEntity.Values>();
                for (Values graphValueData : graphValueDataList) {
                    graphViewDataArray[i] = graphValueData.getValue();

                    times[i] = graphValueData.getTime();
                    values[i] = "" + graphValueData.getValue();
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

                    fullValues.add(graphValueData);

                    volumeArray.add("" + graphValueData.getVolume());

                    ++i;

                }

                setData(filled);
            }
        } catch (Exception e) {
            mChart.clear();
            if (!isCompare) {
                VolumeFragment volumeFragment = (VolumeFragment) ((FragmentActivity) mContext)
                        .getSupportFragmentManager().findFragmentById(
                                R.id.volumefragment);
                volumeFragment.setDataUI(null);
            } else {
                if (getActivity() instanceof ChartActivity) {
                    ChartActivity activity = (ChartActivity) getActivity();
                    activity.hideVolume();
                }
            }
        }

    }

    private void setData(boolean filled) {
        if (!isCompare) {
            yValues.clear();
            fullValues.clear();
            smalist.clear();
        }
        if (times != null) {
            ArrayList<String> xVals = new ArrayList<String>();
            for (int i = 0; i < times.length; i++) {
                String timesFormatted = ChartActivity.getFormattedDate(
                        "dd MMM yyy hh:mm", times[i]);
                xVals.add(timesFormatted);
            }

            ArrayList<Entry> yVals = new ArrayList<Entry>();
            for (int i = 0; i < values.length; i++) {
                double val = Double.valueOf(values[i]); // 0.1) / 10);
                yVals.add(new Entry((float) val, i));

            }
            yValues.add(yVals);
            smalist.add(yVals);
            fullValues.put(0, gd.getGraph().getList());
            // create a dataset and give it a type
            LineDataSet lineSet = new LineDataSet(yVals, "DataSet 1");
            // set1.setFillAlpha(110);
            // set1.setFillColor(Color.RED);

            // set the line to be drawn like this "- - - - - -"
            lineSet.enableDashedLine(10f, 5f, 0f);
            if (gd.getGraph() != null) {
                StickChartEntity entity = gd.getGraph();
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
            if (filled) {
                lineSet.setLineWidth(5f);
            } else {
                lineSet.setLineWidth(3f);
            }
            // set1.setCircleSize(4f);
            lineSet.setFillAlpha(100);
            lineSet.disableDashedLine();
            mChart.setCompare(false);
            lineSet.setDrawFilled(this.filled);
            lineSet.setFillColor(getColor(R.color.color_area));
            if (uniqueId.equalsIgnoreCase("1")
                    || uniqueId.equalsIgnoreCase("2")) {
                lineSet.addImages(false);
            } else {
                lineSet.addImages(true);
            }

            if (uniqueId.equalsIgnoreCase("1")) {
                Refreshdata data = graphData.getRefreshdata();
                String rate = data.getRate();
                boolean flag = data.isFlag();
                if (flag) {
                    setAutoGraphRefresh(flag, rate);
                }
            } else {
                stopAutoRefreshV2();
            }
            mChart.setHighImageValue(highValue);
            mChart.setLowImageValue(lowValue);
            mChart.setHighImagePosition(highPosition);
            mChart.setLowImagePosition(lowPosition);
            mChart.getYLabels().setFormatter(new NumberFormatter());
            // set1.setShader(new LinearGradient(0, 0, 0, mChart.getHeight(),
            // Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));

            // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(xVals, lineSet);

            mChart.setData(data);
            mChart.invalidate();
            mChart.animateX(1000);
            // mChart.setPinchZoom(enabled)
            // set data

            if (!isCompare) {
                VolumeFragment volumeFragment = (VolumeFragment) ((FragmentActivity) mContext)
                        .getSupportFragmentManager().findFragmentById(
                                R.id.volumefragment);
                volumeFragment.setDataUI(times, volumeArray, highValue);
                if (volumeArray.size() == 0) {
                    if (getActivity() instanceof ChartActivity) {
                        ChartActivity activity = (ChartActivity) getActivity();
                        activity.hideVolume();
                    }
                } else {
                    if (getActivity() instanceof ChartActivity) {
                        ChartActivity activity = (ChartActivity) getActivity();
                        activity.initIndicator();

                    }

                }
                if (Utility.enabledayAverage1(mContext)
                        || Utility.enabledayAverage2(mContext)) {
                    redrawSMA();
                }
            } else {
                if (getActivity() instanceof ChartActivity) {
                    ChartActivity activity = (ChartActivity) getActivity();
                    activity.hideVolume();
                }
            }
        }
    }

    @Override
    public void performAutoGraphRefresh() {
        // TODO Auto-generated method stub
        super.performAutoGraphRefresh();
        if (uniqueId.equalsIgnoreCase("1")) {
            doRequest();
        }

    }

    private void getMovingdata() {
        if (movingThread != null) {
            movingThread.interrupt();
        }
        movingThread = new Thread(new Runnable() {

            @Override
            public void run() {
                if (isAdded()) {
                    // String movingUrl = String.format(movingAverageurl, Id);
                    String movingUrl = UrlUtils.getStockGraphUrl(
                            movingAverageurl, ex, Id);
                    try {
                        ChartActivity.movingArea = ParseCall.getInstance()
                                .getStickGraphData(getActivity(), movingUrl);
                        movingHandler.sendEmptyMessage(0);
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }
            }
        });
        movingThread.start();
    }

    private Handler movingHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if (msg.what == 0) {

                Thread t = new Thread(new Runnable() {

                    @Override
                    public void run() {

                        if (mContext != null) {
                            if (gd != null && ChartActivity.movingArea != null) {
                                final SparseArray<ArrayList<Float>> averageList = new SparseArray<ArrayList<Float>>();
                                if (Utility.enabledayAverage1(mContext)) {
                                    int days = Utility.getAverageDay1(mContext);
                                    MovingAverage average = new MovingAverage(days,
                                            ChartActivity.movingArea.getGraph()
                                                    .getList(), gd.getGraph()
                                            .getList());
                                    average.doCompute();
                                    if (average.canAdded()) {
                                        averageList.put(averageList.size(),
                                                average.getAveragelist());
                                    }

                                }
                                if (Utility.enabledayAverage2(mContext)) {
                                    int days = Utility.getAverageDay2(mContext);
                                    MovingAverage average = new MovingAverage(days,
                                            ChartActivity.movingArea.getGraph()
                                                    .getList(), gd.getGraph()
                                            .getList());
                                    average.doCompute();
                                    if (average.canAdded()) {
                                        averageList.put(averageList.size(),
                                                average.getAveragelist());
                                    }
                                }
                                if (averageList.size() > 0) {

                                    if (getActivity() != null) {
                                        getActivity().runOnUiThread(new Runnable() {

                                            @Override
                                            public void run() {
                                                addSma(averageList);

                                            }
                                        });
                                    }
                                }
                            }
                        }
                    }
                });
                t.start();
            }

        }
    };

    public void addSma(SparseArray<ArrayList<Float>> averageList) {
        smalist.clear();
        if (yValues.size() > 0) {
            smalist.add(yValues.get(0));
        } else {
            return;
        }
        mChart.clear();
        if (times != null) {
            ArrayList<String> xVals = new ArrayList<String>();
            for (int i = 0; i < times.length; i++) {
                xVals.add(times[i]);
            }

            for (int i = 0; i < averageList.size(); i++) {
                int key = averageList.keyAt(i);
                ArrayList<Float> list = averageList.get(key);
                if (list != null) {
                    ArrayList<Entry> yVals = new ArrayList<Entry>();
                    for (int j = 0; j < list.size(); j++) {
                        float val = Float.valueOf(list.get(j)); // 0.1) / 10);
                        yVals.add(new Entry((float) val, j));

                    }
                    smalist.add(yVals);
                }
            }

            ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();

            for (int z = 0; z < smalist.size(); z++) {

                ArrayList<Entry> entrys = smalist.get(z);

                LineDataSet lineset = new LineDataSet(entrys, "DataSet "
                        + (z + 1));
                int color;
                if (z == 0) {
                    color = getColor(this.color);
                } else {
                    color = getColor(colors[z]);
                }

                lineset.setColor(color);

                if (z < colors.length) {
                    lineset.setCircleColor(color);
                    lineset.setDrawFilled(false);
                    if (z == 0) {
                        lineset.setLineWidth(3f);
                        lineset.addImages(true);
                        lineset.setFillAlpha(100);
                        lineset.setDrawFilled(this.filled);
                        lineset.setFillColor(getColor(R.color.color_area));
                    } else {
                        lineset.setLineWidth(1f);
                        lineset.addImages(false);
                        lineset.setDrawFilled(false);
                    }

                    dataSets.add(lineset);
                }
            }

            // make the first DataSet dashed

            LineData data = new LineData(xVals, dataSets);
            mChart.setData(data);
            mChart.animateX(1000);
            mChart.invalidate();

        }

    }

    private void drawSMA() {
        movingHandler.sendEmptyMessage(0);

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex) {
        // TODO Auto-generated method stub
        String date = times[e.getXIndex()];
        if (getActivity() instanceof ChartActivity) {
            ChartActivity activity = (ChartActivity) getActivity();
            activity.hideShowMenu(false);
        }
        // date = ChartActivity.getFormattedDate("dd MMM yyy", date);
        if (yValues.size() > 1) {
            updateChartDialog(e.getXIndex());
        } else {

            if (mChart.getHighImagePosition() == e.getXIndex()) {

                showCustomAlert(
                        mContext,
                        "" + date,
                        "High: "
                                + Utility.twoDecimalFormat(mChart
                                .getHighImageValue()));
            } else if (mChart.getLowImagePosition() == e.getXIndex()) {
                showCustomAlert(
                        mContext,
                        "" + date,
                        "Low: "
                                + Utility.twoDecimalFormat(mChart
                                .getLowImageValue()));
            } else {
                dialog_value.setVisibility(View.GONE);

            }
            if (uniqueId.equalsIgnoreCase("1")
                    || uniqueId.equalsIgnoreCase("2")) {

                if (e instanceof Entry) {
                    float val;
                    val = ((Entry) e).getVal();

                    if (getActivity() instanceof ChartActivity) {
                        ChartActivity activity = (ChartActivity) getActivity();
                        String tag = "";
                        if (date.length() <= 5) {
                            tag = getActivity().getResources().getString(
                                    R.string.Timechart);
                        } else {
                            tag = getActivity().getResources().getString(
                                    R.string.Datechart);
                        }
                        String vol = null;
                        if (volumeArray.size() > 0) {
                            vol = volumeArray.get(e.getXIndex());
                            if (vol != null) {
                                vol = Utility
                                        .getHtmlStringGraph(
                                                getActivity()
                                                        .getResources()
                                                        .getString(
                                                                R.string.VolChart),
                                                LargeValueFormatter
                                                        .makePrettyMoneyControlV2((Float
                                                                .valueOf(vol))));
                            }
                        }
                        activity.showValue(Utility
                                        .getHtmlStringGraph(tag, date), vol, Utility
                                        .getHtmlStringGraph(
                                                getActivity().getResources().getString(
                                                        R.string.txt_chart_price),
                                                Utility.twoDecimalFormat(val)), null,
                                null, null);
                    }
                }
            } else {
                int indexx = 0;
                ArrayList<Values> list = fullValues.get(Integer.valueOf(0));
                if (list != null) {
                    indexx = getIndexofDate(list, date);
                }
                Values vales = list.get(indexx);

                if (getActivity() instanceof ChartActivity) {
                    ChartActivity activity = (ChartActivity) getActivity();
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

    }

    @Override
    public void onNothingSelected() {

        if (getActivity() instanceof ChartActivity) {
            ChartActivity activity = (ChartActivity) getActivity();
            activity.hideValue();
            activity.hideShowMenu(true);
        }
        if (dialog_layout != null) {
            dialog_layout.setVisibility(View.GONE);
        }
        if (dialog_value != null) {
            dialog_value.setVisibility(View.GONE);
        }

    }

    public void updateChartDialog(int indexx) {
        String date;
        if (compareTime != null) {
            date = compareTime[indexx];
        } else {
            date = times[indexx];
        }// date = "24 Aug 2015";
        ArrayList<Values> datalist = new ArrayList<Values>();
        for (int i = 0; i < fullValues.size(); i++) {
            ArrayList<Values> list = fullValues.get(Integer.valueOf(i));
            if (list != null) {
                indexx = getIndexofDate(list, date);
                if (indexx != -1) {
                    float firstItem, item;
                    if (list.get(0).getClose() != 0) {
                        firstItem = list.get(0).getClose();
                    } else {
                        firstItem = list.get(0).getValue();
                    }

                    Values entry = list.get(indexx);
                    if (entry.getClose() != 0) {
                        item = entry.getClose();
                    } else {
                        item = entry.getValue();
                    }
                    float valueItem = (item - firstItem) / firstItem;
                    valueItem = valueItem * 100;
                    entry.setPchg(Utility.twoDecimalFormat(valueItem));
                    datalist.add(entry);
                    dialog_layout.setVisibility(View.VISIBLE);

                }
            }
        }
        showChartDialog(date, indexx, datalist);

    }

    private int getIndexofDate(ArrayList<Values> list, String date) {
        int position = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTime().equalsIgnoreCase(date)) {
                position = i;
                break;
            }
        }
        return position;
    }

    private void showChartDialog(String date, int indexx,
                                 ArrayList<Values> datalist) {

        for (int i = 0; i < datalist.size(); i++) {
            String color;
            int direction = 0;
            if (datalist.get(i).getPchg() >= 0) {
                color = "#66CC00";
                direction = 1;
            } else {
                color = "#FF0033";
                direction = 0;

            }
            String htmlString = "<font color=#FFFFFF>%s</font> <font color="
                    + color + ">%s</font>";
            if (i == 0) {

                if (direction == 0) {
                    name1.setText(Html.fromHtml(String.format(htmlString,
                            datalist.get(i).getValue(), datalist.get(i)
                                    .getPchg() + "%")));
                } else {

                    name1.setText(Html.fromHtml(String.format(htmlString,
                            datalist.get(i).getValue(), "+"
                                    + datalist.get(i).getPchg() + "%")));
                }

                linearLayout3.setVisibility(View.GONE);

                volume1.setText("Vol: "
                        + LargeValueFormatter.makePrettyMoneyControlV2(datalist
                        .get(i).getVolume()));

            } else if (i == 1) {

                volume2.setText("Vol: "
                        + LargeValueFormatter.makePrettyMoneyControlV2(datalist
                        .get(i).getVolume()));
                linearLayout2.setVisibility(View.VISIBLE);

                // linearLayout2.setVisibility(View.GONE);

                linearLayout3.setVisibility(View.GONE);
                name2.setText(Html.fromHtml(String.format(htmlString, datalist
                        .get(i).getValue(), datalist.get(i).getPchg() + "%")));

                if (direction == 0) {
                    name2.setText(Html.fromHtml(String.format(htmlString,
                            datalist.get(i).getValue(), datalist.get(i)
                                    .getPchg() + "%")));
                } else {

                    name2.setText(Html.fromHtml(String.format(htmlString,
                            datalist.get(i).getValue(), "+"
                                    + datalist.get(i).getPchg() + "%")));
                }

            } else if (i == 2) {
                volume3.setText("Vol: "
                        + LargeValueFormatter.makePrettyMoneyControlV2(datalist
                        .get(i).getVolume()));
                linearLayout3.setVisibility(View.VISIBLE);

                if (direction == 0) {
                    name3.setText(Html.fromHtml(String.format(htmlString,
                            datalist.get(i).getValue(), datalist.get(i)
                                    .getPchg() + "%")));
                } else {

                    name3.setText(Html.fromHtml(String.format(htmlString,
                            datalist.get(i).getValue(), "+"
                                    + datalist.get(i).getPchg() + "%")));
                }
            }

            dialogHeader.setText(date);

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

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX,
                             float velocityY) {
        // TODO Auto-generated method stub

    }

    public void updateChart(String url, String uniqueId) {
        if (times != null) {
            this.uniqueId = uniqueId;
            this.url = url;

            doBackgroundTask(RequestType.REQ_GET_GRAPH_DATA, url, progressBarr,
                    false);
        }
    }

    @Override
    public void onTaskComplete(int requestType, AppBeanParacable appBean) {
        // TODO Auto-generated method stub
        super.onTaskComplete(requestType, appBean);
        if (appBean instanceof StickGraphBean) {
            graphData = (StickGraphBean) appBean;
            if (graphData != null) {
                if (!isCompare) {
                    getGraphView(graphData);
                } else {
                    if (graphData.getGraph().getStatus() != null) {
                        showToast(graphData.getGraph().getStatus());
                        stockIds.remove(stockIds.size() - 1);

                    } else {

                        getGraphViewCompareData(graphData);
                    }
                }
            }
        }
    }

    public void fillChart(boolean filled) {
        this.filled = filled;
        setData(filled);
    }

    public void showCustomAlert(Context mContext, String date, String value) {
        if (!uniqueId.equalsIgnoreCase("1") && !uniqueId.equalsIgnoreCase("2")) {
            dialog_value.setVisibility(View.VISIBLE);
            TextView date_textView = (TextView) findViewById(R.id.date);
            TextView vaTextView_textView = (TextView) findViewById(R.id.value);
            date_textView.setText(date);
            vaTextView_textView.setText(value);
        }

    }

    public void addCompareDataset() {
        mChart.setCompare(true);
        if (movingfetch != null) {
            movingfetch.interrupt();
        }
        if (movingThread != null) {
            movingThread.interrupt();
        }
        minPosition = 0;
        ArrayList<String> xVals = new ArrayList<String>();

        if (fullValues.size() > 0) {
            minSize = fullValues.get(0).size();

        }
        for (int z = 0; z < fullValues.size(); z++) {
            int tempSize = fullValues.get(z).size();
            if (tempSize < minSize) {
                minSize = tempSize;
                minPosition = z;

            }
        }
        compareTime = new String[fullValues.get(minPosition).size()];
        for (int i = 0; i < fullValues.get(minPosition).size(); i++) {
            compareTime[i] = fullValues.get(minPosition).get(i).getTime();
            xVals.add(compareTime[i]);
        }

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();

        for (int z = 0; z < fullValues.size(); z++) {
            ArrayList<Entry> values = new ArrayList<Entry>();
            ArrayList<Values> items = fullValues.get(z);
            if (items != null) {
                float firstItem;
                if (items.get(0).getClose() != 0) {
                    firstItem = items.get(0).getClose();
                } else {
                    firstItem = items.get(0).getValue();
                }

                float valueItem = 0;
                int j = 0;
                for (Values val : items) {
                    if (Arrays.asList(compareTime).indexOf(val.getTime()) != -1) {
                        if (items.get(j).getClose() != 0) {

                            valueItem = val.getClose();
                        } else {
                            valueItem = val.getValue();
                        }
                        valueItem = (valueItem - firstItem) / firstItem;
                        valueItem = valueItem * 100;

                        values.add(new Entry((Float.valueOf(Utility
                                .twoDecimalFormatOnly(valueItem))), j));
                        j++;
                    }
                }

                LineDataSet lineset = new LineDataSet(values, "DataSet "
                        + (z + 1));
                mChart.getYLabels().setFormatter(null);
                lineset.setLineWidth(3f);
                if (z < colors.length) {
                    int color = getColor(colors[z]);
                    lineset.setColor(color);
                    lineset.setCircleColor(color);
                    lineset.setDrawFilled(false);
                    lineset.addImages(false);
                    dataSets.add(lineset);
                }
            }
        }

        // make the first DataSet dashed

        LineData data = new LineData(xVals, dataSets);
        mChart.setData(data);
        mChart.invalidate();
        mChart.animateX(1000);
        if (getActivity() instanceof ChartActivity) {
            ChartActivity activity = (ChartActivity) getActivity();
            activity.hideVolume();
        }
        stopAutoRefreshV2();
    }

    public void compareIds(String Id) {
        if (Id != null) {
            isCompare = true;
            doBackgroundTask(RequestType.REQ_GET_GRAPH_DATA, Id, progressBarr,
                    false);
        }
    }

    public void getGraphViewCompareData(StickGraphBean gd) {
        // get data in array
        highValue = 0;
        lowValue = 0;
        if (gd != null) {
            if (gd.getGraph() != null) {
                final ArrayList<Values> graphValueDataList = gd.getGraph()
                        .getList();
                if (graphValueDataList != null
                        && graphValueDataList.size() != 0) {
                    volumeArray.clear();
                    int i = 0;
                    ArrayList<Entry> list = new ArrayList<Entry>();
                    ArrayList<Values> values = new ArrayList<Values>();
                    for (Values graphValueData : graphValueDataList) {

                        volumeArray.add("" + graphValueData.getVolume());
                        values.add(graphValueData);
                        double val = Double.valueOf(graphValueData.getValue()); // 0.1)
                        // /
                        // 10);

                        list.add(new Entry((float) val, i));
                        ++i;

                    }
                    fullValues.put(yValues.size(), values);
                    yValues.add(list);

                }
                if (getActivity() instanceof ChartActivity) {
                    ChartActivity activity = (ChartActivity) getActivity();
                    activity.showCompare(yValues.size(), gd.getGraph()
                            .getName());
                }
                addCompareDataset();
            }
        }

    }

    public float newMax(String[] compareValues) {
        float max = Float.valueOf(compareValues[0]);

        for (int i = 1; i < compareValues.length; i++) {
            if (!TextUtils.isEmpty(compareValues[i])) {
                if (Float.valueOf(compareValues[i]) > max) {
                    max = Float.valueOf(compareValues[i]);
                }
            }
        }

        return max;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onRefresh() {
        doRefresh();

    }

    public void redrawCompareGraph() {
        addCompareDataset();
    }

    public void removeDatasetOnPosition(int key) {
        LineData data = mChart.getData();
        int position = key;
        if (data != null) {
            yValues.remove((position - 1));

            if (position == 2) {
                ArrayList<Values> list = fullValues.get(2);
                if (list != null) {
                    fullValues.setValueAt((position - 1), list);
                    fullValues.remove(position);
                } else {
                    fullValues.remove(position - 1);
                }
            } else {
                fullValues.remove(position - 1);
            }
            stockIds.remove((position - 1));
            mChart.clear();
            mChart.notifyDataSetChanged();
            mChart.invalidate();

            if (yValues.size() == 1) {
                isCompare = false;
                // fillChart(filled);
                getGraphView(gd);
            } else {
                redrawCompareGraph();
            }
            if (getActivity() instanceof ChartActivity) {
                ChartActivity activity = (ChartActivity) getActivity();

                activity.updateCompareValue(position);

            }
        }
    }

    @Override
    public void getTouchPositionEvent(MotionEvent event) {

        if (yValues.size() > 1) {
            if (dialog_layout != null
                    && dialog_layout.getVisibility() == View.VISIBLE) {

                RelativeLayout.LayoutParams params = new LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                if (dialog_layout.getWidth() <= event.getRawX()
                        - Utility.pxtodp(20)) {
                    params.leftMargin = (int) event.getRawX()
                            - dialog_layout.getWidth() - Utility.pxtodp(20);
                } else {
                    params.leftMargin = (int) event.getRawX()
                            + Utility.pxtodp(20);

                }
                dialog_layout.setLayoutParams(params);

            }
        } else {
            if (dialog_value != null
                    && dialog_value.getVisibility() == View.VISIBLE) {

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                if (dialog_value.getWidth() <= event.getRawX()
                        - Utility.pxtodp(20)) {
                    params.leftMargin = (int) event.getRawX()
                            - dialog_value.getWidth() - Utility.pxtodp(20);
                } else {
                    params.leftMargin = (int) event.getRawX()
                            + Utility.pxtodp(20);

                }
                Log.e("response", "" + event.getRawY());
                // params.topMargin = (int) event.getRawY();
                dialog_value.setLayoutParams(params);

            }
        }
    }

    public void redrawSMA() {
        if (Utility.enabledayAverage1(mContext)
                || Utility.enabledayAverage2(mContext)) {
            if (ChartActivity.movingArea == null
                    || ChartActivity.movingArea.getGraph() == null
                    || ChartActivity.movingArea.getGraph().getList().size() == 0) {
                getMovingdata();
            } else {
                drawSMA();
            }
        } else {
            if (smalist.size() > 0) {
                getGraphView(graphData);
            }
        }
    }

    void doRequest() {
        if (!isCompare) {
            url = UrlUtils.getVariable(url, "sc_id=", Id);
            url = UrlUtils.getVariable(url, "ex=", ex);
        }
        doBackgroundTask(RequestType.REQ_GET_GRAPH_DATA, url, progressBarr,
                false);

    }
}