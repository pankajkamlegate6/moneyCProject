package com.moneycontrol.handheld.api;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;

abstract public class LoadMoreListiners implements OnScrollListener {

	ListAdapter adapter;
	private boolean isLoading = false;;

	EndlessListener listners;

	public boolean isLoading() {
		return isLoading;
	}

	public void setLoading(boolean isLoading) {
		this.isLoading = isLoading;
	}

	public void setListners(EndlessListener listners) {
		this.listners = listners;
	}

	public LoadMoreListiners(ListAdapter adapter) {
		this.adapter = adapter;
	}

	public abstract void loadData();

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (adapter == null)
			return;

		if (adapter.getCount() == 0)
			return;

		int l = visibleItemCount + firstVisibleItem;
		if (l >= totalItemCount && !isLoading) {
			isLoading = true;
			loadData();
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}

	public static interface EndlessListener {
		public void loadData();
	}

}