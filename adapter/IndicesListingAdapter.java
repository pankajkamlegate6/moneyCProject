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
import com.moneycontrol.handheld.entity.market.StocksGainerLoserData;
import com.moneycontrol.handheld.util.Utility;

public class IndicesListingAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflator;
	private List<StocksGainerLoserData> listStockGainerLoser;
	// private boolean flag_show_change_percentage = false;
	public final int TYPE_STOCK = 0;
	public final int TYPE_INDUSTRIES = 1;
	public int type = 0;

	public IndicesListingAdapter(Context context,
			ArrayList<StocksGainerLoserData> list, int type) {

		this.mContext = context;
		this.listStockGainerLoser = list;
		mInflator = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.type = type;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listStockGainerLoser.size();
	}

	@Override
	public StocksGainerLoserData getItem(int position) {
		// TODO Auto-generated method stub
		return listStockGainerLoser.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.common_list_item, null);
			holder.ivFlag = (ImageView) convertView
					.findViewById(R.id.iv_home_item_flag);
			holder.tvName = (TextView) convertView
					.findViewById(R.id.tv_home_item_indices);
			holder.tvTime = (TextView) convertView
					.findViewById(R.id.tv_home_item_timestamp);
			holder.tvValue = (TextView) convertView
					.findViewById(R.id.tv_home_item_lastprice);
			holder.tvVolume = (TextView) convertView
					.findViewById(R.id.tv_home_item_volume);
			holder.tvChange = (TextView) convertView
					.findViewById(R.id.tv_home_item_change);
			holder.ivChange = (ImageView) convertView
					.findViewById(R.id.iv_home_item_change);

			holder.ivFlag.setVisibility(View.GONE);
			holder.tvTime.setVisibility(View.GONE);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		if (type == TYPE_STOCK) {
			holder.tvVolume.setVisibility(View.VISIBLE);
			holder.tvVolume.setText("Vol: "
					+ listStockGainerLoser.get(position).getVolume());
			if (listStockGainerLoser.get(position).getLastvalue() != null)
				holder.tvValue.setText(listStockGainerLoser.get(position)
						.getLastvalue());
			// TO-DO : Put volume data when comes from server. It is not visible
			// in Industries.
		} else if (type == TYPE_INDUSTRIES) {
			holder.tvVolume.setVisibility(View.GONE);
			if (listStockGainerLoser.get(position).getLastvalue() != null)
				holder.tvValue.setText(listStockGainerLoser.get(position)
						.getMktcappercentage() + "%");
		}

		if (listStockGainerLoser.get(position).getShortname() != null)
			holder.tvName.setText(listStockGainerLoser.get(position)
					.getShortname());

		if (listStockGainerLoser.get(position).getChange() != null) {
			Utility.getInstance().flipChangeTOPercentageData(holder.tvChange,
					listStockGainerLoser.get(position).getChange(),
					listStockGainerLoser.get(position).getPercentchange(),
					listStockGainerLoser.get(position).getDirection(),
					mContext, holder.ivChange);
			/*
			 * holder.tvChange.setOnClickListener(new OnClickListener() {
			 * 
			 * @Override public void onClick(View v) {
			 * 
			 * if (flag_show_change_percentage) flag_show_change_percentage =
			 * false; else flag_show_change_percentage = true;
			 * 
			 * notifyDataSetChanged(); } });
			 */

		}

		/*
		 * holder.tvChange.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { if(showValue) { if
		 * (listStockGainerLoser.get(position).getPercentchange() != null) {
		 * holder
		 * .tvChange.setText(listStockGainerLoser.get(position).getPercentchange
		 * ()); } showValue = false; } else{
		 * holder.tvChange.setText(listStockGainerLoser
		 * .get(position).getChange()); showValue = true; } } });
		 */

		return convertView;

	}

	/*
	 * private void setTextViewBackgroundColor(ViewHolder holder, int position)
	 * { if(listStockGainerLoser.get(position).getDirection().equals("1")) {
	 * holder
	 * .tvChange.setBackgroundDrawable(mContext.getResources().getDrawable(
	 * R.drawable.rectangle_round_corner_green)); } else {
	 * holder.tvChange.setBackgroundDrawable
	 * (mContext.getResources().getDrawable(
	 * R.drawable.rectangle_round_corner_red)); } }
	 */
	class ViewHolder {
		public TextView tvName, tvTime, tvValue, tvVolume, tvChange;
		public ImageView ivFlag;
		ImageView ivChange;
	}

}
