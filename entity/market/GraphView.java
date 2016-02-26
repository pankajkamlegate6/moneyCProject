package com.moneycontrol.handheld.entity.market;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.Shader.TileMode;
import android.view.View;

/**
 * ------------------GraphView---------------------------cg
 *
 * @author Divum Corporate Service pvt ltd.
 * @version 1.0
 */
public class GraphView extends View {

    Context mContext;
    String range;

    private Paint paint;
    private float[] values;
    private String[] horlabels;
    // private String[] verlabels;
    // private String title;
    // private boolean type;
    int xLabelCount;// ,arraySize=0;
    float maxY, minY, maxX, minX, graphHeight, graphWidth, yMargin, xMargin;
    float yOnChartArray[];
    float xOnChartArray[];
    int actualHeight = 0;
    float yLabelCount = 5;
    float yAxisLabelCoOrds[];
    float yAxisLabelValues[];
    String xLabelDisplayValues[];
    float xLabelCoOrds[];

    int validXCoords[];
    String direction = "1";

    public GraphView(Context context, float[] values, String title,
                     String[] horlabels, String[] verlabels, String range,
                     int ActualHeight, String direction) {
        super(context);
        mContext = context;
        this.actualHeight = ActualHeight;
        this.direction = direction;
        this.range = range;
        if (values == null)
            values = new float[0];
        else {
            this.values = values;
        }

        if (horlabels == null)
            this.horlabels = new String[0];
        else
            this.horlabels = horlabels;
        paint = new Paint();
        // setData(true);

    }

    // private void setData(boolean b) {
    //
    // if (horlabels != null) {
    // ArrayList<String> xVals = new ArrayList<String>();
    // for (int i = 0; i < horlabels.length; i++) {
    // String timesFormatted = ChartActivity.getFormattedDate(
    // "dd MMM yyy hh:mm", horlabels[i]);
    // xVals.add(timesFormatted);
    // }
    //
    // ArrayList<Entry> yVals = new ArrayList<Entry>();
    // for (int i = 0; i < values.length; i++) {
    // double val = Double.valueOf(values[i]); // 0.1) / 10);
    // yVals.add(new Entry((float) val, i));
    //
    // }
    //
    // // create a dataset and give it a type
    // LineDataSet lineSet = new LineDataSet(yVals, "DataSet 1");
    // // set1.setFillAlpha(110);
    // // set1.setFillColor(Color.RED);
    //
    // // set the line to be drawn like this "- - - - - -"
    // lineSet.enableDashedLine(10f, 5f, 0f);
    // int color = R.color.red;
    // if (direction != null)
    // if (direction.contains("-1")) {
    // color = R.color.red;
    // } else {
    // color = R.color.color_green;
    // }
    // lineSet.setColor(color);
    // if (b) {
    // lineSet.setLineWidth(5f);
    // } else {
    // lineSet.setLineWidth(3f);
    // }
    // // set1.setCircleSize(4f);
    // lineSet.setFillAlpha(100);
    // lineSet.disableDashedLine();
    // lineSet.setDrawFilled(true);
    // lineSet.setFillColor(mContext.getResources().getColor(
    // R.color.color_area));
    //
    // lineSet.addImages(false);
    //
    // // set1.setShader(new LinearGradient(0, 0, 0, mChart.getHeight(),
    // // Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));
    //
    // // add the datasets
    //
    // // create a data object with the datasets
    // LineData data = new LineData(xVals, lineSet);
    //
    // setData(data);
    // invalidate();
    // animateX(1000);
    // // mChart.setPinchZoom(enabled)
    // // set data
    //
    // }
    //
    // }

    protected void initializeVariables() {

        xMargin = 40;

        graphWidth = getWidth() - xMargin;
        if (range == "") {
            yMargin = 0;
            graphHeight = getHeight() - yMargin - 15;
        } else {
            yMargin = 10;
            graphHeight = getHeight() - yMargin - 10;
        }
    }

    protected void calcXYValues() {

        initializeVariables();
        maxY = getMax();
        minY = getMin();
        maxX = values.length;
        minX = 0;
        xOnChartArray = new float[values.length];
        yOnChartArray = new float[values.length];
        if (minX == maxX) {
            minX = minX - 0.5f;
        }
        if (maxY == minY) {
            minY = minY - 0.5f;
        }
        float diff = maxY - minY;
        minY = minY - (diff * 0.5f);
        diff = maxY - minY;
        for (int i = 0; i < values.length; i++) {
            if (diff == 0) {
                xOnChartArray[i] = xMargin + ((graphWidth) * i);

                yOnChartArray[i] = yMargin
                        + (graphHeight - ((graphHeight) * (values[i] - minY)));
            } else {
                xOnChartArray[i] = xMargin + ((graphWidth / (maxX - minX)) * i);

                yOnChartArray[i] = yMargin
                        + (graphHeight - ((graphHeight / diff) * (values[i] - minY)));
            }
        }

        addYAxisLabels();
        addXlabel();
        // populateXYDictionary();
    }

    protected void onDraw(Canvas canvas) {
        // System.out.println("-----------------OnDraw Called-----------");

        calcXYValues();

        super.onDraw(canvas);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        Path pth = new Path();
        Paint pGreen = new Paint(Paint.ANTI_ALIAS_FLAG
                | Paint.FILTER_BITMAP_FLAG);
        Path pthGreen = new Path();
        float lastyAxisLabelCoords = 0;
        // Drawing y label and grids
        for (int j = 0; j < yLabelCount; j++) {
            paint.setColor(Color.DKGRAY);
            canvas.drawLine(xMargin, yAxisLabelCoOrds[j], getWidth(),
                    yAxisLabelCoOrds[j], paint);
            paint.setColor(Color.WHITE);

            // System.out.println("YAxisLabel " + yAxisLabelValues[j] + " " +
            // (Math.round())/10000);
            float yLabel = yAxisLabelValues[j] * 100;
            float rounded = Math.round(yLabel);
            int finalValue = (int) rounded / 100;

            if (j == 0) {

                if (lastyAxisLabelCoords != yAxisLabelCoOrds[j]) {
                    lastyAxisLabelCoords = yAxisLabelCoOrds[j];
                    canvas.drawText(finalValue + "", xMargin - 40,
                            lastyAxisLabelCoords, paint);
                }
            } else {

                if (lastyAxisLabelCoords != yAxisLabelCoOrds[j]) {
                    lastyAxisLabelCoords = yAxisLabelCoOrds[j];
                    canvas.drawText(finalValue + "", xMargin - 40,
                            lastyAxisLabelCoords, paint);
                }
            }
            lastyAxisLabelCoords = yAxisLabelCoOrds[j];

            paint.setTextAlign(Align.LEFT);

        }

        // Drawing x label and grids
        for (int j = 0; j < xLabelCount; j++) {

            paint.setColor(Color.DKGRAY);
            canvas.drawLine(xLabelCoOrds[j], yMargin + graphHeight,
                    xLabelCoOrds[j], yMargin, paint);
            paint.setColor(Color.WHITE);

            // canvas.drawText(xLabelDisplayValues[j], xLabelCoOrds[j] - 20,
            // yMargin + graphHeight + 20, paint);
            paint.setTextAlign(Align.LEFT);

        }

        paint.setColor(Color.BLACK);

        // Drawing graph
        p.setColor(0xff800000);
        p.setShader(new LinearGradient(0, 0, 0, getHeight(), Color.rgb(31, 31,
                31), Color.rgb(31, 31, 31), TileMode.MIRROR));
        pGreen.setColor(0xff800000);
        // pGreen.setShader(new LinearGradient(0, 0, 0, getHeight(),
        // Color.rgb(94, 205, 5), Color.rgb(94, 205, 5), TileMode.MIRROR));
        if (direction != null)
            if (direction.contains("-1")) {
                pGreen.setShader(new LinearGradient(0, 0, 0, getHeight(), Color
                        .rgb(255, 0, 51), Color.rgb(255, 0, 51),
                        TileMode.MIRROR)); // Red
            } else {
                pGreen.setShader(new LinearGradient(0, 0, 0, getHeight(), Color
                        .rgb(94, 205, 5), Color.rgb(94, 205, 5),
                        TileMode.MIRROR));
            }

        for (int i = 0; i < values.length - 1; i++) {

            pth.lineTo(xOnChartArray[i], yOnChartArray[i] + 5);
            pthGreen.lineTo(xOnChartArray[i], yOnChartArray[i]);
        }

        pth.lineTo(graphWidth + xMargin, graphHeight + yMargin);
        pth.lineTo(xMargin, graphHeight + yMargin);
        pth.lineTo(xMargin, yOnChartArray[0] + 5);

        pthGreen.lineTo(graphWidth + xMargin, graphHeight + yMargin);
        pthGreen.lineTo(xMargin, graphHeight + yMargin);
        pthGreen.lineTo(xMargin, yOnChartArray[0]);

        canvas.drawPath(pthGreen, pGreen);
        canvas.drawPath(pth, p);

    }

    private float getMax() {
        float largest = Integer.MIN_VALUE;
        for (int i = 0; i < values.length; i++)
            if (values[i] > largest)
                largest = values[i];
        return largest;
    }

    private float getMin() {
        float smallest = Integer.MAX_VALUE;
        for (int i = 0; i < values.length; i++)
            if (values[i] < smallest)
                smallest = values[i];
        return smallest;
    }

    protected void addYAxisLabels() {

        float yDiffValues[] = new float[10];
        yAxisLabelValues = new float[10];
        yAxisLabelCoOrds = new float[10];

        yLabelCount = 5;

        float ratio = 1 / (yLabelCount - 1);
        float maxDiff = maxY - minY;

        for (int j = 0; j < yLabelCount; j++) {
            yDiffValues[j] = 9999;
            if (maxDiff == 0) {
                yAxisLabelValues[j] = minY + j * ratio + 3;
            } else {
                yAxisLabelValues[j] = minY + j * ratio * maxDiff + 3;
            }
        }

        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < yLabelCount; j++) {
                if (yDiffValues[j] >= Math.abs(yAxisLabelValues[j] - values[i])) {
                    yDiffValues[j] = Math.abs(yAxisLabelValues[j] - values[i]);
                    yAxisLabelCoOrds[j] = yOnChartArray[i];
                }
            }
        }
    }

    void addXlabel() {
        if (range == "1d") {
            processXLabel1d();
        } else if (range == "1m") {
            processXLabel1m();
        } else if (range == "6m") {
            processXLabel6m();
        } else if (range == "1yr") {
            processXLabel1yr();
        } else if (range == "5yr") {
            processXLabel5yr();
        }
    }

    void processXLabel1d() {
        xLabelCount = 0;
        xLabelCoOrds = new float[values.length];
        xLabelDisplayValues = new String[values.length];

        String printedXDict[] = new String[values.length];

        int lastXLabel = 0;
        for (int i1 = 0; i1 < values.length; i1++) {
            printedXDict[i1] = "0";
        }

        int i = 0;

        float xValue = xOnChartArray[i];

        String valueString = horlabels[i];

        String subString = valueString.substring(0, 5);

        String displayString = subString;

        // var firstInt = subString.substr(0,2);
        printedXDict[i] = subString.substring(0, 2);

        saveXLabel(displayString, xValue);

        lastXLabel++;

        for (int i1 = 1; i1 < values.length - 1; i1++) {

            // System.out.println(" value " + i1 );

            try {
                xValue = xOnChartArray[i1];
                valueString = horlabels[i1];
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            String lastString = printedXDict[lastXLabel - 1];
            // System.out.println("valueString" + valueString );

            subString = valueString.substring(0, 5);
            // System.out.println("subString" + subString );

            String subInt = valueString.substring(0, 2) + "";
            // System.out.println("subInt" + subInt );

            if (!(subInt.equalsIgnoreCase(lastString))) {

                // System.out.println("subInt"+subInt+"lastString"+lastString);

                if (lastXLabel < values.length) {
                    if (printedXDict[lastXLabel] == "0") {
                        // if(lastXLabel%2==0) {
                        displayString = subString;
                        saveXLabel(displayString, xValue);
                        // }
                        printedXDict[lastXLabel] = subInt;
                        lastXLabel++;
                    }
                }

            }

        }

    }

    void processXLabel1m() {
        xLabelCount = 0;
        xLabelCoOrds = new float[30];
        xLabelDisplayValues = new String[30];

        String printedXDict[] = new String[30];

        int lastXLabel = 0;
        for (int i1 = 0; i1 < 30; i1++) {
            printedXDict[i1] = "0";
        }

        int i = 0;

        float xValue = xOnChartArray[i];

        String valueString = horlabels[i];

        String subString = valueString.substring(0, 2);

        String displayString = subString;

        // var firstInt = subString.substr(0,2);
        printedXDict[i] = subString;

        saveXLabel(displayString, xValue);

        lastXLabel++;

        for (int i1 = 1; i1 < values.length - 1; i1++) {

            // System.out.println(" value " + i1 );

            try {
                xValue = xOnChartArray[i1];
                valueString = horlabels[i1];
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            String lastString = printedXDict[lastXLabel - 1];
            subString = valueString.substring(0, 2);
            String subInt = valueString.substring(0, 2) + "";

            if (!(subInt.equalsIgnoreCase(lastString))) {

                if (lastXLabel <= printedXDict.length - 1) {

                    if (printedXDict[lastXLabel] == "0") {
                        if (lastXLabel % 2 == 0) {
                            displayString = subString;
                            saveXLabel(displayString, xValue);
                        }
                        printedXDict[lastXLabel] = subInt;
                        lastXLabel++;
                    }
                }

            }

        }

    }

    void processXLabel6m() {
        xLabelCount = 0;
        xLabelCoOrds = new float[30];
        xLabelDisplayValues = new String[30];

        String printedXDict[] = new String[30];

        int lastXLabel = 0;
        for (int i1 = 0; i1 < 30; i1++) {
            printedXDict[i1] = "0";
        }

        int i = 0;

        float xValue = xOnChartArray[i];

        String valueString = horlabels[i];

        String subString = valueString.substring(0, 6);

        int firstInt = Integer.parseInt(subString.substring(0, 2));

        String displayString = subString;
        printedXDict[i] = subString;
        saveXLabel(displayString, xValue);
        lastXLabel++;

        String lastString;

        for (int i1 = 1; i1 < values.length - 1; i1++) {

            try {
                xValue = xOnChartArray[i1];
                valueString = horlabels[i1];
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            lastString = printedXDict[lastXLabel - 1];
            subString = valueString.substring(0, 6);

            int subInt = Integer.parseInt(subString.substring(0, 2));

            String lastMonth = lastString.substring(3, lastString.length());
            String thisMonth = subString.substring(3, subString.length());
            // System.out.println("subInt"+thisMonth+"lastString"+lastMonth);

            if (!(lastMonth.equalsIgnoreCase(thisMonth))
                    && (Math.abs(subInt - firstInt) <= 1)) {
                if (lastXLabel <= printedXDict.length - 1) {
                    if (printedXDict[lastXLabel] == "0") {
                        // if(lastXLabel%2==0) {
                        displayString = subString;
                        saveXLabel(displayString, xValue);
                        // }
                        printedXDict[lastXLabel] = subString;
                        lastXLabel++;
                    }
                }

            }

        }

    }

    void processXLabel1yr() {
        xLabelCount = 0;
        xLabelCoOrds = new float[30];
        xLabelDisplayValues = new String[30];

        String printedXDict[] = new String[30];

        int lastXLabel = 0;
        for (int i1 = 0; i1 < 30; i1++) {
            printedXDict[i1] = "0";
        }

        int i = 0;

        float xValue = xOnChartArray[i];

        String valueString = horlabels[i];

        String subString = valueString.substring(0, 6);

        int firstInt = Integer.parseInt(subString.substring(0, 2));

        String displayString = subString;
        printedXDict[i] = subString;
        saveXLabel(displayString, xValue);
        lastXLabel++;

        String lastString;

        for (int i1 = 1; i1 < values.length - 1; i1++) {

            // xValue = xOnChartArray[i1];
            // valueString = horlabels[i1];

            try {
                xValue = xOnChartArray[i1];
                valueString = horlabels[i1];
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            lastString = printedXDict[lastXLabel - 1];
            subString = valueString.substring(0, 6);

            int subInt = Integer.parseInt(subString.substring(0, 2));

            String lastMonth = lastString.substring(3, lastString.length());
            String thisMonth = subString.substring(3, subString.length());
            // System.out.println("subInt"+thisMonth+"lastString"+lastMonth);

            if (!(lastMonth.equalsIgnoreCase(thisMonth))
                    && (Math.abs(subInt - firstInt) <= 1)) {
                if (lastXLabel <= printedXDict.length - 1) {
                    if (printedXDict[lastXLabel] == "0") {
                        if (lastXLabel % 2 == 0) {
                            displayString = subString;
                            saveXLabel(displayString, xValue);
                        }
                        printedXDict[lastXLabel] = subString;
                        lastXLabel++;
                    }
                }

            }

        }

    }

    void processXLabel5yr() {

        xLabelCount = 0;
        xLabelCoOrds = new float[30];
        xLabelDisplayValues = new String[30];

        String printedXDict[] = new String[70];

        int lastXLabel = 0;
        for (int i1 = 0; i1 < 70; i1++) {
            printedXDict[i1] = "0";
        }

        int i = 0;

        float xValue = xOnChartArray[i];

        String valueString = horlabels[i];

        String subString = valueString;

        // int firstInt = Integer.parseInt(subString.substring(0,2));

        String displayString = subString.substring(2, 6) + " "
                + subString.substring(9, subString.length());
        printedXDict[i] = subString;
        saveXLabel(displayString, xValue);
        lastXLabel++;

        String lastString;

        for (int i1 = 1; i1 < values.length - 1; i1++) {

            try {
                xValue = xOnChartArray[i1];
                valueString = horlabels[i1];
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            lastString = printedXDict[lastXLabel - 1];
            subString = valueString;

            String lastMonth = lastString.substring(3, lastString.length());
            String thisMonth = subString.substring(3, subString.length());
            // System.out.println("subInt"+thisMonth+"lastString"+lastMonth);

            if (!(lastMonth.equalsIgnoreCase(thisMonth))) {
                if (lastXLabel <= printedXDict.length - 1) {
                    if (printedXDict[lastXLabel] == "0") {
                        if (lastXLabel % 12 == 0) {
                            displayString = subString.substring(2, 6) + " "
                                    + subString.substring(9, subString.length());
                            saveXLabel(displayString, xValue);
                        }
                        printedXDict[lastXLabel] = subString;
                        lastXLabel++;
                    }
                }
            }

        }
    }

    void saveXLabel(String displayString, float xLoc) {

        // System.out.println(">>>>>display string "+displayString+" Xlocation "+xLoc);
        xLabelCoOrds[xLabelCount] = Math.round(xLoc);
        xLabelDisplayValues[xLabelCount] = displayString;

        xLabelCount++;
    }

    private void populateXYDictionary() {

        int lastXValue = 0;
        float xValue = 0;

        validXCoords = new int[10000];

        for (int i1 = 0; i1 < values.length; i1++) {
            xValue = xOnChartArray[i1];
            for (int j1 = lastXValue; j1 < xValue; j1++) {
                lastXValue++;
                validXCoords[lastXValue] = 10000;
            }
            validXCoords[lastXValue] = i1;
        }

        // isItValidXPoint();

    }

    public boolean onTouchListener(android.view.MotionEvent event) {

        // System.out.println("Came here" + event.getX());
        drawInteractionLine(event.getX());
        return false;

    }

    ;

    private void drawInteractionLine(float x) {

        int x1 = (int) (x - xMargin);

        if (validXCoords[x1] != 10000) {
            @SuppressWarnings("unused")
            int index = validXCoords[x1];
        }
    }

    @SuppressWarnings("unused")
    private void isItValidXPoint() {

        for (int i1 = 0; i1 < xOnChartArray[values.length - 1]; i1++) {

            if (validXCoords[i1] != 10000) {
                int index = validXCoords[i1];

            }
        }

    }

}
