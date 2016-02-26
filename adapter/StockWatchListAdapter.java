package com.moneycontrol.handheld.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.api.AppBeanParacable;
import com.moneycontrol.handheld.api.RequestType;
import com.moneycontrol.handheld.entity.watchlist.WatchListBean;
import com.moneycontrol.handheld.massages.fragments.BaseFragement;
import com.moneycontrol.handheld.netcomm.Communicator;
import com.moneycontrol.handheld.util.TaskCompleteListiners;
import com.moneycontrol.handheld.util.UrlUtils;
import com.moneycontrol.handheld.util.Utility;
import com.moneycontrol.handheld.watchlist.fragment.BaseWatchListFragment;

public class StockWatchListAdapter extends BaseAdapter implements
		TaskCompleteListiners {

	private Context mContext;
	private LayoutInflater mInflator;
	private ArrayList<WatchListBean> list = new ArrayList<WatchListBean>();
	private String htmlString;

	public StockWatchListAdapter(Context context, ArrayList<WatchListBean> list) {
		this.mContext = context;
		this.list = list;
		mInflator = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		htmlString = "<font color=#CCCCCC><b>%s</b></font> <font color=#999999>(%s)</font>";

	}

	public ArrayList<WatchListBean> getList() {
		return list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
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

		vHolder.crossImage.setTag(UrlUtils.deleteWatchListStock(mContext, list
				.get(position).getId()));
		if (list.get(position).getDirection().equalsIgnoreCase("1")) {
			vHolder.ivChange.setBackgroundResource(R.drawable.green_base);
		} else {
			vHolder.ivChange.setBackgroundResource(R.drawable.red_base);
		}
		vHolder.tv_day_gain_change_value.setText(list.get(position).getChange()
				+ "\n" + list.get(position).getPercentchange() + "%");
		vHolder.name.setText(list.get(position).getShortname());
		vHolder.exchange.setText(list.get(position).getExchg() + ": "
				+ list.get(position).getDttime());
		vHolder.price.setText(list.get(position).getLastvalue());
		vHolder.volume.setText("Vol: " + list.get(position).getVolume());

		vHolder.bid.setText(Html.fromHtml(String.format(htmlString,
				list.get(position).getBidprice(), list.get(position)
						.getBidqty())));
		vHolder.offer.setText(Html.fromHtml(String.format(htmlString,
				list.get(position).getOfferprice(), list.get(position)
						.getOfferqty())));
		return view;
	}

	class ViewHolder {
		public TextView name, exchange, price, volume, bid, offer,
				tv_day_gain_change_value;

		public ImageView ivChange, crossImage;
		public LinearLayout ll_bid_offer;
		public RelativeLayout rl_change;

	}

	@Override
	public void onTaskComplete(int requestType, AppBeanParacable appBean) {
		// TODO Auto-generated method stub

	}
}
