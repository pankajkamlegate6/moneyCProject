package com.moneycontrol.handheld.chart.data;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;

import com.moneycontrol.handheld.chart.utils.ColorTemplate;
import com.moneycontrol.handheld.chart.utils.Utils;

public class LineDataSet extends LineRadarDataSet<Entry> {

	/** arraylist representing all colors that are used for the circles */
	private ArrayList<Integer> mCircleColors = null;

	/** the radius of the circle-shaped value indicators */
	private float mCircleSize = 4f;

	/** sets the intensity of the cubic lines */
	private float mCubicIntensity = 0.2f;

	/** the path effect of this DataSet that makes dashed lines possible */
	private DashPathEffect mDashPathEffect = null;

	/** if true, drawing circles is enabled */
	private boolean mDrawCircles = true;

	/** if true, cubic lines are drawn instead of linear */
	private boolean mDrawCubic = false;
	private boolean addImages;

	public boolean isAddImages() {
		return addImages;
	}

	
	public LineDataSet(ArrayList<Entry> yVals, String label) {
		super(yVals, label);

		// mCircleSize = Utils.convertDpToPixel(4f);
		// mLineWidth = Utils.convertDpToPixel(1f);

		mCircleColors = new ArrayList<Integer>();

		// default colors
		// mColors.add(Color.rgb(192, 255, 140));
		// mColors.add(Color.rgb(255, 247, 140));
		mCircleColors.add(Color.rgb(140, 234, 255));
	}

	@Override
	public void addImages(boolean values) {
		// TODO Auto-generated method stub
		super.addImages(values);
		addImages = values;
	}

	@Override
	public DataSet<Entry> copy() {

		ArrayList<Entry> yVals = new ArrayList<Entry>();

		for (int i = 0; i < mYVals.size(); i++) {
			yVals.add(mYVals.get(i).copy());
		}

		LineDataSet copied = new LineDataSet(yVals, getLabel());
		copied.mColors = mColors;
		copied.mCircleSize = mCircleSize;
		copied.mCircleColors = mCircleColors;
		copied.mDashPathEffect = mDashPathEffect;
		copied.mDrawCircles = mDrawCircles;
		copied.mDrawCubic = mDrawCubic;
		copied.mHighLightColor = mHighLightColor;

		return copied;
	}

	/**
	 * Sets the intensity for cubic lines (if enabled). Max = 1f = very cubic,
	 * Min = 0.05f = low cubic effect, Default: 0.2f
	 * 
	 * @param intensity
	 */
	public void setCubicIntensity(float intensity) {

		if (intensity > 1f)
			intensity = 1f;
		if (intensity < 0.05f)
			intensity = 0.05f;

		mCubicIntensity = intensity;
	}

	/**
	 * Returns the intensity of the cubic lines (the effect intensity).
	 * 
	 * @return
	 */
	public float getCubicIntensity() {
		return mCubicIntensity;
	}

	/**
	 * sets the size (radius) of the circle shpaed value indicators, default
	 * size = 4f
	 * 
	 * @param size
	 */
	public void setCircleSize(float size) {
		mCircleSize = Utils.convertDpToPixel(size);
	}

	/**
	 * returns the circlesize
	 */
	public float getCircleSize() {
		return mCircleSize;
	}

	/**
	 * Enables the line to be drawn in dashed mode, e.g. like this "- - - - - -"
	 * 
	 * @param lineLength
	 *            the length of the line pieces
	 * @param spaceLength
	 *            the length of space inbetween the pieces
	 * @param phase
	 *            offset, in degrees (normally, use 0)
	 */
	public void enableDashedLine(float lineLength, float spaceLength,
			float phase) {
		mDashPathEffect = new DashPathEffect(new float[] { lineLength,
				spaceLength }, phase);
	}

	/**
	 * Disables the line to be drawn in dashed mode.
	 */
	public void disableDashedLine() {
		mDashPathEffect = null;
	}

	/**
	 * Returns true if the dashed-line effect is enabled, false if not.
	 * 
	 * @return
	 */
	public boolean isDashedLineEnabled() {
		return mDashPathEffect == null ? false : true;
	}

	/**
	 * returns the DashPathEffect that is set for this DataSet
	 * 
	 * @return
	 */
	public DashPathEffect getDashPathEffect() {
		return mDashPathEffect;
	}

	/**
	 * set this to true to enable the drawing of circle indicators for this
	 * DataSet, default true
	 * 
	 * @param enabled
	 */
	public void setDrawCircles(boolean enabled) {
		this.mDrawCircles = enabled;
	}

	/**
	 * returns true if drawing circles for this DataSet is enabled, false if not
	 * 
	 * @return
	 */
	public boolean isDrawCirclesEnabled() {
		return mDrawCircles;
	}

	/**
	 * If set to true, the linechart lines are drawn in cubic-style instead of
	 * linear. Default: false
	 * 
	 * @param enabled
	 */
	public void setDrawCubic(boolean enabled) {
		mDrawCubic = enabled;
	}

	/**
	 * returns true if drawing cubic lines is enabled, false if not.
	 * 
	 * @return
	 */
	public boolean isDrawCubicEnabled() {
		return mDrawCubic;
	}

	/** ALL CODE BELOW RELATED TO CIRCLE-COLORS */

	/**
	 * returns all colors specified for the circles
	 * 
	 * @return
	 */
	public ArrayList<Integer> getCircleColors() {
		return mCircleColors;
	}

	/**
	 * Returns the color at the given index of the DataSet's circle-color array.
	 * Performs a IndexOutOfBounds check by modulus.
	 * 
	 * @param index
	 * @return
	 */
	public int getCircleColor(int index) {
		return mCircleColors.get(index % mCircleColors.size());
	}

	/**
	 * Sets the colors that should be used for the circles of this DataSet.
	 * Colors are reused as soon as the number of Entries the DataSet represents
	 * is higher than the size of the colors array. Make sure that the colors
	 * are already prepared (by calling getResources().getColor(...)) before
	 * adding them to the DataSet.
	 * 
	 * @param colors
	 */
	public void setCircleColors(ArrayList<Integer> colors) {
		mCircleColors = colors;
	}

	/**
	 * Sets the colors that should be used for the circles of this DataSet.
	 * Colors are reused as soon as the number of Entries the DataSet represents
	 * is higher than the size of the colors array. Make sure that the colors
	 * are already prepared (by calling getResources().getColor(...)) before
	 * adding them to the DataSet.
	 * 
	 * @param colors
	 */
	public void setCircleColors(int[] colors) {
		this.mCircleColors = ColorTemplate.createColors(colors);
	}

	/**
	 * ets the colors that should be used for the circles of this DataSet.
	 * Colors are reused as soon as the number of Entries the DataSet represents
	 * is higher than the size of the colors array. You can use
	 * "new String[] { R.color.red, R.color.green, ... }" to provide colors for
	 * this method. Internally, the colors are resolved using
	 * getResources().getColor(...)
	 * 
	 * @param colors
	 */
	public void setCircleColors(int[] colors, Context c) {

		ArrayList<Integer> clrs = new ArrayList<Integer>();

		for (int color : colors) {
			clrs.add(c.getResources().getColor(color));
		}

		mCircleColors = clrs;
	}

	/**
	 * Sets the one and ONLY color that should be used for this DataSet.
	 * Internally, this recreates the colors array and adds the specified color.
	 * 
	 * @param color
	 */
	public void setCircleColor(int color) {
		resetCircleColors();
		mCircleColors.add(color);
	}

	/**
	 * resets the circle-colors array and creates a new one
	 */
	public void resetCircleColors() {
		mCircleColors = new ArrayList<Integer>();
	}
}
