
package com.moneycontrol.handheld.chart.data;

import java.util.ArrayList;

import android.graphics.Color;

/**
 * Baseclass of all DataSets for Bar-, Line-, Scatter- and CandleStickChart.
 * 
 * @author Philipp Jahoda
 */
public abstract class BarLineScatterCandleRadarDataSet<T extends Entry> extends DataSet<T> {

    /** default highlight color */
    protected int mHighLightColor = Color.rgb(255, 187, 115);

    public BarLineScatterCandleRadarDataSet(ArrayList<T> yVals, String label) {
        super(yVals, label);
    }

    /**
     * Sets the color that is used for drawing the highlight indicators. Dont
     * forget to resolve the color using getResources().getColor(...) or
     * Color.rgb(...).
     * 
     * @param color
     */
    public void setHighLightColor(int color) {
        mHighLightColor = color;
    }

    /**
     * Returns the color that is used for drawing the highlight indicators.
     * 
     * @return
     */
    public int getHighLightColor() {
        return mHighLightColor;
    }
}
