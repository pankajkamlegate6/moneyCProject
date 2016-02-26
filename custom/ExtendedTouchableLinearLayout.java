package com.moneycontrol.handheld.custom;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.LinearLayout;

public class ExtendedTouchableLinearLayout extends LinearLayout {

	public ExtendedTouchableLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public ExtendedTouchableLinearLayout(Context context) {
		super(context);
		init();
		// TODO Auto-generated constructor stub
	}

	private void init() {
		post(new Runnable() {
			public void run() {
				// Post in the parent's message queue to make sure the parent
				// lays out its children before we call getHitRect()
				Rect delegateArea = new Rect();
				LinearLayout delegate = ExtendedTouchableLinearLayout.this;
				delegate.getHitRect(delegateArea);
				delegateArea.top -= 100;
				delegateArea.bottom += 100;
				delegateArea.left -= 100;
				delegateArea.right += 100;
				TouchDelegate expandedArea = new TouchDelegate(delegateArea,
						delegate);
				// give the delegate to an ancestor of the view we're
				// delegating the
				// area to
				if (View.class.isInstance(delegate.getParent())) {
					((View) delegate.getParent())
							.setTouchDelegate(expandedArea);
				}
			};
		});
	}
}
