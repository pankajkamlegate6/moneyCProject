package com.moneycontrol.handheld.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.entity.watchlist.CommoditesWatchListBean;
import com.moneycontrol.handheld.entity.watchlist.WatchListBean;
import com.moneycontrol.handheld.util.UrlUtils;

public class FutureWatchListAdapter extends BaseAdapter {

	private ArrayList<CommoditesWatchListBean> list;
	private Context mContext;
	private LayoutInflater mInflator;

	public FutureWatchListAdapter(Context mContext,
			ArrayList<CommoditesWatchListBean> itemList) {
		// TODO Auto-generated constructor stub
		this.list = itemList;
		this.mContext = mContext;
		mInflator = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public ArrayList<CommoditesWatchListBean> getList() {
		return list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public CommoditesWatchListBean getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder vHolder;
		if (view == null) {
			vHolder = new ViewHolder();
			view = mInflator.inflate(R.layout.watchlist_item_layout, null);

			vHolder.name = (TextView) view.findViewById(R.id.tvWlItemName);
			vHolder.exchange = (TextView) view
					.findViewById(R.id.tvWlItemDateTime);
			vHolder.price = (TextView) view.findViewById(R.id.tvWlItemPrice);
			vHolder.ivChange = (ImageView) view
					.findViewById(R.id.iv_day_gain_change);
			vHolder.volume = (TextView) view.findViewById(R.id.tvWlItemVolume);
			vHolder.bid = (TextView) view.findViewById(R.id.tv_wl_item_bid);
			vHolder.offer = (TextView) view.findViewById(R.id.tv_wl_item_offer);
			vHolder.tv_day_gain_change_value = (TextView) view
					.findViewById(R.id.tv_day_gain_change_value);
			vHolder.ll_bid_offer = (LinearLayout) view
					.findViewById(R.id.ll_bid_offer);
			vHolder.rl_change = (RelativeLayout) view
					.findViewById(R.id.rl_change);
			vHolder.crossImage = (ImageView) view
					.findViewById(R.id.iv_wl_item_delete);
			view.setTag(vHolder);

		} else {
			vHolder = (ViewHolder) view.getTag();

		}
		if (list.get(position).getCurrent_show() == WatchListBean.SHOW_CHANGES) {
			vHolder.ll_bid_offer.setVisibility(View.GONE);
			vHolder.rl_change.setVisibility(View.VISIBLE);
			vHolder.crossImage.setVisibility(View.GONE);
		} else if (list.get(position).getCurrent_show() == WatchListBean.SHOW_BID) {
			vHolder.ll_bid_offer.setVisibility(View.VISIBLE);
			vHolder.rl_change.setVisibility(View.GONE);
			vHolder.crossImage.setVisibility(View.GONE);
		} else {
			vHolder.ll_bid_offer.setVisibility(View.GONE);
			vHolder.rl_change.setVisibility(View.GONE);
			vHolder.crossImage.setVisibility(View.VISIBLE);
		}
		if (list.get(position).getDirection().equalsIgnoreCase("1")) {
			vHolder.ivChange.setBackgroundResource(R.drawable.green_base);
			vHolder.offer.setTextColor(mContext.getResources().getColor(
					R.color.green));
		} else {
			vHolder.ivChange.setBackgroundResource(R.drawable.red_base);
			vHolder.offer.setTextColor(mContext.getResources().getColor(
					R.color.red));
		}
		vHolder.crossImage
				.setTag(UrlUtils.deleteWatchListFutures(mContext,
						list.get(position).getSymbol(), list.get(position)
								.getExpirydate(), list.get(position)
								.getExchange(), list.get(position)
								.getInstrument(), list.get(position).getId()));

		vHolder.tv_day_gain_change_value.setText(list.get(position).getChange()
				+ "\n" + list.get(position).getPercentchange() + "%");
		if (list.get(position).getShow_symbol() == CommoditesWatchListBean.SHOW_SYMBOL) {
			vHolder.name.setText(list.get(position).getSymbol() + "\n"
					+ list.get(position).getExpirydate());

		} else {
			vHolder.name.setText(list.get(position).getShortName() + "\n"
					+ list.get(position).getExpirydate());
		}
		vHolder.exchange.setText(list.get(position).getExchange() + ": "
				+ list.get(position).getLastupdate());
		vHolder.price.setText(list.get(position).getLastprice());
		vHolder.volume.setText("Vol: " + list.get(position).getVolume());
		vHolder.volume.setVisibility(View.GONE);
		vHolder.bid.setText(list.get(position).getOpen_int());
		vHolder.offer.setText(list.get(position).getOpen_int_chg_perc() + "%");

		return view;
	}

	class ViewHolder {
		public TextView name, exchange, price, volume, bid, offer,
				tv_day_gain_change_value;

		public ImageView ivChange, crossImage;
		public LinearLayout ll_bid_offer;
		public RelativeLayout rl_change;

	}

	private double setTextValueinKformatvai(double h) {

		// double h = 0;

		h = h / 1000;
		return round(h, 2);

	}

	public double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	private void setTextValue(TextView tx, String str1, String str2) {

		if (str2.equalsIgnoreCase("1")) {

			tx.setTextColor(mContext.getResources().getColor(R.color.green));

		} else {

			tx.setTextColor(mContext.getResources().getColor(R.color.red));
		}
	}

	private void setTextValuewithOutPer(TextView tx, String str1, String str2) {

		if (str2.equalsIgnoreCase("1")) {

			tx.setTextColor(mContext.getResources().getColor(R.color.green));

		} else {

			tx.setTextColor(mContext.getResources().getColor(R.color.red));
		}

		tx.setText(str1);
	}

	private void setPercentageText(String strData, TextView tx, double num) {

		if (strData.contains("-")) {

			tx.setText(strData + "%");
			tx.setTextColor(0xffff0000);
			tx.setTextColor(mContext.getResources().getColor(R.color.red));
		} else {
			tx.setText(strData + "%");
			tx.setTextColor(mContext.getResources().getColor(R.color.green));
		}

		// return strData;

	}

}
