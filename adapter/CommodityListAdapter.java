package com.moneycontrol.handheld.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.entity.market.CommoditiesCarouselData;
import com.moneycontrol.handheld.util.Utility;

public class CommodityListAdapter extends BaseAdapter {

	private Context mContext;
	private List<CommoditiesCarouselData> listCommodities;
	private LayoutInflater mInflator;

	public CommodityListAdapter(Context context,
			ArrayList<CommoditiesCarouselData> list) {

		this.mContext = context;
		this.listCommodities = list;

		mInflator = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		return listCommodities.size();
	}

	@Override
	public Object getItem(int arg0) {
		return listCommodities.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	static class ViewHolder {

		private TextView tvName;
		private TextView tvLastValue;
		private TextView tvChange;
		private ImageView ivChange;
		private TextView tvExpDate;
		private TextView tvVolume;
		// private TextView tvLastUpdated;

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View view = null;

		if (convertView == null) {

			final ViewHolder vHolder = new ViewHolder();

			view = mInflator.inflate(R.layout.common_list_item, null);

			vHolder.tvName = (TextView) view
					.findViewById(R.id.tv_home_item_indices);
			vHolder.tvLastValue = (TextView) view
					.findViewById(R.id.tv_home_item_lastprice);
			vHolder.tvChange = (TextView) view
					.findViewById(R.id.tv_home_item_change);
			vHolder.ivChange = (ImageView) view
					.findViewById(R.id.iv_home_item_change);
			vHolder.tvExpDate = (TextView) view
					.findViewById(R.id.tv_home_item_timestamp);
			vHolder.tvVolume = (TextView) view
					.findViewById(R.id.tv_home_item_volume);

			/*
			 * view.findViewById(R.id.tv_home_item_timestamp).setVisibility(
			 * View.GONE);
			 */
			/*
			 * view.findViewById(R.id.tv_home_item_volume)
			 * .setVisibility(View.GONE);
			 */
			view.findViewById(R.id.iv_home_item_flag).setVisibility(View.GONE);
			// vHolder.tvLastUpdated =
			// (TextView)view.findViewById(R.id.txt_market_item_lastupdated);
			// vHolder.tvLastUpdated.setVisibility(View.GONE);
			view.setTag(vHolder);

		} else {
			view = convertView;

		}

		ViewHolder holder = (ViewHolder) view.getTag();
		holder.tvName.setText(listCommodities.get(position).getName());
		holder.tvExpDate.setTextColor(mContext.getResources().getColor(
				R.color.white_gray));
		// holder.tvExpDate.setTextSize(new TypedValue().COMPLEX_UNIT_PX,
		// R.dimen.list_volume);
		holder.tvExpDate.setText(listCommodities.get(position).getLastupdate());
		holder.tvLastValue
				.setText(listCommodities.get(position).getLastprice());
		holder.tvVolume.setText("Vol:"
				+ listCommodities.get(position).getVolume());
		if (listCommodities.get(position).getChange() != null) {

			// holder.tvChange.setText(listCommodities.get(position).getChange()
			// + "\n" + listCommodities.get(position).getPercentchange());

			Utility.getInstance().flipChangeTOPercentageData(holder.tvChange,
					listCommodities.get(position).getChange(),
					listCommodities.get(position).getPercentchange(),
					listCommodities.get(position).getDirection(), mContext,
					holder.ivChange);

		}

		return view;
	}
}
