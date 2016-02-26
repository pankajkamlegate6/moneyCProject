package com.moneycontrol.handheld.chart.listener;

import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.moneycontrol.handheld.chart.PieRadarChartBase;
import com.moneycontrol.handheld.chart.interfaces.OnChartGestureListener;
import com.moneycontrol.handheld.chart.utils.Highlight;
import com.moneycontrol.handheld.chart.utils.Utils;

/**
 * Touchlistener for the PieChart.
 * 
 * @author Philipp Jahoda
 */
public class PieRadarChartTouchListener extends SimpleOnGestureListener
		implements OnTouchListener {

	private static final int NONE = 0;
	private static final int ROTATE = 1;

	private PointF mTouchStartPoint = new PointF();

	private PieRadarChartBase mChart;

	private int mTouchMode = NONE;

	private GestureDetector mGestureDetector;

	public PieRadarChartTouchListener(PieRadarChartBase ctx) {
		this.mChart = ctx;

		mGestureDetector = new GestureDetector(ctx.getContext(), this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent e) {

		if (mGestureDetector.onTouchEvent(e))
			return true;

		// if rotation by touch is enabled
		if (mChart.isRotationEnabled()) {

			float x = e.getX();
			float y = e.getY();

			switch (e.getAction()) {

			case MotionEvent.ACTION_DOWN:
				mChart.setStartAngle(x, y);
				mTouchStartPoint.x = x;
				mTouchStartPoint.y = y;
				break;
			case MotionEvent.ACTION_MOVE:

				if (mTouchMode == NONE
						&& distance(x, mTouchStartPoint.x, y,
								mTouchStartPoint.y) > Utils
								.convertDpToPixel(8f)) {
					mTouchMode = ROTATE;
					mChart.disableScroll();
				} else if (mTouchMode == ROTATE) {
					mChart.updateRotation(x, y);
					mChart.invalidate();
				}

				break;
			case MotionEvent.ACTION_UP:
				mChart.enableScroll();
				mTouchMode = NONE;
				break;
			}
		}

		return true;
	}

	@Override
	public void onLongPress(MotionEvent me) {
		OnChartGestureListener l = mChart.getOnChartGestureListener();

		if (l != null) {
			l.onChartLongPressed(me);
		}
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		return true;
	}

	/** reference to the last highlighted object */
	private Highlight mLastHighlight = null;

	@Override
	public boolean onSingleTapUp(MotionEvent e) {

		OnChartGestureListener l = mChart.getOnChartGestureListener();

		String xValue = null, yValue = null;
		if (l != null) {

			l.onChartSingleTapped(e);
		}

		float distance = mChart.distanceToCenter(e.getX(), e.getY());

		// check if a slice was touched
		if (distance > mChart.getRadius()) {

			// if no slice was touched, highlight nothing
			mChart.highlightValues(null);
			mLastHighlight = null;

		} else {

			float angle = mChart.getAngleForPoint(e.getX(), e.getY());
			int index = mChart.getIndexForAngle(angle);

			// check if the index could be found
			if (index < 0) {

				mChart.highlightValues(null);
				mLastHighlight = null;

			}
		}

		return true;
	}

	@Override
	public boolean onDoubleTap(MotionEvent e) {
		OnChartGestureListener l = mChart.getOnChartGestureListener();

		if (l != null) {
			l.onChartDoubleTapped(e);
		}
		return super.onDoubleTap(e);
	}

	/**
	 * returns the distance between two points
	 * 
	 * @param eventX
	 * @param startX
	 * @param eventY
	 * @param startY
	 * @return
	 */
	private static float distance(float eventX, float startX, float eventY,
			float startY) {
		float dx = eventX - startX;
		float dy = eventY - startY;
		return (float) Math.sqrt(dx * dx + dy * dy);
	}
}
