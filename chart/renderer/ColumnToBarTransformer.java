package com.moneycontrol.handheld.chart.renderer;

import java.util.ArrayList;

import com.moneycontrol.handheld.chart.data.BarData;
import com.moneycontrol.handheld.chart.data.Entry;
import com.moneycontrol.handheld.chart.interfaces.ChartInterface;

public class ColumnToBarTransformer extends Transformer
{
	/**
	 * Prepares the matrix that transforms values to pixels.
	 *
	 * @param chart
	 */
	@Override
	public void prepareMatrixValuePx(ChartInterface chart)
	{
		float scaleX = (chart.getWidth() - chart.getOffsetRight() - chart.getOffsetLeft()) / chart.getDeltaY();
		float scaleY = (chart.getHeight() - chart.getOffsetTop() - chart.getOffsetBottom()) / chart.getDeltaX();

		// setup all matrices
		mMatrixValueToPx.reset();
		mMatrixValueToPx.postTranslate(0, -chart.getYChartMin());
		mMatrixValueToPx.postScale(scaleX, -scaleY);
	}


	/**
	 * Transforms an arraylist of Entry into a float array containing the x and
	 * y values transformed with all matrices for the BARCHART.
	 *
	 * @param entries
	 * @param dataSet the dataset index
	 * @return
	 */
	public float[] generateTransformedValuesBarChart(ArrayList<? extends Entry> entries,
													 int dataSet, BarData bd, float phaseY) {

		float[] valuePoints = new float[entries.size() * 2];

		int setCount = bd.getDataSetCount();
		float space = bd.getGroupSpace();

		for (int j = 0; j < valuePoints.length; j += 2) {

			int index = j/2;
			Entry e = entries.get(index);

			//TODO: Need to find a better value than 1, should be dependant on yMax, I think
			float x = e.getVal() + 1;
			// calculate the y-position, depending on datasetcount
			float y = e.getXIndex() + (index * (setCount - 1)) + dataSet + 0.5f + space * index
					+ space / 2f;

			valuePoints[j] = x;
			valuePoints[j + 1] = y;
		}

		pointValuesToPixel(valuePoints);

		return valuePoints;
	}

}
