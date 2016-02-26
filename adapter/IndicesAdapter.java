package com.moneycontrol.handheld.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.constant.AppConstants;
import com.moneycontrol.handheld.entity.home.HomeMarketMainIndices;
import com.moneycontrol.handheld.util.ImageDownloader;
import com.moneycontrol.handheld.util.Utility;

public class IndicesAdapter extends BaseAdapter {

	private ArrayList<HomeMarketMainIndices> homeIndicesArray = null;
	// private boolean flag_show_change_percentage = false;
	private Activity context = null;
	private int type = 0;

	public IndicesAdapter(ArrayList<HomeMarketMainIndices> homeIndicesArray,
			Activity con, int intype) {

		context = con;
		this.type = intype;
		this.homeIndicesArray = homeIndicesArray;
	}

	@Override
	public int getCount() {
		return homeIndicesArray.size();
	}

	@Override
	public Object getItem(int arg0) {
		return homeIndicesArray.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.common_list_item, null);
			holder.heading = (TextView) convertView
					.findViewById(R.id.tv_home_item_indices);
			holder.lastPrice = (TextView) convertView
					.findViewById(R.id.tv_home_item_lastprice);
			holder.lastUpdated = (TextView) convertView
					.findViewById(R.id.tv_home_item_timestamp);
			holder.flag = (ImageView) convertView
					.findViewById(R.id.iv_home_item_flag);
			holder.change = (TextView) convertView
					.findViewById(R.id.tv_home_item_change);
			holder.ivChange = (ImageView) convertView
					.findViewById(R.id.iv_home_item_change);

			convertView.findViewById(R.id.tv_home_item_volume).setVisibility(
					View.GONE);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		if (homeIndicesArray.get(position).getStkexchg() != null) {
			holder.heading.setText(Html.fromHtml(homeIndicesArray.get(position)
					.getStkexchg()));
		}

		if (homeIndicesArray.get(position).getChange() != null) {

			// holder.change.setText(homeIndicesArray.get(position).getChange()+"\n"+homeIndicesArray.get(position).getPercentchange());

			Utility.getInstance().flipChangeTOPercentageData(holder.change,
					homeIndicesArray.get(position).getChange(),
					homeIndicesArray.get(position).getPercentchange(),
					homeIndicesArray.get(position).getDirection(), context,
					holder.ivChange);

		}

		/*
		 * if (homeIndicesArray.get(position).getInd_id().equals(""))
		 * convertView.setBackgroundColor(Color.BLACK);
		 */

		if (homeIndicesArray.get(position).getLastupdated() != null)
			holder.lastUpdated.setText(homeIndicesArray.get(position)
					.getLastupdated());

		if (homeIndicesArray.get(position).getFlag() != null
				|| homeIndicesArray.get(position).getFlag() != " ") {
			if (type == AppConstants.INDICE_INDIAN) {
				holder.flag.setVisibility(View.GONE);

			} else {

				new ImageDownloader().download(homeIndicesArray.get(position)
						.getFlag(), holder.flag);
			}
		} else {

			holder.flag.setVisibility(View.GONE);
		}

		if (homeIndicesArray.get(position).getLastprice() != null)
			holder.lastPrice.setText(homeIndicesArray.get(position)
					.getLastprice());

		return convertView;
	}

	class ViewHolder {
		public TextView heading, lastPrice, lastUpdated;
		public TextView change;
		public ImageView flag, ivChange;
	}

}
